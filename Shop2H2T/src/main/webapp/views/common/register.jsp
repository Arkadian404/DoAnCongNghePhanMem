<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<h2>Register Page</h2>
<c:url value="/common/register" var="registerControllerUrl"></c:url>
<div>
	<form role="form" action="${registerControllerUrl }" method="post">
		Username: <input type="text" name="username" placeholder="Nhập username của bạn:"><br>		
		Password: <input type="password" name="password" placeholder="Nhập password của bạn:"><br>
		Confirmed password: <input type="password" name="confirmedPassword" placeholder="Xác nhận lại password đã nhập"><br>
		<button type="submit" class="btn btn-primary">Register</button>
	</form>
</div>