package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.domain.TipoLogin;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.DataUtil;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/vagas/*")
public class VagaController extends HttpServlet implements BaseController {

    private static final long serialVersionUID = 1L;
    private VagaDAO dao;

    @Override
    public void init() {
        dao = new VagaDAO();
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
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vaga> vagas = null;
        String cidade = request.getParameter("cidade");

        try {
            if (cidade == null || cidade.isEmpty()){
                vagas = dao.getAll();
            }else{
                vagas = dao.getAllByCidade(cidade);
            }
        } catch (Exception e) {
            Erro erros = new Erro();
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/vaga/lista.jsp");
        }
        request.setAttribute("listaVagas", vagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vaga/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vaga/formCadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        try {
            String cargo = request.getParameter("cargo");
            String descricao = request.getParameter("descricao");
            String remuneracao = request.getParameter("remuneracao");
            Date dataLimite = DataUtil.convertStringToDate(request.getParameter("dataLimite"));
            Empresa empresa = new Empresa(request.getParameter("cnpjEmpresa"));
            Vaga vaga = new Vaga(cargo,descricao,remuneracao,dataLimite,empresa);
            dao.insert(vaga);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/vaga/formCadastro.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("vagas");
        dispatcher.forward(request, response);
    }
}
