<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<style>
.btn{
	float: right;
	margin: 5px;
	margin-top: 0;
}
.fa {
	color: red;
}
</style>
 <form:form modelAttribute="report" method="post" class="forms-sample" enctype="multipart/form-data">
    <div class="col-12 grid-margin stretch-card">
       <div class="card">
         <div class="card-body">
           <h4 class="card-title">보고서 등록</h4>
             <div class="form-group">
               <label for="reportTitle"><font color=red><span style="font-size:9pt;">* </span></font>제목</label>
               <form:input path="repoTitle" type="text" class="form-control" id="reportTitle" />
             </div>
             
             <div class="form-group">
               <label for="exampleTextarea1"><font color=red><span style="font-size:9pt;">* </span></font>내용</label>
               <form:textarea path="repoContents" class="form-control" id="exampleTextarea1" rows="4" />
               <form:errors path="repoContents" element="span" cssClass="error"/>
             </div>
             
              <div class="form-group">
            <label for="repoAttId">첨부파일</label>
           	 <input type="file" name="repoFiles" class="form-control"/>
           </div>
           <form:errors path="repoFiles" element="span" cssClass="error" />
             <div>
           		<form:input path="repoSn" cssClass="noDisplay"/>
           </div>
         </div>
          <div>
             <button class="btn btn-light">취소</button>
             <button type="submit" class="btn btn-primary mr-2">저장</button>
          </div>
         </div>
       </div>
  </form:form>
  <script type="text/javascript">
  	CKEDITOR.replace('repoContents');
  </script>