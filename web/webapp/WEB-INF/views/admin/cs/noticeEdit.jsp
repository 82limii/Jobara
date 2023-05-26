<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 

<form:form modelAttribute="notice" method="post" enctype="multipart/form-data" >
<form:hidden path="noticeSn"/>
<form:hidden path="adminId"/>
<div class="container">
    <div class="col-md-12">
        <h2 class="text-center">공지사항 등록</h2>
        <div class="table table-responsive">
        	<table class="table table-striped">
	          <tr>
	              <td>제목</td>
	              <td colspan="3">
	              	<form:input path="noticeTitle" cssClass="form-control" required="required" />
	              	<form:errors path="noticeTitle" element="span" cssClass="error" />
	              </td>
	          </tr>
	          <tr>
	              <td colspan="4">
	              	<label for="noticeContent"></label> <form:errors path="noticeContents" cssclass="error" element="span" />
        			<form:textarea path="noticeContents" class="form-control" id="noticeContent" rows="4"></form:textarea>
	              </td>
	          </tr>
	           
	          <tr>  
	              <td colspan="4">
	                  <input type="button" class="btn btn-secondary linkBtn right" value="목록" data-url="${cPath }notice/admin/noticeList.do">
	                  <input type="submit" value="등록" class="btn btn-success right">
	              </td>
	          </tr>
   			</table>
        </div>
    </div>
</div>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('noticeContents');
</script>