<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="company_registration"/>
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
					<fmt:message key="company_registration"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
					<c:if test="${sessionScope.login != null && sessionScope.login.tipoLogin.equal(TipoLogin.ADMIN)}">
						&nbsp;&nbsp;&nbsp;
						<a href="/<%=contextPath%>/empresa/cadastro">
							<fmt:message key="company_new"/>
						</a>
					</c:if>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/empresa/lista">
						<fmt:message key="company_show_all"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/empresa/insercao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="company_registration"/>
						</legend>
						<fmt:message key="cnpj"/> </br>
						<input type="text" name="cnpj"/> <br/>
						<fmt:message key="name"/> </br>
						<input type="text" name="nome"/> <br/>
						<fmt:message key="email"/> </br>
						<input type="email" name="email"/> <br/>
						<fmt:message key="pw"/> </br>
						<input type="password" name="senha"/> <br/>
						<fmt:message key="city"/> </br>
						<input type="text" name="cidade"/> <br/>
						<input type="submit" name="enviar" value="<fmt:message key="register"/>"/>
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>