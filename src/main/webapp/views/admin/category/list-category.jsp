<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container-xl">
	<div class="table-responsive">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>Quản lý <b>Danh mục</b></h2>
					</div>
					<div class="col-sm-6">
						<a href="#addCategoryModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm danh mục</span></a>					
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Ten danh muc</th>
						<th>Trang thai</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${categoryList}" var="list">
					<tr>
						<td>${list.categoryID }</td>
						<td>${list.categoryName }</td>
						<td>${list.status }</td>
						<td>
							<a href="#editCategoryModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
							<a href="#deleteCategoryModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
							<input type="hidden" name="id" id="id" value="${list.categoryID}">
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clearfix">
				<div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
				<ul class="pagination">
					<li class="page-item disabled"><a href="#">Previous</a></li>
					<li class="page-item"><a href="#" class="page-link">1</a></li>
					<li class="page-item"><a href="#" class="page-link">2</a></li>
					<li class="page-item active"><a href="#" class="page-link">3</a></li>
					<li class="page-item"><a href="#" class="page-link">4</a></li>
					<li class="page-item"><a href="#" class="page-link">5</a></li>
					<li class="page-item"><a href="#" class="page-link">Next</a></li>
				</ul>
			</div>
		</div>
	</div>        
</div>
<!-- Add Modal HTML -->
<div id="addCategoryModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/admin/category/list?action=create" method="post">
				<div class="modal-header">						
					<h4 class="modal-title">Thêm danh mục</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">					
					<div class="form-group">
						<label>Ten danh muc</label>
						<input type="text" name="categoryName" id="categoryName" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Trang thai</label>
						<input type="text" name="status" id="status" class="form-control" required>
					</div>					
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					<input type="submit" class="btn btn-success" value="Add">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Edit Modal HTML -->
<div id="editCategoryModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/admin/category/list?action=update" method="post">
				<div class="modal-header">						
					<h4 class="modal-title">Sửa danh mục</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">									
					<div class="form-group">
						<label>Ten danh muc</label>
						<input type="text" name="categoryName" id="categoryName" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Trang thai</label>
						<input type="text" name="status" id="status" class="form-control" required>
					</div>					
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					<input type="submit" class="btn btn-info" value="Save">
					<input type="hidden" name="id" id="id">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteCategoryModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/admin/category/list?action=delete" method="post">
				<div class="modal-header">						
					<h4 class="modal-title">Xóa danh mục</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">					
					<p>Are you sure you want to delete these Records?</p>
					<p class="text-warning"><small>This action cannot be undone.</small></p>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					<input type="submit" class="btn btn-danger" value="Delete">
					<input type="hidden" name="id" id="id">
				</div>
			</form>
		</div>
	</div>
</div>


<script>
$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	$('table .delete').on('click', function(){
		var id = $(this).parent().find("#id").val();
		$('#deleteCategoryModal #id').val(id);
	});
	
	$('table .edit').on('click', function(){
		var id = $(this).parent().find("#id").val();
		$.ajax({
			type:'GET',
			url:'${pageContext.request.contextPath}/admin/category/list',
			data:{action:'find', id:id},
			dataType:'json',
			contentType:'application/json',
			success: function(result){
				$('#editCategoryModal #id').val(result.categoryID);
				$('#editCategoryModal #categoryName').val(result.categoryName);
				$('#editCategoryModal #status').val(result.status);
			}
		});
	});
});

</script>