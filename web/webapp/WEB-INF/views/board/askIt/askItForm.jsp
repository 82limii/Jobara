<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
.btn {
	float: right;
	margin: 5px;
	margin-top: 0;
}
</style>
<form:form modelAttribute="askIt" method="post" enctype="multipart/form-data">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
		            <div class="hero__categories">
		                <div class="hero__categories__all">
		                    <i class="fa fa-bars"></i>
		                    <span>개발자 톡톡</span>
		                </div>
							<ul>
								<c:forEach items="${menuList }" var="menu">
									<c:choose>
										<c:when test="${not empty menu.menuUpper }">
											<br>
											<h5>${menu.menuUpper }</h5>
										</c:when>
										<c:otherwise>
											<li><a href="${cPath }/${menu.menuUrl }">${menu.menuName }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
	    			 </div>
		        </div>
		        <div class="col-9 grid-margin stretch-card">
	              <div class="card-body">
	          		  <h4 class="card-title">Ask It 게시글 등록</h4>
			              <div class="form-group">
			                <label for="askItBoardTitle">제목</label>
			                <form:input path="boardTitle" type="text" class="form-control" id="askItBoardTitle" />
			                <form:errors path="boardTitle" element="span" cssClass="error" />
			              </div>
			              
			              <div class="form-group">
			                <label for="boardContents">내용</label>
			                <form:textarea path="boardContents" class="form-control" id="boardContents" rows="4" />
			              	<form:errors path="boardContents" element="span" cssClass="error" />
			              </div>
			              
			              <div class="form-group">
			                <label>[첨부파일]</label>
			                <input type="file" name="askItFiles" class="form-control"/>
			              </div>
		          		 <div>
				           	<form:input type="hidden" path="boardSn"/>
				         </div>
		              <button class="btn btn-light">취소</button>
		              <button type="submit" class="btn btn-primary mr-2">저장</button>
	            </div>
	        </div>
	    </div>
	</div>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('boardContents');
	
</script>
