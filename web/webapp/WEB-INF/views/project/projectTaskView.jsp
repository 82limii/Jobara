<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <style>
th {
	text-align: center;
}
.btnControl{
	float: right;
	margin: 5px;
	margin-top: 0;
}
 </style>
 <div class="col-lg-12 grid-margin stretch-card">
   <div class="card">
     <div class="card-body">
       <h4 class="card-title">업무 내용</h4>
       <table class="table table-bordered col-lg-12">
       	<tr>
       		<th class="col-lg-3">업무명</th>
       		<td class="col-lg-9">${task.protNm }</td>
       	</tr>
       	<tr>
       		<th>등록일</th>
       		<td>${task.protDate }</td>
       	</tr>
       	<tr>
       		<th>작성자</th>
       		<td>${task.pmemNm }</td>
       	</tr>
       	<tr>
       		<th>담당자</th>
       		<td>${task.protManager }</td>
       	</tr>
       	<tr>
       		<th>시작일</th>
       		<td>${task.protStartd }</td>
       	</tr>
       	<tr>
       		<th>마감일</th>
       		<td>${task.protEndd }</td>
       	</tr>
       	<tr>
       		<th>중요도</th>
       		<td>${task.protImprt }</td>
       	</tr>
       	<tr>
       		<th>진척도</th>
       		<td>${task.protProgress }%</td>
       	</tr>
       	<tr>
       		<th>진행상태</th>
       		<td>${task.protStateNm }</td>
       	</tr>
       </table><br>
        <input type="button" class="btn btn-outline-secondary btn-md linkBtn" value="목록"
			data-url="${cPath }/project/projTaskList.do?what=${param.what}"
			/>
		<c:choose>
			<c:when test="${authMember.pmemId eq task.pmemId }">
				<input type="button" class="btn btn-outline-danger btn-md linkBtn btnControl" value="삭제"
					data-toggle="modal" data-target="#deleteModal"
				/>
				<c:url value="/project/projTaskUpdate.do" var="updateURL">
					<c:param name="jobara" value="${task.protSn }"/>
					<c:param name="what" value="${task.proSn }"/>
				</c:url>
				<input type="button" class="btn btn-outline-secondary btn-md linkBtn btnControl" value="수정"
					data-url="${updateURL }"
				/>
			</c:when>
		</c:choose>
		
		  <!-- 게시글 삭제 확인 Modal -->
				<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<form id="deleteForm" method="post" action='<c:url value="/project/projTaskDelete.do?what=${param.what }"/>'>
						<input type="hidden" name="protSn" value="${task.protSn }" />
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					   		<p>삭제하시겠습니까?</p>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					        <button type="submit" class="btn btn-danger" id="saveChange">삭제</button>
					      </div>
					    </div>
					</form>
				  </div>
				</div>
			<!-- Modal end -->
		
     </div>
   </div>
 </div>