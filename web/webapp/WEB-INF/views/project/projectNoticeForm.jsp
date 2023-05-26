<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<style>
.btn{
	float: right;
	margin: 5px;
	margin-top: 0;
}
i {
	color: red;
}
</style>
<form:form modelAttribute="notice" method="post" class="forms-sample" enctype="multipart/form-data">
   <div class="col-12 grid-margin stretch-card">
     <div class="card">
       <div class="card-body">
         <h4 class="card-title">전달 사항 등록</h4>
           <div class="form-group">
             <label for="noticeTitle"><font color=red><span style="font-size:9pt;">* </span></font>제목</label>
             <form:input path="notiTitle" class="form-control" id="noticeTitle" placeholder="업무명" />
             <form:errors path="notiTitle" class="error" element="span"/>
           </div>
           
           <div class="form-group">
             <label for="noticeImportance"><font color=red><span style="font-size:9pt;">* </span></font>중요도</label>
             <form:select path="notiImportance" class="custom-select" id="noticeImportance" >
               <option value>중요도</option>
               <c:forEach items="${impList }" var="imp">
					<option value="${imp['commNm'] }" <c:if test="${imp.commNm eq notice.notiImportance }">selected</c:if>>${imp.commNm }</option>   			
	               <form:options items="${notiImportance }"/>
               </c:forEach>
             </form:select>
           </div>
           
           <div class="form-group">
             <label for="noticeContent"><font color=red><span style="font-size:9pt;">* </span></font>내용</label> <form:errors path="notiContents" cssclass="error" element="span" />
             <form:textarea path="notiContents" class="form-control" id="noticeContent" rows="4"></form:textarea>
           </div>
           
           <div class="form-group">
            <label for="notiAttId">첨부파일</label>
           	 <input type="file" name="notiFiles" class="form-control"/>
           </div>
           <form:errors path="notiFiles" element="span" cssClass="error" />
           <div>
           	<form:input path="notiSn" cssClass="noDisplay" />
           </div>
           
           <input type="button" class="btn btn-light" data-url="${cPath }/project/projNotice.do?what=${param.what}" value="취소"/>
           <button type="submit" class="btn btn-primary mr-2" >저장</button>
       
       </div>
     </div>
   </div>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace('notiContents');
</script>
