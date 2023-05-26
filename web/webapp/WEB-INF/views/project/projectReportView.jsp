<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <style>
 .btn{
 	float: right;
 	margin: 5px;
 	margin-top: 0;
 }
 #content {
	text-align: center;
 }
 </style>
 <div class="col-lg-12 grid-margin stretch-card">
     <div class="card">
       <div class="card-body">
         <h4 class="card-title">보고서</h4>
         </p>
         <table class="table table-bordered">
             <tr>
               <th class="col-md-1"> 제목 </th>
               <td>${report.repoTitle }</td>
             </tr>
             <tr>
               <th> 작성자 </th>
               <td>${report.pmemId }</td>
             </tr>
             <tr>
               <th> 작성일자 </th>
               <td>${report.repoDate }</td>
             </tr>
             <tr>
               <th colspan="2" id="content"> 내용 </th>
             </tr>
             <tr>
               <td colspan="2">${report.repoContents }</td>
             </tr>
             <tr>
               <th> 첨부파일 </th>
               <td>
           		<c:if test="${not empty attatchList }">
          			<c:forEach items="${attatchList }" var="attatch">
          				<a href="${cPath }/download.do?what=${attatch.attSn}&num=${attatch.attNum}">
          					<i class="fa fa-paperclip" aria-hidden="true"></i>&nbsp;${attatch.attNm }
          				</a>&nbsp;&nbsp;&nbsp;&nbsp;
          			</c:forEach>
          		</c:if>
               </td>
             </tr>
         </table><br>
         <input type="button" class="btn btn-outline-secondary btn-md linkBtn" value="목록"
			data-url="${cPath }/project/projReport.do?what=${param.what }"
			/>
			<c:choose>
				<c:when test="${authMember.pmemId eq report.pmemId }">
					<c:url value="/project/projReportUpdate.do" var="updateURL">
						<c:param name="jobara" value="${report.repoSn }" />
						<c:param name="what" value="${report.proSn }" />
					</c:url>
					<input type="button" class="btn btn-outline-secondary linkBtn" value="수정"
						data-url="${updateURL }"
					 />
				</c:when>
			</c:choose>
        </div>
      </div>
    </div>