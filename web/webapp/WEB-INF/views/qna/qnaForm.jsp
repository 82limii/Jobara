<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   

<form:form modelAttribute="qna" method="post" enctype="multipart/form-data" >
<div class="container">
	<div class="row">
		<div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>기업페이지</span>
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
        <div class="col-lg-9">
			<div class="row">
			    <div class="col-md-12">
			    	<br>
			        <h2 class="text-center">Q&A 작성</h2>
			            <div class="table table-responsive">
			                <table class="table table-striped">
					            <tr>
					                <td>제목</td>
					                <td colspan="3">
					                	<form:input path="qnaTitle" cssClass="form-control" required="required" />
					                	<form:errors path="qnaTitle" element="span" cssClass="error" />
					                </td>
					            </tr>
					            <tr>
					                <td>첨부파일</td>
					                <td colspan="3">
					                	<form:input type="file" path="qnaFiles" cssClass="form-control" required="required" />
					                	<form:errors path="qnaFiles" element="span" cssClass="error" />
					                </td>
					            </tr>
					            <tr>
					                <td colspan="4">
					                	<label for="qnaContent"></label> <form:errors path="qnaContents" cssclass="error" element="span" />
             							<form:textarea path="qnaContents" class="form-control" id="qnaContent" rows="4"></form:textarea>
					                </td>
					            </tr>
					            <tr>  
					                <td colspan="4"  class="text-center">
					                    <input type="submit" value="등록" class="btn btn-success right">
					                    <input type="button"  class="btn btn-primary linkBtn right" data-url="${cPath }/qna/com/qnaList.do" value="전체글보기">
					                </td>
					            </tr>
			            	</table>
			            </div>
			    </div>
			</div>
        </div>
	</div>
</div>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('qnaContents');
</script>