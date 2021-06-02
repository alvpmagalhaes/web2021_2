package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.util.DataUtil;
import br.ufscar.dc.dsw.util.Erro;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/empresas/*")
public class CandidaturaController extends HttpServlet implements BaseController {

    private static final long serialVersionUID = 1L;
    private CandidaturaDAO dao;
    private ProfissionalDAO profissionalDAO;
    private VagaDAO vagaDAO;

    @Override
    public void init() {
        dao = new CandidaturaDAO();
        vagaDAO = new VagaDAO();
        profissionalDAO = new ProfissionalDAO();
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
                case "/recusar":
                    recusar(request, response);
                    break;
                case "/aprovar":
                    aprovar(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void recusar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        String dataCandidatura = request.getParameter("dataCandidatura");
        String codigoVaga = request.getParameter("codigoVaga");
        String cpfProfissional = request.getParameter("cpfProfissional");

        try {
            dao.recusar(dataCandidatura,codigoVaga,cpfProfissional);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/candidatura/lista.jsp");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresas/candidaturas");
        dispatcher.forward(request, response);
    }

    private void aprovar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        String dataCandidatura = request.getParameter("dataCandidatura");
        String codigoVaga = request.getParameter("codigoVaga");
        String cpfProfissional = request.getParameter("cpfProfissional");

        try {
            dao.aprovar(dataCandidatura,codigoVaga,cpfProfissional);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/candidatura/lista.jsp");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresas/candidaturas");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/candidatura/formCadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long codigoVaga = null;

        Erro erros = new Erro();
        Login logado = (Login) request.getSession().getAttribute("login");

        if (logado == null) {
            erros.add("Precisa estar logado para acessar essa página.");
            redirectErrorTo(request,response,erros,loginUrl);
            return;
        }

        if (!logado.getTipoLogin().equals(TipoLogin.PROFISSIONAL)) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [PROFISSIONAL] pode acessar essa página.");
            redirectErrorTo(request,response,erros,noAuthUrl);
            return;
        }

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        FileItem arquivo = null;

        try
        {
            if(isMultipart)
            {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);

                for(FileItem item : items)
                {
                    if(item.isFormField())
                    {
                        if(item.getFieldName().equals("codigoVaga"))
                            codigoVaga = Long.parseLong(item.getString());
                    }
                    else
                    {
                        arquivo = item;
                    }
                }

                Profissional profissional = profissionalDAO.getByEmail(logado.getEmail());
                Vaga vaga = vagaDAO.get(codigoVaga);

                String[] nomeArquivoSplit = arquivo.getFieldName().split(".");

                String nomeArquivo = profissional.getCpf()+"_"+
                        vaga.getCodigo()+"_"+
                        DataUtil.convertDateToString(new Date())+"."+
                        nomeArquivoSplit[nomeArquivoSplit.length-1];
                Candidatura candidatura = new Candidatura(profissional,vaga, nomeArquivo);

                dao.insert(candidatura);

                File uploadFile = new File("/arquivo/" + nomeArquivo);
                arquivo.write(uploadFile);

            }
        }
        catch(Exception ex)
        {
            erros = new Erro();
            erros.add("Erro nos dados preenchidos.");
            redirectErrorTo(request,response,erros,"/empresa/formCadastro.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/candidaturas");
        dispatcher.forward(request, response);
    }
}
