<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
												<c:param name="what" value="${inter.ememId }" />
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
				<div class="row">
					<div class="table-wrap">
						<table class="table myaccordion table-hover" id="accordion">
							<tr>
								<th class="col-md-3">제목</th>
								<td class="center col-md-9">${inter.interTitle}</td>
							</tr>
							<tr>
								<th class="col-md-3">난이도</th>
								<td class="col-md-9">${inter.commNm}</td>
							</tr>
							<tr>
								<th class="col-md-3">면접 날짜</th>
								<td class="col-md-9">${inter.interDate}</td>
							</tr>
							<tr>
								<th class="col-md-3">면접 질문</th>
								<td class="col-md-9">${inter.interQna}</td>
							</tr>
							<tr>
								<th class="col-md-3">면접 답변</th>
								<td class="col-md-9">${inter.interAns}</td>
							</tr>
							<tr>
								<th class="col-md-3">후기</th>
								<td class="col-md-9">${inter.interLater}</td>
							</tr>
						</table>
						<c:choose>
						<c:when test="${authMember.userType eq 'Emember'}">
							<button id="coteM" type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/review/com/cointReviewList.do?what=${inter.ememId }">목록</button>
						</c:when>
						<c:otherwise>
							<button id="coteM" type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/review/cointReviewList.do?what=${inter.ememId }">목록</button>
						</c:otherwise>
					</c:choose>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>