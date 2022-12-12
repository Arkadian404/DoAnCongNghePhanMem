<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<h2>Login Page</h2>
<c:url value="/common/login" var="loginControllerUrl"></c:url>
<div>
	<form role="form" action="${loginControllerUrl }" method="post">
		Username: <input type="text" name="username" placeholder="Nhập username của bạn:"><br>		
		Password: <input type="password" name="password" placeholder="Nhập password của bạn:"><br>
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</div>