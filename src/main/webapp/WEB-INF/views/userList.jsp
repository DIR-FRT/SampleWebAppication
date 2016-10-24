<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<div class="panel-logout">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
			ユーザ : ${pageContext.request.userPrincipal.name} | <a id="logout"
				href="javascript:formSubmit()"> ログアウト</a>
	</c:if>
	</div>
<div>
	<h2>ユーザ管理（CI基盤：サンプルWeb）</h2>
</div>
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
	<div class="container myrow-container">
		<div class="panel panel-success">
			<div class="panel-heading">
				<div class="panel-title">ユーザ一覧</div>
				<div class="panel-register"><a href="registration">新規ユーザ作成</a></div>
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
								<th>ユーザ名</th>
								<th>メールアドレス</th>
								<th class="text-center">ステータス</th>
								<th colspan="2">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<th><c:out value="${user.id}" /></th>
									<th><c:out value="${user.username}" /></th>
									<th><c:out value="${user.email}" /></th>
									<th class="text-center">
										<c:if test="${user.active == true}">
											<div>有効</div>
										</c:if>
										<c:if test="${user.active == false}">
											<div class="text-danger">無効</div>
										</c:if>
									</th>
									<th>
										<a id="editUser" href="editUser?id=<c:out value='${user.id}'/>">編集</a>
									</th>
									<th>
										<c:if test="${pageContext.request.userPrincipal.name != user.username}">
											<c:choose >
												<c:when test="${user.active == true}">
													<a id="disableUser" href="disableUser?id=<c:out value='${user.id}'/>">無効化する</a>
												</c:when>
												<c:when test="${user.active == false}">
													<a id="enableUser" href="enableUser?id=<c:out value='${user.id}'/>">有効化する</a>
												</c:when>
											</c:choose>
										</c:if>
									</th>
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