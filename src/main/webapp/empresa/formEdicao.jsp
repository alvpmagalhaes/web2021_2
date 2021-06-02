<%@ page import="br.ufscar.dc.dsw.domain.TipoLogin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="company_managing"/>
			</title>
		</head>
		<body>
		<c:set var="PROFISSIONAL" value="<%=TipoLogin.PROFISSIONAL%>"/>
		<c:set var="EMPRESA" value="<%=TipoLogin.EMPRESA%>"/>
		<c:set var="ADMIN" value="<%=TipoLogin.ADMIN%>"/>
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
					<fmt:message key="company_managing"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
					<c:if test="${sessionScope.login != null && sessionScope.login.tipoLogin.equals(ADMIN)}">
						&nbsp;&nbsp;&nbsp;
						<a href="/<%=contextPath%>/empresas/cadastro">
							<fmt:message key="company_new"/>
						</a>
					</c:if>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/empresas/lista">
						<fmt:message key="empresacompany_show_all"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/empresas/atualizacao" method="get">
					<fieldset>
						<legend>
							<fmt:message key="company_edition"/>
						</legend>
						<fmt:message key="cnpj.readonly"/> </br>
						<input type="text" name="cnpj" value="${empresa.cnpj}" readonly/> <br/>
						<fmt:message key="name"/> </br>
						<input type="text" name="nome" value="${empresa.nome}"/> <br/>
						<fmt:message key="email"/> </br>
						<input type="email" name="email" value="${empresa.email}"/> <br/>
						<fmt:message key="pw"/> </br>
						<input type="password" name="senha" value="${empresa.senha}"/> <br/>
						<fmt:message key="city"/> </br>
						<input type="text" name="cidade" value="${empresa.cidade}"/> <br/>
						<input type="submit" name="enviar" value="<fmt:message key="update"/>" />
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>