<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.coteListC {
	float: right;
	width: 90px;  
	height: 45px;
	margin: 5px;
	margin-top: 0;
}
</style>

<c:set var="successIntList" value="${pagingVO.dataList }" />
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
												<c:param name="what" value="${ememId }" />
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
								<h4 class="heading-section">합격 자소서</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-wrap">
									<table class="table myaccordion table-hover" id="accordion">
										<thead>
											<tr>
												<th>순번</th>
												<th>합격시기</th>
												<th>제목</th>
												<th>등록일</th>
											</tr>
										</thead>
										<tbody  id="listBody">
											
												<c:choose>
													<c:when test="${empty successIntList }">
														<tr>
															<td colspan="7">자소서가 없음.</td>
														</tr>
													</c:when>
													<c:otherwise>
														<c:forEach items="${successIntList }" var="successInt">
															<c:url value="/successInt/successIntView.do" var="viewURL">
																<c:param name="what" value="${successInt.sinSn }" />
															</c:url>
															<c:url value="/successInt/successIntDelete.do" var="viewDURL">
																<c:param name="what" value="${successInt.sinSn }" />
															</c:url>
															<tr data-toggle="collapse" data-target="#collapseOne"
																aria-expanded="false" aria-controls="collapseOne">
																<th scope="row">${successInt.rnum}</th>
																<td>
																<a href="${viewURL }">
																${successInt.sinSdate}
																</a>
																</td>
																<td>
																<a href="${viewURL }">
																	${successInt.sinTitle}
																</a>
																</td>
																<td>${successInt.sinDate}</td>
															</tr>
															
														</c:forEach>
													</c:otherwise>
												</c:choose>
										</tbody>
										<tfoot>
											<tr>
											<c:choose>
											<c:when test="${authMember.userType eq 'Pmember'}">
												<button type="button" class="btn btn-outline-success linkBtn" id="coteListC" data-url="${cPath }/review/certiInsert.do?what=${ememId }">등록</button>
											</c:when>
										</c:choose>	
												<td colspan="3">
													<div class="pagingArea">
														${pagingVO.pagingHTMLBS }
													</div>
													
												</td>
											</tr>
										</tfoot>
										
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
<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>