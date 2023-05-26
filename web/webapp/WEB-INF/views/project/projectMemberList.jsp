<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/DataTable/datatables.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/DataTable/datatables.min.js"></script>
<style>
#addBtn, #removeBtn{
	float: right;
	margin: 5px;
	margin-top: 0;
}
#delBtn, #saveBtn {
	float: right;
	margin: 5px;
	margin-top: 0;
}
#mailBtn{
	width: 10pt;
	height: 10pt;
}
th{
	text-align: center;
}
#staticBackdropLabel{
	color: black;
}
.trVal:hover {
	background-color: lightgray;
}
</style>
<div class="col-lg-12 grid-margin stretch-card">
<div class="card">
    <div class="card-body">
      <h3 class="card-title">구성원 관리</h3>
       <c:set value="${requestScope['javax.servlet.forward.servlet_path'] }" var="servletPath"/>
     	  <c:choose>
      		<c:when test="${fn:contains(servletPath,'com') }">
			      <button id="removeBtn" type="button" class="btn btn-outline-secondary">
			      <i class="mdi mdi-minus"></i>삭제</button>
			      <button id="addBtn" type="button" class="btn btn-outline-secondary"
			      	data-toggle="modal" data-target="#staticBackdrop">
	    			  <i class="mdi mdi-plus"></i>추가</button>
   		    </c:when>
       </c:choose>
      
      <!-- Modal -->
	<form class="forms-sample" method="post" action="${cPath }/project/projMemInsert.do" id="addMemForm">
<%-- 		<input type="hidden" name="what" value="${param.what}" /> --%>
		<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">구성원 추가</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body col-lg-12 grid-margin stretch-card">
				<select class="custom-select col-lg-10" name="applyMember">
						<option value>프로젝트 공고목록</option>
						<c:forEach items="${proBoardList }" var="proBoard">
							<option value="${proBoard.probSn }">${proBoard.probTitle }</option>
						</c:forEach>
					</select>
					<input type="button" id="applyMemBtn" class="btn btn-outline-success" value="조회" />
		      </div>
		      <div>
			      <p style="color: red; font-size: 1px; margin-bottom: 0;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;※구성원으로 등록할  프로젝트를 선택해주십시오.</p>
		      </div>
		      <div class="modal-body col-lg-12 grid-margin stretch-card">
				<p style="margin-top: 10px; color: black;">프로젝트 선택</p>&nbsp;&nbsp;
				 <select class="custom-select col-lg-9" name="projName">
				 	<option value>PROJECT 선택</option>
				 	<c:forEach items="${projListCom }" var="projList">
				 		<option value="${projList.proSn }">${projList.proName }</option>
				 	</c:forEach>
				 </select>		      	
		      </div>
		      <div>
		      <table class="table table-bordered" id="applyMemTb">
				<thead>
					<tr>
						<th class="col-1"></th>
						<th class="col-2">아이디</th>
						<th class="col-2">이름</th>
						<th class="col-4">Email</th>
						<th class="col-3">연락처</th>
					</tr>
					</thead>
					<tbody id="applyMemBody">
					
					</tbody>
				</table>
		      </div>
		      <div class="modal-footer">
		        
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" onclick="addMember()">등록</button>
      </div>
    </div>
  </div>
</div>
</form>
	 <!-- modal end -->
      
      
      
      <table class="table col-lg-12" id="memTb" style="text-align: center;">
        <thead>
          <tr>
            <th>프로젝트명</th>
            <th>역할</th>
            <th>이름</th>
            <th>이메일</th>
            <th>연락처</th>
            <th>소속업체</th>
          </tr>
        </thead>
      </table>
      
    </div>
  </div>
