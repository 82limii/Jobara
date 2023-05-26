<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<div class="col-lg-3">
			<div class="hero__categories">
				<div class="hero__categories__all">
					<i class="fa fa-bars"></i> <span>기업페이지</span>
				</div>
				<ul>
					<c:forEach items="${menuList }" var="menu">
						<c:choose>
							<c:when test="${not empty menu.menuUpper }">
								<br>
								<h5>${menu.menuUpper }</h5>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty menu.menuFlag }">
										<c:if test="${menu.menuFlag eq 'ememId' }">
											<c:url value="${menu.menuUrl }" var="viewURL">
												<c:param name="what" value="${successInt.ememId }" />
											</c:url>
											<li><a href="${cPath }/${viewURL }">${menu.menuName }</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<li><a href="${cPath }/${menu.menuUrl }">${menu.menuName }</a>
									</c:otherwise>
								</c:choose>
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
								<h5 class="heading-section">합격 자소서</h5>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-wrap">
									<table class="table myaccordion table-hover" id="accordion">
										<tr>
											<th class="col-md-3">제목</th>
											<td class="col-md-9">${successInt.sinTitle}</td>
										</tr>
										<tr>
											<th>작성자</th>
											<td>${successInt.pmemId}</td>
										</tr>
										<tr>
											<th>합격시기</th>
											<td>${successInt.sinSdate}</td>
										</tr>
										<tr>
											<th>작성일자</th>
											<td>${successInt.sinDate}</td>
										</tr>
										<tr>
											<th>자기 소개서</th>
											<td>${successInt.sinConten}</td>
										</tr>
									</table>
									<c:choose>
										<c:when test="${authMember.userType eq 'Emember'}">
											<button id="coteM" type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/successInt/com/successIntList.do?what=${successInt.ememId }">목록</button>
										</c:when>
										<c:otherwise>
											<button id="coteM" type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/successInt/successIntList.do?what=${successInt.ememId }">목록</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
</div>