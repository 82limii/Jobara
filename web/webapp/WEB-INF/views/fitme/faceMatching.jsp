<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- <form:form modelAttribute="faceMatching" method="post" enctype="multipart/form-data"> --%>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>마이페이지</span>
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
        <div class="col-lg-9 row">
        	<div class="card-body">
	        	<table class="table table-bordered col-12">
	        	<c:set var="faceMatching" value="${faceMatch.pmemId }" />
					<c:choose>
						<c:when test="${empty faceMatching }">
							<tr>
								<td colspan="7">버튼을 눌러 신청해주세요</td>
							</tr>
							<tr>
			        			<td colspan="3">
			        				<button  class="linkBtn btn btn-primary" data-url="${cPath }/fitme/faceMatchingInsert.do">AI 기업추천 신청하기</button>
		
			        			</td>
			        		</tr>
							</c:when>
						<c:otherwise>
	        		<tr>
	        			<th colspan="3">이미지 매칭</th>
	        		</tr>
	        		<c:choose>
	        			<c:when test="">
	        			</c:when>
	        			<c:otherwise></c:otherwise>
	        		</c:choose>
	        		<tr>
	        			<c:if test="${not empty  faceMatch.pmemPic}">
		        			<td rowspan="5" class="col-7">
		        				<img src="<spring:url value='/image/${faceMatch.pmemPic}'/>" />
		        			</td>
	        			</c:if>
	        			<c:choose>
	        				<c:when test="${not empty faceMatch.faceEnter1 }">
		        					<td class="col-3">${faceMatch.faceEnter1}</td>
				        			<td class="col-2">${faceMatch.facePer1}%</td>
				        		</tr>
				        		<tr>
				        			<td>${faceMatch.faceEnter2}</td>
				        			<td>${faceMatch.facePer2}%</td>
				        		</tr>
				        		<tr>
				        			<td>${faceMatch.faceEnter3}</td>
				        			<td>${faceMatch.facePer3}%</td>
				        		</tr>
				        		<tr>
				        			<td>${faceMatch.faceEnter4}</td>
				        			<td>${faceMatch.facePer4}%</td>
				        		</tr>
				        		<tr>
				        			<td>${faceMatch.faceEnter5}</td>
				        			<td>${faceMatch.facePer5}%</td>
				        		</tr>
				        		
	        				</c:when>
	        				<c:otherwise>
	        						<td>신청후 다음날에 결과를 확인해 주세요</td>
	        					</tr>
	        				</c:otherwise>
	        			</c:choose>
	        		
	        			</c:otherwise>
	        			
					</c:choose>
					
	      
	        	</table>
	        </div>
        </div>
	</div>
</div>
<%-- </form:form>			 --%>
<script>

	
	$("#faceMatching").on("submit", function (e) {
        var value = marginTag(); // return array
        $("#hiddenSkill").val(value); 
        

        $(this).submit();
    });
</script>