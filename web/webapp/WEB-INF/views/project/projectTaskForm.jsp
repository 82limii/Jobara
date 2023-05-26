<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<style>
.btn{
	float: right;
	margin: 5px;
	margin-top: 0;
}
#inputStartDate, #inputEndDate {
	width: 350pt;
}
#d1{
	width: 50%;
	margin-right: 0;
	float: left;
}
#d2{
	width: 50%;
	float: right;
}
.form-control {
	line-height: 40px;
}
.fa {
	color: red;
}
</style>
 <form:form modelAttribute="task" method="post" class="forms-sample">
   <div class="col-12 grid-margin stretch-card">
     <div class="card">
       <div class="card-body">
         <h4 class="card-title">작업 등록</h4>
           <div class="form-group">
             <label for="taskName"><font color=red><span style="font-size:9pt;">* </span></font>업무명</label>
             <form:errors path="protNm" element="span" cssClass="error" />
             <form:input path="protNm" class="form-control" id="taskName" placeholder="업무명" />
           </div>
           <div class="form-group">
             <label for="proMemberList"><font color=red><span style="font-size:9pt;">* </span></font>담당자</label>
			<form:errors path="protManager" element="span" cssClass="error" />
             <form:select path="protManager" class="custom-select" id="proMemberList">
				<option value>선택</option>
				<c:forEach items="${memList }" var="proMem">
					<option value="${proMem.pmemNm }" 
						<c:if test="${proMem.pmemNm eq task.protManager}">selected</c:if>
					>${proMem.pmemNm }</option>
					<form:options items="${protManager }" />
				</c:forEach>
             </form:select>
           </div>
           <div class="form-group">
	           <div class="form-group" id="d1">
	             <label for="inputStartDate"><font color=red><span style="font-size:9pt;">* </span></font>시작일</label>
	             <form:errors path="protStartd" element="span" cssClass="error" />
		         <form:input path="protStartd" type="date" class="form-control col-md-11" id="inputStartDate" />
	           </div>
	             
			   <div class="form-group" id="d2">
		         <label for="inputEndDate"><font color=red><span style="font-size:9pt;">* </span></font>마감일</label>
	             <form:errors path="protEndd" element="span" cssClass="error" />
			     <form:input path="protEndd" type="date" class="form-control col-md-11" id="inputEndDate" />
			   </div>
           </div><br>
           
           <div class="form-group">
             <label for="importantList"><font color=red><span style="font-size:9pt;">* </span></font>중요도</label>
             <form:errors path="protImprtCd" element="span" cssClass="error" />
             <form:select path="protImprtCd" class="custom-select" id="importantList" >
               <option value>선택</option>
      			<c:forEach items="${impList }" var="imp">
      				<option value="${imp.commCd }"<c:if test="${imp.commNm eq task.protImprt }">selected</c:if>
      				>${imp.commNm }</option>
      				<form:options items="${protImprtCd }" />
      			</c:forEach>
             </form:select>
           </div>
           <div class="form-group">
            <label for="progressList">진척도</label>
             <form:errors path="protProgress" element="span" cssClass="error" />
              <form:select path="protProgress" class="custom-select" id="progressList">
               <c:forEach var="prog" begin="0" end="100" step="10">
               		<option value="${prog }" <c:if test="${prog eq task.protProgress }">selected</c:if>
               		>${prog }%</options>
               </c:forEach>
             </form:select>
           </div>
           <div class="form-group">
           	<label for="stateList"><font color=red><span style="font-size:9pt;">* </span></font>진행상태</label>
             <form:errors path="protState" element="span" cssClass="error" />
           	<form:select path="protState" class="custom-select" id="stateList" >
			 <option value>선택</option>
			 <c:forEach items="${ingList }" var="ing">
			 	<option value="${ing.commCd }" <c:if test="${ing.commNm eq task.protStateNm }">selected</c:if> >${ing.commNm }</option>
			 	<form:options items="${protState }" />
			 </c:forEach>           	
           	</form:select>
           </div>
           <div>
           	<form:input path="protSn" cssClass="noDisplay"/>
           </div>
           
           <br>
           <button class="btn btn-light">취소</button>
           <button type="submit" class="btn btn-primary mr-2">저장</button>
       </div>
     </div>
   </div>
 </form:form>
 
 <script type="text/javascript">
 	
 </script>
