<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users List</title>

<!-- Bootstrap CSS -->
<%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">
.myrow-container {
	margin: 20px;
}
</style>
</head>
<body class=".container-fluid">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form:form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form:form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			User : ${pageContext.request.userPrincipal.name} | <a id="logout"
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	<div class="container myrow-container">
		<div class="panel panel-success">
			<div class="panel-heading">
				<div align="left">
					<h3 class="panel-title">
						<b>Manage Users</b>
					</h3>
				</div>
			</div>
			<div class="panel-body">
				<c:if test="${empty userList}">
                There are no Users
            </c:if>
				<c:if test="${not empty userList}">

					<table class="table table-hover table-bordered">
						<thead style="background-color: #bce8f1;">
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Email</th>
								<th colspan="2">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<th><c:out value="${user.id}" /></th>
									<th><c:out value="${user.username}" /></th>
									<th><c:out value="${user.email}" /></th>
									<th><a id="editUser"
										href="editUser?id=<c:out value='${user.id}'/>">Edit</a></th>
									<th><a id="disableUser"
										href="deleteUser?id=<c:out value='${user.id}'/>">Disable</a></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>