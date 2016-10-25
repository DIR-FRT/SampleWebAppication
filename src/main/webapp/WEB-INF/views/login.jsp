<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
<div>
	<h2>ユーザ管理（CI基盤：サンプルWeb）</h2>
</div>
<div class="container">
    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">ログイン</h2>
        	<div><span>${message}</span></div>
         	<label>ユーザ名</label>
         	<div class="form-group ${error != null ? 'has-error' : ''}">
	            <input type="text" id="username" name="username" class="form-control"
	                   autofocus></input>
            </div>
            <label>パスワード</label>
            <div class="form-group ${error != null ? 'has-error' : ''}">
	            <input type="password" id="password" name="password" class="form-control"/>
	            <span>${error}</span>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="text-center">
            	<button class="btn btn-lg btn-primary btn-block" id="login" type="submit">ログイン</button>
            </div>
    </form>
</div>
<!-- /container -->
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
