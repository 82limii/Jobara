<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
     <div class="col-lg-12">
		<div class="row">
		    <div class="col-xs-12 col-md-12">
		    <br>
		    <h2 class="text-center">1:1 문의</h2>
			    <div class="table table-responsive">
			        <table class="table">
				        <tr>
				            <th class="success col-lg-3">제목</th>
				            <td colspan="2">${helps.helpsTitle }</td>
				            <th class="success col-lg-2">문의 종류</th>
				            <td colspan="2">${helps.helpsKind }</td>
				        </tr>
				        <tr>
				            <th class="success col-lg-2">등록일</th>
				            <td colspan="2">${helps.helpsDate }</td>
				            <th class="success col-lg-3">첨부파일</th>
				            <td colspan="2">
				            	<c:if test="${not empty attatchList}">
									<c:forEach items="${attatchList }" var="attatch">
										<a href="${cPath }/download.do?what=${attatch.attSn}&num=${attatch.attNum}">
											<i class="fa fa-paperclip" aria-hidden="true"></i>&nbsp;${attatch.attNm }
										</a>&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
								</c:if>
				            </td>
				        </tr>
				        <tr>
				            <th class="success col-lg-3">내용</th>
				            <td colspan="5">${helps.helpsContents }</td>
				        </tr>
<!-- 				        <tr> -->
<!-- 				            <th class="success col-lg-3">답변 첨부파일</th> -->
<!-- 				            <td colspan="5"> -->
<%-- 				            	<c:if test="${not empty attatchList}"> --%>
<%-- 									<c:forEach items="${attatchList }" var="attatch"> --%>
<%-- 										<a href="${cPath }/download.do?what=${attatch.attSn}&num=${attatch.attNum}"> --%>
<%-- 											<i class="fa fa-paperclip" aria-hidden="true"></i>&nbsp;${attatch.attNm } --%>
<!-- 										</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
<%-- 									</c:forEach> --%>
<%-- 								</c:if> --%>
<!-- 				            </td> -->
<!-- 				        </tr> -->
				        <c:if test="${not empty helpsAns }">
				        <tr>
				        	<th>답변</th>
				        	<td colspan="5">
				        		${helpsAns.helpsContents }
				        	</td>
				        </tr>
				        </c:if>
				        <c:if test="${empty helpsAns.helpsContents }">
				        
				        <form:form action="${cPath }/helps/admin/helpsInsert.do?what=${helps.helpsSn }" 
				        			enctype="multipart/form-data" method="post" modelAttribute="helpsAns">

				        <form:input type="hidden" path="helpsId" value="${helps.helpsId }" />

				        <form:input type="hidden" path="helpsId" value="${authMember.adminId }" />

				        <form:input type="hidden" path="helpsTitle" value="RE:${helps.helpsTitle }" />
				        <form:input type="hidden" path="helpsKindCd" value="${helps.helpsKindCd }" />
<!-- 					        <tr> -->
<!-- 					        	<td>파일첨부</td> -->
<!-- 					        	<td colspan="5"> -->
<%-- 					        		<form:input type="file" path="helpsFiles" /> --%>
<%-- 					        		<form:errors path="helpsFiles" element="span" cssClass="error" /> --%>
<!-- 					        	</td> -->
<!-- 					        </tr> -->
					        <tr>
					        	<td colspan="6">
						            <label for="helpsContent"><font color=red><span style="font-size:9pt;">* </span></font>내용</label> <form:errors path="helpsContents" cssclass="error" element="span" />
						            <form:textarea path="helpsContents" class="form-control" id="helpsContent" rows="4"></form:textarea>
						        </td>
					        </tr>
					        <tr>
					        	<td colspan="6">
						            <button type="submit" class="btn btn-primary right">답글쓰기</button>
					    			<input type="button" class="btn btn-success linkBtn right" value="목록" data-url="${cPath }/helps/admin/helpsList.do">
					            </td>
					        </tr>
				        </form:form>
				        </c:if>
				        <c:if test="${not empty helpsAns.helpsContents }">
				        	<tr>
				        		<td colspan="6">
					    			<input type="button" class="btn btn-success linkBtn right" value="목록" data-url="${cPath }/helps/admin/helpsList.do">
					    		</td>
					    	</tr>
					    </c:if>
			        </table>
			    </div>
		    </div>
		</div>
     </div>
</div>
<script type="text/javascript">
	CKEDITOR.replace('helpsContents');
</script>

 