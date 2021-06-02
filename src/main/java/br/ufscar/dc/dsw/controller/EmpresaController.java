package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/empresas/*")
public class EmpresaController extends HttpServlet implements BaseController {

    private static final long serialVersionUID = 1L; 
    private EmpresaDAO dao;
    private VagaDAO vagaDAO;
    private CandidaturaDAO candidaturaDAO;

    @Override
    public void init() {
        dao = new EmpresaDAO();
        vagaDAO = new VagaDAO();
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
                case "/vagas":
                    getVagas(request, response);
                    break;
                case "/candidaturas":
                    getCandidaturas(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void getCandidaturas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Erro erros = new Erro();
        Login logado = (Login) request.getSession().getAttribute("login");

        if (logado == null) {
            erros.add("Precisa estar logado para acessar essa página.");
            redirectErrorTo(request,response,erros,loginUrl);
            return;
        }

        if (!logado.getTipoLogin().equals(TipoLogin.EMPRESA)) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [EMPRESA] pode acessar essa página.");
            redirectErrorTo(request,response,erros,noAuthUrl);
            return;
        }

        List<Candidatura> candidaturas = null;
        Empresa empresa = dao.getByEmail(logado.getEmail());

        try {
            candidaturas = candidaturaDAO.getAllByCnpjEmpresa(empresa);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/candidatura/lista.jsp");
            return;
        }
        request.setAttribute("listaCandidaturas", candidaturas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/candidatura/lista.jsp");
        dispatcher.forward(request, response);

    }

    private void getVagas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Erro erros = new Erro();
        Login logado = (Login) request.getSession().getAttribute("login");

        if (logado == null) {
            erros.add("Precisa estar logado para acessar essa página.");
            redirectErrorTo(request,response,erros,loginUrl);
            return;
        }

        if (!logado.getTipoLogin().equals(TipoLogin.EMPRESA)) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [EMPRESA] pode acessar essa página.");
            redirectErrorTo(request,response,erros,noAuthUrl);
            return;
        }

        List<Vaga> vagas = null;
        Empresa empresa = dao.getByEmail(logado.getEmail());

        try {
            vagas = vagaDAO.getAllByCnpjEmpresa(empresa.getCnpj());
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/vaga/lista.jsp");
            return;
        }
        request.setAttribute("listaVagas", vagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vaga/lista.jsp");
        dispatcher.forward(request, response);

    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empresa> empresas = dao.getAll();
        request.setAttribute("listaEmpresas", empresas);

        Set<String> listaCidades = new HashSet<String>();

        for (int i = 0; i < empresas.size(); i++) {
            String cidade = empresas.get(i).getCidade();
            if (!listaCidades.contains(cidade)) {
                listaCidades.add(cidade);
            }
        }
        request.setAttribute("listaCidades", listaCidades);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void listaCidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empresa> empresas = null;
        String cidade = request.getParameter("cidade");
        try {
            empresas = dao.getAllCidade(cidade);
        } catch (Exception e) {
            Erro erros = new Erro();
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/empresa/lista.jsp");
            return;
        }
        request.setAttribute("listaEmpresas", empresas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/listaCidade.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/formCadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cidade = request.getParameter("cidade");
        String descricao = request.getParameter("descricao");

        Empresa empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);
        try {
            dao.insert(empresa);
        } catch (Exception e) {
            Erro erros = new Erro();
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/empresa/formCadastro.jsp");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("empresas");
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
        
        String cnpj = request.getParameter("cnpj");
        Empresa empresa = dao.get(cnpj);
        dao.delete(empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("empresas");
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
        String cnpj = request.getParameter("cnpj");
        Empresa empresa = dao.get(cnpj);
        request.setAttribute("empresa", empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/formEdicao.jsp");
        dispatcher.forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        String cnpj = request.getParameter("cnpj");
        Empresa empresa = dao.get(cnpj);

        String nome = request.getParameter("nome");
        if (nome == "") {
            nome = empresa.getNome();
        }
        String email = request.getParameter("email");
        if (email == "") {
            email = empresa.getEmail();
        }
        String senha = request.getParameter("senha");
        if (senha == "") {
            senha = empresa.getSenha();
        }
        String cidade = request.getParameter("cidade");
        if (cidade == "") {
            cidade = empresa.getCidade();
        }
        String descricao = request.getParameter("descricao");
        if (descricao == "") {
            descricao = empresa.getDescricao();
        }

        Empresa empresaAtualizada = new Empresa(email, senha, cnpj, nome, descricao, cidade);
        try {
            dao.update(empresaAtualizada);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/empresa/formEdicao.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("empresas");
        dispatcher.forward(request, response);
    }
}
