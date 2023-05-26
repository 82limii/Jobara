<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form modelAttribute="faq" method="post" enctype="multipart/form-data" >
	<div class="container">
		<div class="col-lg-12">
			<div class="row">
			    <div class="col-md-12">
			        <div class="card-body"><h3 class="text-center">자주묻는 질문</h3></div>
			            <div class="table table-responsive">
		                   <table class="table table-striped">
				            <tr>
				                <td>제목</td>
				                <td colspan="3">
				                	<form:input path="faqTitle" cssClass="form-control" required="required" />
				                	<form:errors path="faqTitle" element="span" cssClass="error" />
				                </td>
				            </tr>
				            <tr>
				                <td><label for="faqList">질문유형코드</label></td>
				                <td colspan="3">
				                	<form:select path="combSn" class="custom-select" id="faqList">
						               <c:forEach items="${faqList }" var="faqs">
						               		<option value="${faqs.commCd }" <c:if test="${faqs.commNm eq faq.combSn }">selected</c:if> >${faqs.commNm }</options>
						               		<form:options items="${combSn }" />
						               </c:forEach>
						            </form:select>
				                </td>
				            </tr>
				            <tr>
				                <td>FAQ 내용</td>
				                <td colspan="3">
				                	<label for="faqContent"></label> <form:errors path="faqContents" cssclass="error" element="span" />
             						<form:textarea path="faqContents" class="form-control" id="faqContent" rows="4"></form:textarea>
				                </td>
				            </tr>
				            <tr>
				                <td>FAQ 답변</td>
				                <td colspan="3">
				                	<label for="faqReqs"></label> <form:errors path="faqReq" cssclass="error" element="span" />
             						<form:textarea path="faqReq" class="form-control" id="faqReqs" rows="4"></form:textarea>
				                </td>
				            </tr>
				             
				            <tr>  
				                <td colspan="4"  class="text-center">
				                    <input type="button" class="btn btn-secondary linkBtn right" value="목록" data-url="${cPath }/faq/admin/faqList.do">
				                    <input type="submit" value="등록" class="btn btn-success right">
				                </td>
				            </tr>
		          		</table>
		            </div>
			    </div>
			</div>
		</div>
	</div>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('faqContents');
	CKEDITOR.replace('faqReq');
</script>
