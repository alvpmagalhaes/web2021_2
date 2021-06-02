<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="msgs">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
        <script src="toggle.js"></script>

    </head>
    <body>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <div style="text-align:center;">
            <button class="btn cliente" onclick="toggleLoader()"> <fmt:message key="client"/> </button>
            <button class="btn locadora" style="display: none;" onclick="toggleLoader()"> <fmt:message key="company"/> </button>
        </div>
        <div class="box profissional">     
            <h2>Login</h2>
            <form method="post" action="login">
                
                <div class="login-form">
                    <label for="profissional_login">Email</label>
                    <input type="text" name="profissional_login" value="${param.login}" placeholder="Email"/>
                    
                    <label for="cliente_password">Password</label>
                    <input type="password" name="profissional_senha" class="password" placeholder=<fmt:message key="str_pw"/>>
                </div>
                
                <input type="submit" class="submit" style="float:right" name="profissionalOK" value=<fmt:message key="str_login"/>>
            </form>
        </div>

        <div class="box locadora" style="border-left: 5px solid rgb(255, 77, 70); display: none;">
            <h2>Login</h2>
            <form method="post" action="login">
                
                <div class="login-form">
                    <label for="empresa_login">Email</label>
                    <input type="text" name="empresa_login" value="${param.login}" placeholder="Email"/>
                    
                    <label for="empresa_password"><fmt:message key="pw"/> </label>
                    <input type="password" name="empresa_senha" class="password" placeholder=<fmt:message key="str_pw"/>>
                </div>
                
                <input type="submit" class="submit" style="float:right" name="locadoraOK" value=<fmt:message key="str_login"/>>
            </form>
        </div>

    </body>
    </fmt:bundle>
</html>
