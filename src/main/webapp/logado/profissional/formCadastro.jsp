<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="professional_registration"/>
			</title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        	</c:if>
			<div align="center">
				<h1>
					<fmt:message key="professional_registration"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/profissional/insercao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="professional_registration"/>
						</legend>
						<fmt:message key="cpf"/> </br>
						<input type="text" name="cpf"/> <br/>
						<fmt:message key="name"/> </br>
						<input type="text" name="nome"/> <br/>
						<fmt:message key="email"/> </br>
						<input type="email" name="email"/> <br/>
						<fmt:message key="pw"/> </br>
						<input type="password" name="senha"/> <br/>
						<fmt:message key="gender"/> </br>
						<input type="text" name="sexo"/> <br/>
						<fmt:message key="phone"/> </br>
						<input type="text" name="telefone" placeholder="X XXXX-XXXX"/> <br/>
						<c:if test="${sessionScope.login != null && sessionScope.login.tipoLogin.equal(TipoLogin.ADMIN)}">
							<fmt:message key="role"/> </br>
							<select name="papel" id="papel">
								<option value="USER">USER</option>
								<option value="ADMIN">ADMIN</option>
							</select> <br/>
						</c:if>
						<fmt:message key="birthdate"/> </br>
						<input type="text" name="dataDeNascimento" placeholder="<fmt:message key="birthdate.ph"/>"/> <br/>
						<input type="submit" name="enviar" value="<fmt:message key="register"/>" />
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>