<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<div class="container mt-3">
    <div class="row">
        <div class="col-sm">
            <div class="card">
                <div class="card-header bg-primary text-white text-uppercase">
                    <i class="fa fa-star"></i> All Product for category: ${categoryName} 
                </div>
                <div class="card-body">
                    <div class="row">
                    	<c:forEach items="${productsByCategory }" var="product" varStatus="Stt">	                    
	                        <div class="col-sm">
	                            <div class="card">
	                                <img class="card-img-top" src="${product.images }" alt="Card image cap" width="600" height="280">
	                                <div class="card-body">
	                                    <h4 class="card-title"><a href="<%=request.getContextPath() %>/common/product/detail?productId=${product.productId }" title="View Product">${product.productName }</a></h4>
	                                    <p class="card-text">${product.description}</p>
	                                    <div class="row">
	                                        <div class="col">
	                                            <p class="btn btn-danger btn-block">${product.price} $</p>
	                                        </div>
	                                        <div class="col">
	                                            <a href="<%=request.getContextPath() %>/common/cart?productId=${product.productId }&action=addToCart" class="btn btn-success btn-block">Add to cart</a>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	
	
