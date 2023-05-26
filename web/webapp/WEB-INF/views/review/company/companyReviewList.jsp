<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="comReviewList" value="${pagingVO.dataList }" />
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
												<c:param name="what" value="${ememId}" />
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
				<section class="ftco-section">
						<div class="card-body">
					<div class="container">
						<div class="row justify-content-center">
						
							<div class="col-md-6 text-center mb-4">
								<h4 class="heading-section">기업리뷰 조회</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-wrap">
									<table class="table myaccordion table-hover" id="accordion">
										<thead>
											<tr>
												<th class="col-md-1">순번</th>
												<th class="col-md-2">재직상태</th>
												<th>직무</th>
												<th>별점</th>
												<th>등록일</th>												
											</tr>
										</thead>
										<tbody id="listBody">
											
											<c:choose>
												<c:when test="${empty comReviewList }">
													<tr>
														<td colspan="7">조건에 맞는 리뷰가 없음.</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${comReviewList }" var="comReview">
														<tr >
															<th scope="row">${comReview.reviewSn}</th>
															<td>${comReview.stae}</td>
															<td>${comReview.dept}</td>
															<c:if test="${comReview.rat eq '1점'}">
															<td><i class="fa fa-star" aria-hidden="true"></i></td>
															</c:if>
															<c:if test="${comReview.rat eq '2점'}">
															<td><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i></td>
															</c:if>
															<c:if test="${comReview.rat eq '3점'}">
															<td><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i></td>
															</c:if>
															<c:if test="${comReview.rat eq '4점'}">
															<td><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i></td>
															</c:if>
															<c:if test="${comReview.rat eq '5점'}">
															<td><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i></td>
															</c:if>
															<td>${comReview.reviewDate}</td>
														</tr>
														<tr>
															<td class="col-md-2"  >
															<p></p>
															</td>
															<td colspan="6"  >
															<div class="col-12 row">
																<div class="col-12">장점 : ${comReview.reviewAdv }</div>
															</div><hr>	
															<div class="col-12 row">
																<div class="col-12">단점 : ${comReview.reviewDisadv }</div>
															</div>
															<hr>
															<div>${comReview.reviewComment}</div>
															</td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</tbody>
										<tfoot>
											
											<c:choose>
											<c:when test="${authMember.userType eq 'Pmember'}">
												<button type="button" class="btn btn-outline-success linkBtn" id="coteListC" data-url="${cPath }/review/companyCertiInsert.do?what=${ememId }">등록</button>
											</c:when>
										</c:choose>
										
											<tr>
											<td colspan="3">
												<div class="pagingArea">
													${pagingVO.pagingHTMLBS }
												</div>
											</td>                                                                                                                                                                                  	</tr>
										</tfoot>
									</table>
								</div>
							</div>
							</div>
						</div>
					</div>
				
				</section>
			</div>    
		</div>
</div>
<form id="searchForm"> 
	<input type="hidden" name="page" placeholder="page"/>
</form>
<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>