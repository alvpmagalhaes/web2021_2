<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="new_lease"/>
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
					<fmt:message key="application_registration"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/vaga/insercao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="application_registration"/>
						</legend>
						
						<label for="empresa">
							<fmt:message key="application"/>:
						</label> </br>

						<select name="empresa" id="empresa">
							<c:forEach var="empresa" items="${requestScope.listaEmpresas}">
								<option value="${empresa.cnpj}">${empresa.cnpj}, ${empresa.nome}, ${empresa.cidade}</option>
							</c:forEach>
						</select> <br/>
						
						<br/>
							
						<label for="data">
							<fmt:message key="date"/>:
						</label> <br/>
						<input type="date" id="data" name="data"> <br/>
  						
  						<br/>
  						
  						
						</br>
						
						<input type="submit" name="enviar" value="<fmt:message key="register"/>"/>
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>