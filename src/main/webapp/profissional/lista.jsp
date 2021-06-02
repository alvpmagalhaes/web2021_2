<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="professional_managing"/>
			</title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<div align="center">
				<h1>
					<fmt:message key="professional_managing"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/profissional/cadastro">
						<fmt:message key="professional_new"/>
					</a>
				</h2>
			</div>
			<div align="center">
				<table border="1">
					<caption>
						<fmt:message key="professional_list"/>
					</caption>
					<tr>
						<th><fmt:message key="cpf"/></th>
						<th><fmt:message key="name"/></th>
						<th><fmt:message key="email"/></th>
						<th><fmt:message key="pw"/></th>
						<th><fmt:message key="gender"/></th>
						<th><fmt:message key="phone"/></th>
		                <th><fmt:message key="birthdate"/></th>
		                <th><fmt:message key="actions"/></th>
					</tr>
					<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
						<tr>
							<td>${profissional.cpf}</td>
							<td>${profissional.nome}</td>
							<td>${profissional.email}</td>
							<td>${profissional.senha}</td>
							<td>${profissional.sexo}</td>
		                    <td>${profissional.telefone}</td>
		                    <td>${profissional.dataDeNascimento}</td>
							<td>
								<a href="/<%= contextPath%>/profissionais/edicao?cpf=${profissional.cpf}">
									<fmt:message key="edition"/>
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message var="confirmation_text" key="confirmation_text"/>
								<a
									href="/<%= contextPath%>/profissionais/remocao?cpf=${profissional.cpf}"
									onclick="return confirm(${confirmation_text});">
									<fmt:message key="removal"/>
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>