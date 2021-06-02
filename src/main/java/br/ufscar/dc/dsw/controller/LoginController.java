package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LoginDAO;
import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.domain.TipoLogin;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login/*")
public class LoginController extends HttpServlet implements BaseController{
    
    private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO;

	@Override
	public void init() throws ServletException {
		loginDAO = new LoginDAO();
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Erro erros = new Erro();

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
			erros.add("Login ou senha não informado!");
			redirectErrorTo(request,response,erros, logoutUrl);
			return;
		}

		if (email.equals("admin") && senha.equals("admin")){
			Login loginAdmin = new Login(email,senha, TipoLogin.ADMIN);
			redirectToIndex(request,response,loginAdmin);
			return;
		}

		Login login = loginDAO.getLoginByEmail(email);
		if (login == null || !login.getSenha().equals(senha)) {
			erros.add("Login ou senha inválida!");
			redirectErrorTo(request,response,erros, logoutUrl);
			return;
		}

		redirectToIndex(request,response,login);
		return;

    }

	private void redirectToIndex(HttpServletRequest request, HttpServletResponse response, Login login) throws ServletException, IOException {
		request.getSession().setAttribute("login", login);
		String URL = "/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
}
