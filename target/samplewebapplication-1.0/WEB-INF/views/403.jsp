<%--
  User: Ranga Reddy
  Date: 1/27/2015
  Time: 9:52 PM
  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Page</title>

<!-- Bootstrap CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

<style type="text/css">
.myrow-container {
	margin: 20px;
}
</style>
</head>
<body class=".container-fluid">
	<div class="container myrow-container">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h1>HTTP Status 403 - Access is denied</h1>
				<h3 class="panel-title">
					<c:choose>
						<c:when test="${empty username}">
							<h2>You do not have permission to access this page!</h2>
						</c:when>
						<c:otherwise>
							<h2>
								Username : ${username} <br />You do not have permission to
								access this page!
							</h2>
						</c:otherwise>
					</c:choose>
				</h3>
			</div>
		</div>
	</div>
	
<script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

</body>
</html>