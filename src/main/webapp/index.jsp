<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import = "br.ufscar.dc.dsw.domain.*" %>

<!DOCTYPE html>
<html>
	<fmt:bundle basename="msgs">
	    <head>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <title>
	        	<fmt:message key="title"/>
	        </title>
	    </head>
	    <body>
			<c:set var="PROFISSIONAL" value="<%=TipoLogin.PROFISSIONAL%>"/>
			<c:set var="EMPRESA" value="<%=TipoLogin.EMPRESA%>"/>
			<c:set var="ADMIN" value="<%=TipoLogin.ADMIN%>"/>
			<div class="menu">
				<c:if test="${sessionScope.login == null}">
					<fmt:message key="index_hello"/>
				</c:if>
				<c:if test="${sessionScope.login != null}">
					<fmt:message key="index_hello_logged"/> ${sessionScope.login.email}.
				</c:if>
				<br>
				<a href="empresas">
					<fmt:message key="rental_company_list"/> 
				</a>
				<br>
				<c:if test="${sessionScope.login != null &&  sessionScope.login.tipoLogin.equals(PROFISSIONAL)}">
					<a href="vagas">Vagas
						<fmt:message key="client_list"/>
					</a>
					<br>
					<a href="profissional/candidaturas">Candidaturas
						<fmt:message key="client_list"/> 
					</a>
					<br>
				</c:if>
				<c:if test="${sessionScope.login != null && sessionScope.login.tipoLogin.equals(EMPRESA)}">
					<a href="vagas/cadastro">Cadastro de vagas
						<fmt:message key="my_leases"/>
					</a>
					<br>
					<a href="empresas/vagas">Vagas
						<fmt:message key="my_leases"/> 
					</a>
					<br>
					<a href="empresas/candidaturas">Candidaturas
						<fmt:message key="my_leases"/>
					</a>
					<br>
				</c:if>
				<c:if test="${sessionScope.login != null && sessionScope.login.tipoLogin.equals(ADMIN)}">
					<a href="profissional/cadastro">cadastro profissional
						<fmt:message key="my_leases"/>
					</a>
					<br>
					<a href="profissional">Profissionais
						<fmt:message key="my_leases"/>
					</a>
					<br>
					<a href="empresas/cadastro">cadastro empresa
						<fmt:message key="my_leases"/>
					</a>
					<br>
					<a href="empresas">Empresas
						<fmt:message key="my_leases"/>
					</a>
					<br>
				</c:if>
				<c:choose>
				<c:when test="${sessionScope.login == null}">
					<a href="login.jsp">
						Login
					</a>
					<br>
					<a href="vagas">
						<fmt:message key="client_registration"/> 
					</a>
					<br>
					<br>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/logout">
						<fmt:message key="log_out"/> 
					</a>
					<br>
				</c:otherwise>
				</c:choose>
			</div>
	    </body>
    </fmt:bundle>
</html>