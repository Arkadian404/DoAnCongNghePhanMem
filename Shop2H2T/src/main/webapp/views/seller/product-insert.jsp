<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>

<c:url value="/seller/product/insert" var="insertUrl"></c:url>
<form method="post" action="${insertUrl }">
  <div class="form-group">
    <label for="productName">Product name:</label>
    <input class="form-control" id="productName" placeholder="Enter product 's name" name="productName">
  </div>
  <div class="form-group">
    <label for="productDescription">Product description:</label>
    <input class="form-control" id="productDescription" placeholder="Description" name="productDescription">
  </div>  
  <div class="form-group">
    <label for="productPrice">Product price:</label>
    <input class="form-control" id="productPrice" placeholder="Price" name="productPrice">
  </div>
  <div class="form-group">
    <label for="productStock">Product stock:</label>
    <input class="form-control" id="productStock" placeholder="Stock" name="productStock" type="number">
  </div>
  <div class="form-group">
    <label for="productImage">Product image:</label>
    <input class="form-control" id="productImage" placeholder="Image" name="productImage">
  </div>
  <div class="form-group">
    <label for="productCreateDate">Create date:</label>
    <input class="form-control" id="productCreateDate" placeholder="Image" name="productCreateDate" type="date">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
  <a class="btn btn-secondary" href="<%=request.getContextPath() %>/common/home">Back</a>
</form>
