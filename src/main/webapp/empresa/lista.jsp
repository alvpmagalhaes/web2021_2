<%@ page import="br.ufscar.dc.dsw.domain.TipoLogin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				</h2>
			</div>
			<div align="center">
				<p>
				<form action="/<%= contextPath%>/empresas/lista" method="get">
					<fmt:message key="company_show_by_city"/>: 
					<select name="cidade">
						<c:forEach var="cidade" items="${requestScope.listaCidades}">
							<option value ="${cidade}">${cidade}</option>
						</c:forEach>
					</select>
					<input type="submit" value="<fmt:message key="choose"/>">
				</p>
				</form>
			</div>
			<div align="center">
				<table border="1">
					<caption>
						<fmt:message key="company_list"/>
					</caption>
					<tr>
						<th><fmt:message key="cnpj"/></th>
						<th><fmt:message key="name"/></th>
						<th><fmt:message key="email"/></th>
						<th><fmt:message key="city"/></th>
						<c:if test="${sessionScope.login != null && sessionScope.login.tipoLogin.equals(ADMIN)}">
							<th><fmt:message key="actions"/></th>
						</c:if>
					</tr>
					<c:forEach var="empresa" items="${requestScope.listaEmpresas}">
						<tr>
							<td>${empresa.cnpj}</td>
							<td>${empresa.nome}</td>
							<td>${empresa.email}</td>
							<td>${empresa.cidade}</td>
							<c:if test="${sessionScope.login != null && sessionScope.login.tipoLogin.equals(ADMIN)}">
							<td>
								<a href="/<%= contextPath%>/empresas/edicao?cnpj=${empresa.cnpj}">
									<fmt:message key="edition"/>
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message var="confirmation_text" key="confirmation_text"/>
								<a
									href="/<%= contextPath%>/empresas/remocao?cnpj=${empresa.cnpj}"
									onclick="return confirm(${confirmation_text});">
									<fmt:message key="removal"/>
								</a>
							</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>