<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.form-group label, .form-group input {
	display: inline-block;
}
label {
	text-align: center;
	display: inline-block;
}
i {
	color: #FF6666;
}
#info {
	text-align: right;
}
.center {
	text-align: center;
}
.btnSize {
	width: 85px;
	height: 45px;
	margin: 5px;
}
.left {
	float: left;
}
.largeBtn {
	width: 120px;
	height: 45px;
	margin: 5px;
}
</style>
<div class="container">
	<div class="row">
        <div class="col-lg-12 card-body content-wrapper">
<form class="form-group">
	<div class="card-body">
		<input type="text" id="postTitle" class="form-control-lg col-12" placeholder="이력서 제목을 입력하세요." />
	</div>
	<div class="card-body">
		<div class="row">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-outline-success largeBtn" data-toggle="modal" data-target="#exampleModal">
			  이력서 등록
			</button>
			<h5>선택한 이력서명</h5>
			
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">이력서 선택</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      	<table class="table table-bordered col-12">
			      		<tr>
			      			<td class="col-1"><input type="radio" /></td>
			      			<td class="col-9">이력서 제목</td>
			      			<td class="col-2">등록일</td>
			      		</tr>
			      		<tr>
			      			<td class="col-1"><input type="radio" /></td>
			      			<td class="col-9">이력서 제목</td>
			      			<td class="col-2">등록일</td>
			      		</tr>
			      		<tr>
			      			<td class="col-1"><input type="radio" /></td>
			      			<td class="col-9">이력서 제목</td>
			      			<td class="col-2">등록일</td>
			      		</tr>
			      		<tr>
			      			<td class="col-1"><input type="radio" /></td>
			      			<td class="col-9">이력서 제목</td>
			      			<td class="col-2">등록일</td>
			      		</tr>
			      		<tr>
			      			<td class="col-1"><input type="radio" /></td>
			      			<td class="col-9">이력서 제목</td>
			      			<td class="col-2">등록일</td>
			      		</tr>
			      		<tr>
			      			<td class="col-1"><input type="radio" /></td>
			      			<td class="col-9">이력서 제목</td>
			      			<td class="col-2">등록일</td>
			      		</tr>
			      	</table>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary">등록</button>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
	</div>
</form>
<div class="center">
	<button type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="<c:url value='/enter/jobboard.do'/>">목록</button>
	<button type="submit" class="btn btn-success btnSize">저장</button>
	<button type="reset" class="btn btn-danger btnSize">취소</button>
</div>
		</div>
	</div>
</div>	

<script>
	let modal = $('#myModal').on('shown.bs.modal', function () {
		$('#myInput').trigger('focus')
	});
</script>
