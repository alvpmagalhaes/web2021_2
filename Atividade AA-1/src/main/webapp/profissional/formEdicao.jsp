<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
					<fmt:message key="professional_managing"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/profissional/cadastro">
						<fmt:message key="client_new"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/profissionais/atualizacao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="client_edition"/>
						</legend>
						<fmt:message key="cpf.readonly"/></br>
						<input type="text" name="cpf" value="${profissional.cpf}" readonly/> <br/>
						<fmt:message key="name"/> </br>
						<input type="text" name="nome" value="${profissional.nome}"/> <br/>
						<fmt:message key="email"/> </br>
						<input type="email" name="email" value="${profissional.email}"/> <br/>
						<fmt:message key="pw"/> </br>
						<input type="password" name="senha" value="${profissional.senha}"/> <br/>
						<fmt:message key="gender"/> </br>
						<input type="text" name="sexo" value="${profissional.sexo}"/> <br/>
						<fmt:message key="phone"/> </br>
						<input type="text" name="telefone" value="${profissional.telefone}"/> <br/>
						<fmt:message key="birthdate"/> </br>
						<input type="datetime-local" name="dataDeNascimento"  value="${profissional.dataDeNascimento}"/> <br/>
						<input type="submit" name="enviar" value="<fmt:message key="update"/>" />
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>