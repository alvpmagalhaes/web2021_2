package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LoginDAO;
import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login/*")
public class LoginController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private final String logoutUrl = "/logout";
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
			redirectLogout(request,response,erros);
		}

		Login login = loginDAO.getLoginByEmail(email);
		if (login == null || !login.getSenha().equals(senha)) {
			erros.add("Login ou senha inválida!");
			redirectLogout(request,response,erros);
		}

		request.getSession().setAttribute("login", login);
		String URL = "/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
		return;

    }

    private void redirectLogout(HttpServletRequest request, HttpServletResponse response, Erro erros) throws ServletException, IOException {
		request.setAttribute("mensagens", erros);
		RequestDispatcher rd = request.getRequestDispatcher(logoutUrl);
		rd.forward(request, response);
	}
}
