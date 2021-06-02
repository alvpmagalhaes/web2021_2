package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.domain.TipoLogin;
import br.ufscar.dc.dsw.util.DataUtil;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/profissional/*")

public class ProfissionalController extends HttpServlet implements BaseController {
	
	private static final long serialVersionUID = 1L; 
    private ProfissionalDAO dao;

    @Override
    public void init() {
        dao = new ProfissionalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {           
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                //default:
                //    lista(request, response);
                //    break;
            }
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        }
    }

   /*
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profissional> profissionais = dao.getAll();
        request.setAttribute("listaEmpresas", profissionais);

        Set<String> listaCidades = new HashSet<String>();

        for (int i = 0; i < profissionais.size(); i++) {
            String cidade = profissionais.get(i).getCidade();
            if (!listaCidades.contains(cidade)) {
                listaCidades.add(cidade);
            }
        }
        request.setAttribute("listaCidades", listaCidades);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void listaCidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profissional> profissionais = null;
        String cidade = request.getParameter("cidade");
        try {
            profissionais = dao.getAllCidade(cidade);
        } catch (Exception e) {
            Erro erros = new Erro();
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/profissional/lista.jsp");
        }
        request.setAttribute("listaEmpresas", profissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/listaCidade.jsp");
        dispatcher.forward(request, response);
    }*/

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formCadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataDeNascimento = DataUtil.convertStringToDate(request.getParameter("dataDeNascimento"));
        

        Profissional profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, dataDeNascimento);
        try {
            dao.insert(profissional);
        } catch (Exception e) {
            Erro erros = new Erro();
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/profissional/formCadastro.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("profissionais");
        dispatcher.forward(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Erro erros = new Erro();
        Login logado = (Login) request.getSession().getAttribute("login");
        
        if (logado == null) {
            erros.add("Precisa estar logado para acessar essa página.");
            redirectErrorTo(request,response,erros,loginUrl);
            return;
        }

        if (!logado.getTipoLogin().equals(TipoLogin.ADMIN)) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [ADMIN] pode acessar essa página.");
            redirectErrorTo(request,response,erros,noAuthUrl);
            return;
        }
        
        String cpf = request.getParameter("cpf");
        Profissional profissional = dao.get(cpf);
        dao.delete(profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("profissionais");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Erro erros = new Erro();
        Login logado = (Login) request.getSession().getAttribute("login");
        
        if (logado == null) {
            erros.add("Precisa estar logado para acessar essa página.");
            redirectErrorTo(request,response,erros,loginUrl);
            return;
        }

        if (!logado.getTipoLogin().equals(TipoLogin.ADMIN)) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [ADMIN] pode acessar essa página.");
            redirectErrorTo(request,response,erros,noAuthUrl);
            return;
        }
        
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        Profissional profissional = dao.get(cpf);
        request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formEdicao.jsp");
        dispatcher.forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        Erro erros = new Erro();
        Login logado = (Login) request.getSession().getAttribute("login");
        
        if (logado == null) {
            erros.add("Precisa estar logado para acessar essa página.");
            redirectErrorTo(request,response,erros,loginUrl);
            return;
        }

        if (!logado.getTipoLogin().equals("ADMIN")) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [ADMIN] pode acessar essa página.");
            redirectErrorTo(request,response,erros,noAuthUrl);
            return;
        }
        
        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf");
        Profissional profissional = dao.get(cpf);

        String nome = request.getParameter("nome");
        if (nome == "") {
            nome = profissional.getNome();
        }
        String email = request.getParameter("email");
        if (email == "") {
            email = profissional.getEmail();
        }
        String senha = request.getParameter("senha");
        if (senha == "") {
            senha = profissional.getSenha();
        }
        String telefone = request.getParameter("telefone");
        if (telefone == "") {
            telefone = profissional.getTelefone();
        }
        String sexo = request.getParameter("sexo");
        if (sexo == "") {
            sexo = profissional.getSexo();
        }
        String dataDeNascimentoString = request.getParameter("dataNascimento");
        Date dataDeNascimento = profissional.getDataDeNascimento();
        if (dataDeNascimentoString != "") {
            dataDeNascimento = DataUtil.convertStringToDate(request.getParameter("dataNascimento"));
        }

        
        Profissional profissionalAtualizado = new Profissional(email, senha, cpf, nome, telefone, sexo, dataDeNascimento);
        try {
            dao.update(profissionalAtualizado);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/profissional/formEdicao.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("profissional");
        dispatcher.forward(request, response);
    }

}
