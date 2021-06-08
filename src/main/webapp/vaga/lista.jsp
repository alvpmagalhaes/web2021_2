<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="leases"/>
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
					<fmt:message key="applications"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/vaga/cadastro">
						<fmt:message key="new_application"/>
					</a>
				</h2>
			</div>
			<div align="center">
				<c:if test="${requestScope.listaVagas != null}">
				<table border="1">
					<caption>
						<fmt:message key="my_leases"/>
					</caption>
					<tr>
						<th><fmt:message key="professional_cpf"/></th>
						<th><fmt:message key="professional_name"/></th>
						<th><fmt:message key="company_cnpj"/></th>
						<th><fmt:message key="company_name"/></th>
						<th><fmt:message key="date"/></th>
					</tr>
					<c:forEach var="locacao" items="${requestScope.listaLocacoes}">
						<tr>
							<td>${vaga.professional.cpf}</td>
							<td>${vaga.professional.nome}</td>
							<td>${vaga.empresa.cnpj}</td>
							<td>${vaga.empresa.nome}</td>
							<td>${vaga.dataLimite}</td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				<c:if test="${requestScope.listaVagas == null}">
					<fmt:message key="no_applications"/>
				</c:if>
			</div>
		</body>
	</fmt:bundle>
</html>