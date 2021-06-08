package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BaseController {

    String logoutUrl = "/logout";
    String noAuthUrl = "/noAuth.jsp";
    String loginUrl = "/login.jsp";

    default void redirectErrorTo(HttpServletRequest request, HttpServletResponse response, Erro erros, String url ) throws ServletException, IOException {
        request.setAttribute("mensagens", erros);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