</div>
<script type="text/javascript">

	$.fn.dataTable.ext.errMode = 'none';
    $('#memTb').DataTable({
    	language : {
            url : "//cdn.datatables.net/plug-ins/1.11.4/i18n/ko.json"
        },
    	"ajax" : {
    		"url" : "${cPath}/project/projMemList.do?what="+"${param.what}",
    		"dataSrc" : "dataList"
    	},
    	"columns" : [
			{"data":"proName"},    		
			{"data":"memPosition"},    		
			{"data":"pmemNm"},    		
			{"data":"memEmail"},    		
			{"data":"memTel"},    		
			{"data":"memFree"}	
    	]
    });


var counter = 0;
    $("#applyMemBtn").click(function(){
    	let proBoard = $("select[name=applyMember]").val();
    	let projName = $("select[name=projName]").val();
    	console.log(proBoard);
    	if(proBoard != null) {
    		$.ajax({
    			url : "${cPath}/project/com/proBoardApplyMem.do?what="+proBoard,
    			method : "get",
    			dataType : "json",
    			success : function(resp) {
    				let result = resp.applyMemList;
    				let trTag = "";
    				$.each(result, function(i, applyMem){
    					trTag += "<tr class='trVal'>";
    					trTag += "<td><input type='checkbox' class='checkBoxs' name='checkboxName' value='" + applyMem.pmemId + "' /></td>";
    					trTag += "<td>" + applyMem.pmemId + "</td>";
    					trTag += "<td>" + applyMem.pmemNm + "</td>";
    					trTag += "<td>" + applyMem.pmemEmail + "</td>";
    					trTag += "<td>" + applyMem.pmemTel + "</td>";
    					trTag += "<input type='hidden' name=projectMemberList["+counter+"].pmemId id='pmemId"+counter+"' value='"+applyMem.pmemId+"'/>";
    					trTag += "<input type='hidden' name=projectMemberList["+counter+"].pmemNm id='pmemNm"+counter+"' value='"+applyMem.pmemNm+"'/>";
    					trTag += "<input type='hidden' name=projectMemberList["+counter+"].memEmail id='pmemEmail"+counter+"' value='"+applyMem.pmemEmail+"'/>";
    					trTag += "<input type='hidden' name=projectMemberList["+counter+"].memTel id='pmemTel"+counter+"' value='"+applyMem.pmemTel+"'/>";
    					trTag += "</tr>"
    				$("#pmemId"+counter).val(applyMem.pmemId);
    				$("#pmemNm"+counter).val(applyMem.pmemNm);
    				$("#pmemEmail"+counter).val(applyMem.pmemEmail);
    				$("#pmemTel"+counter).val(applyMem.pmemTel);
    				$("#applyMemBody").empty();
    				$("#applyMemBody").append(trTag);
    				counter++;
    				});
    			let proSnTag ="";
    			proSnTag += "<input type='hidden' name='proSn' id='proSn' value='"+projName+"'/>"; 
   				$("#applyMemBody").append(proSnTag);
   				console.log(proSnTag);
   				let result1 = $("#proSn").val();
    			console.log("12312: " + result1);
    				
    			},
    			error : function(xhr, status, error) {
    				console.log(xhr);
    				console.log(status);
    				console.log(error);
    			}
    		});
    	
    	} 
    })
  function addMember(){
//     let applyMemBody = $("#applyMemBody");
//     var checkbox = $("input[name=checkboxName]:checked");
//     var rst1 = "";
//     var rst2 = "";
//     var rst3 = "";
//     var rst4 = "";
//     checkbox.each(function(i){
//     	var tr = checkbox.parent().parent().eq(i);
//     	var td = tr.children();
//     	rst1 = td.eq(1).text();
//     	rst2 = td.eq(2).text();
//     	rst3 = td.eq(3).text();
//     	rst4 = td.eq(4).text();
//     });
    $("#addMemForm").submit();    
// 	  let checkBoxs = applyMemBody.find(".checkBoxs");
// 	   for (var i = 0; i < checkBoxs.length; i++) {
// 	      if ($(checkBoxs[i]).prop("checked")) {
// 	    	  console.log(checkBoxs[i]);
// 	    	  $("#addMemForm").submit();
// 	      }
// 	   }

 }  
 
</script>








