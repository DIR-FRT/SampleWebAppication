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

    <title>Edit	an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div>
	<h2>ユーザ管理（CI基盤：サンプルWeb）</h2>
</div>
<div class="container">

    <form:form method="POST" action="editUser" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading text-center">ユーザ情報編集</h2>
        <spring:bind path="username">
            <div>
            	<form:hidden path="id"/>
            	<label>ユーザ名</label>
                <form:input type="text" path="username" class="form-control" disabled="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="password">
        	<label>パスワード（8文字以上～３２文字以内）</label>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" autofocus="true"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
        	<label>パスワード（確認）</label>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="email">
        	<label>メールアドレス</label>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" placeholder="Email"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>
		<div class="text-center">
			<button class="btn btn-lg btn-primary btn-block" type="submit">送信</button>
		</div>
		<div class="text-right backlink">
			<a href="${contextPath}/">ユーザ一覧に戻る</a>
	    </div>
    </form:form>
</div>
<!-- /container -->
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
