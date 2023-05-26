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
<form:form modelAttribute="shareIt" method="post" enctype="multipart/form-data">
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
          		  <h4 class="card-title">Share It 등록</h4>
	            <form class="forms-sample">
	              <div class="form-group">
					<label>글번호</label>
					<form:input path="rebSn" cssClass="form-control" readonly="true" />
			      </div>		            
	              <div class="form-group">
					<label>부모글번호</label>
					<form:input path="rebSn2" cssClass="form-control" readonly="true" />
			      </div>		            
	              <div class="form-group">
	                <label for="askItBoardTitle">제목</label>
	                <form:input path="rebTitle" type="text" class="form-control" id="askItBoardTitle" />
	              </div>
	             <div class="form-group">
	                <label for="rebContents">내용</label>
	                <form:textarea path="rebContents" class="form-control" id="exampleTextarea1" rows="4" />
	              </div>
	              <button class="btn btn-light">취소</button>
	              <button type="submit" class="btn btn-primary mr-2">저장</button>
	            </form>
            </div>
        </div>
    </div>
</div>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('rebContents');
	
</script>
