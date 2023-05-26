<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#coteListC {
	float: right;
	width: 90px;
	height: 45px;
	margin: 5px;
	margin-top: 0;
}
#coteListI {
	float: right;
	width: 90px;
	height: 45px;
	margin: 5px;
	margin-top: 0;
}
</style>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>기업페이지</span>
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
        <div class="col-lg-9">
        <div class="card-body">
		<section class="ftco-section">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-6 text-center mb-4">
						<h4 class="heading-section">합격스펙 조회</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="table-wrap">
							<table class="table myaccordion table-hover" id="accordion">
							  <thead>
							    <tr>
							      <th class="col-md-1">학력</th>
							      <th class="col-md-1">학점</th>
							      <th class="col-md-1">경력</th>
							      <th class="col-md-2">기술스택</th>
							    </tr>
							  </thead>
							  <tbody>
							    
							    <tr data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"
							    onclick="location.href='/myCompany/specDiviView.do'">
							      <th scope="row">대졸</th>
							      <td>3.5</td>
							      <td>5년차</td>
							      <td>무슨무슨 기술</td>
							    </tr>
							    <tr data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"
							    onclick="location.href='/myCompany/specDiviView.do'">
							      <th scope="row">대졸</th>
							      <td>3.5</td>
							      <td>5년차</td>
							      <td>무슨무슨 기술</td>
							    </tr>
							    <tr data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"
							    onclick="location.href='/myCompany/specDiviView.do'">
							      <th scope="row">대졸</th>
							      <td>3.5</td>
							      <td>5년차</td>
							      <td>무슨무슨 기술</td>
							    </tr>
							  </tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
		</div>
		</div>
    </div>
</div>



