<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<style>
.detail td, .detail th {
	text-align: left;
}
.center{
	text-align: center;
}
</style>
<div class="container">
	<div class="col-lg-12 row">
		<div class="card-body">
			<div class="card border-dark col-12">
				<br>
	        	<div class="col-12">
	        		<h4>${resume.reTitle }</h4>
	        	</div>
	        	<br>	
	        	        	
	        	<div class="col-12">
	        		<h5>인적사항</h5>
	        		<c:set value="${resume.member }" var="member" />
	        		<table class="col-12">
	        			<tr>
	        				<td rowspan="5" class="col-2">
	        					<img src="<spring:url value='/image/${member.pmemPic }'/>" style="width:auto; height:140px;"/>
	        				</td>
	        				<td colspan="2">
	        					<h6 style="display: inline;"><b>${member.pmemNm }</b></h6>&nbsp;${member.pmemSexNm }, ${member.pmemBir }
	        				</td>
	        			</tr>
	        			<tr>
	        				<td class="col-2"><h6>연락처</h6></td>
	        				<td class="col-6">${member.pmemEmail }</td>
	        			</tr>
	        			<tr>
	        				<td class="col-2"><h6>연락처</h6></td>
	        				<td class="col-6">${member.pmemTel }</td>
	        			</tr>
	        			<tr>
	        				<td class="col-2"><h6>주소</h6></td>
	        				<td class="col-6">${member.pmemBadd }, ${member.pmemDadd }</td>
	        			</tr>
	        			<tr>
	        				<td><h6>기술스택</h6></td>
	        				<td>${resume.reStack }</td>
	        			</tr>
	        		</table>
	        	</div>
	        	<br>
	        	
	        	<c:if test="${not empty resume.educationList }">
	        	<div class="col-12">
		        	<h5>학력</h5>
		        	<hr>
		        	<br>
		        	<table class="col-12 table">
		        	<c:forEach items="${resume.educationList }" var="education">
		        		<tr>
		        			<td rowspan="2" class="col-3">
		        				${education.eduSdate }&nbsp; ~ &nbsp;${education.eduEdate }
		        				<br>
		        				${education.enterNm }&nbsp;/&nbsp;${education.stateNm }
		        			</td>
		        			<td class='col-9'>
		        				<b>${education.eduName }</b>&nbsp;&nbsp;&nbsp;&nbsp;${education.eduMajor }
		        			</td>
		        		</tr>
		        		<tr>
		        			<td>
			        		<c:if test="${not empty education.eduScore }">
		        				학점&nbsp;&nbsp;&nbsp;&nbsp;${education.eduScore }
			        		</c:if>
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</table>
	        	</div>
	        	<br>
	        	</c:if>
	        	
	        	<c:if test="${not empty resume.careerList }">
	        	<div class="col-12">
		        	<h5>경력</h5>
		        	<hr>
		        	<br>
		        	<table class="col-12 table">
		        	<c:forEach items="${resume.careerList }" var="career">
		        		<tr>
		        			<td rowspan="4" class="col-3">
		        				${career.carStartd }&nbsp; ~ &nbsp;${career.carEndd }
		        			</td>
		        			<td class='col-9'>
		        				<b>${career.carEnter }</b>&nbsp;&nbsp;&nbsp;&nbsp;${career.carDepartment}
		        				<br>
		        				${career.carPosi }&nbsp;/&nbsp;${career.carDept }</td>
		        		</tr>
		        		<tr>
		        			<td>연봉&nbsp;&nbsp;&nbsp;&nbsp;${career.carPay }만원</td>
		        		</tr>
		        		<tr>
		        			<td>${career.carTask }</td>
		        		</tr>
		        		<tr>
		        			<td>${career.carDescription }</td>
		        		</tr>
		        	</c:forEach>
		        	</table>
	        	</div>
	        	<br>
	        	</c:if>
	        	
	        	<c:if test="${not empty resume.activityList }">
	        	<div class="col-12">
		        	<h5>대외활동</h5>
		        	<hr>
		        	<br>
		        	<table class="col-12 table">
		        	<c:forEach items="${resume.activityList }" var="activity">
		        		<tr>
		        			<td rowspan="3" class="col-3">
		        				${activity.actStartd }&nbsp; ~ &nbsp;${activity.actEndd }
		        			</td>
		        			<td class='col-9'>
		        				<b>${activity.actNm }</b>&nbsp;&nbsp;&nbsp;&nbsp;${activity.actDiv}
		        				<br>
		        			</td>
		        		</tr>
		        		<tr>
		        			<td>
		        				${activity.actContents }
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</table>
	        	</div>
	        	<br>
	        	</c:if>
	        	
	        	<c:if test="${not empty resume.schoolList }">
	        	<div class="col-12">
		        	<h5>교육이수</h5>
		        	<hr>
		        	<br>
		        	<table class="col-12 table">
		        	<c:forEach items="${resume.schoolList }" var="school">
		        		<tr>
		        			<td rowspan="4" class="col-3">
		        				${school.schStartd }&nbsp; ~ &nbsp;${school.schEndd }
		        			</td>
		        			<td class='col-9'>
		        				<b>${school.schNm }</b>&nbsp;&nbsp;&nbsp;&nbsp;${school.schInt }
		        				<br>
		        			</td>
		        		</tr>
		        		<tr>
		        			<td>
		        				${school.schContents }
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</table>
	        	</div>
	        	<br>
	        	</c:if>
	        	
	        	<c:if test="${not empty resume.languageList }">
	        	<div class="col-12">
		        	<h5>어학</h5>
		        	<hr>
		        	<br>
		        	<table class="col-12 table">
		        	<c:forEach items="${resume.languageList }" var="language">
		        		<tr>
		        			<td class="col-2">
			        			<b>${language.langNm }</b>
			        			<br>${language.divNm }
		        			</td>
		        			<td class="col-10">
		        				<c:if test="${not empty language.langTest }">
		        				<b>${language.langTest }&nbsp;&nbsp;&nbsp;&nbsp;${language.langScore }</b>&nbsp;&nbsp;&nbsp;&nbsp;${language.langDate }
		        				</c:if>
		        				<br>
		        				<c:if test="${not empty language.langCon }">
		        				${language.langCon }
		        				</c:if>
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</table>
	        	</div>
	        	<br>
	        	</c:if>
	        	
	        	<c:if test="${not empty resume.licenseList }">
	        	<div class="col-12">
		        	<h5>자격증</h5>
		        	<hr>
		        	<br>
		        	<table class="col-12 table">
		        	<c:forEach items="${resume.licenseList }" var="license">
		        		<tr>
		        			<td class="col-2">${license.licDate }</td>
		        			<td class="col-10">
		        				<b>${license.licNm }</b>&nbsp;&nbsp;&nbsp;&nbsp;${license.licInt }
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</table>
	        	</div>
	        	<br>
	        	</c:if>
	        	
	        	<div class="col-12">
		        	<h5>자기소개서</h5>
		        	<hr>
		        	<br>
		        	${resume.reInt }
	        	</div>
	        	<br>

	        	<div class="col-12">
		        	<h5>희망근무조건</h5>
		        	<hr>
		        	<br>
		        	<c:set value="${resume.condi }" var="condi" />
		        	<table class="col-12 table">
		        		<tr>
		        			<th class="col-2">고용형태</th>
		        			<td class="col-10">${condi.shapeNm }</td>
		        		</tr>
		        		<tr>
		        			<th>희망근무지</th>
		        			<td>${condi.placeNm }</td>
		        		</tr>
		        		<tr>
		        			<th>희망직무</th>
		        			<td>${condi.dutyNm }</td>
		        		</tr>
		        	</table>
	        	</div>
	        	<br>
	        	
			</div>
		</div>
	</div>
</div>