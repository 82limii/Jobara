<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="row">
		<div class="col-lg-3">
			<div class="hero__categories">
				<div class="hero__categories__all">
					<i class="fa fa-bars"></i> <span>마이페이지</span>
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
			<section class="ftco-section">
				<div class="card-body">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-md-6 text-center mb-4">
								<h4 class="heading-section">관심기업</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-wrap">
									<table class="table myaccordion table-hover" id="accordion">
										<thead>
											<tr>
												<th class="col-md-1">순번</th>
												<th class="col-md-2">기업명</th>
												<th class="col-md-3">이메일</th>
												<th class="col-md-3">삭제</th>
											</tr>
										</thead>
										<tbody id="listBody">
										<c:set var="scrapCompanyList" value="${pagingVO.dataList }" />
												<c:choose>
													<c:when test="${empty scrapCompanyList }">
														<tr>
															<td colspan="7">스크랩 목록이 없음.</td>
														</tr>
													</c:when>
													<c:otherwise>
															<c:forEach items="${scrapCompanyList }" var="scrapEnter">
															<c:url value="/info/info.do" var="viewURL">
																<c:param name="what" value="${scrapEnter.ememId }" />
															</c:url>
																<tr data-toggle="collapse" data-target="#collapseOne"
																aria-expanded="false" aria-controls="collapseOne">
																	<th scope="row">${scrapEnter.rnum}</th>
																	<td>
																		<a href="${viewURL }">
																			${scrapEnter.ememNm}
																		</a>
																	</td>
																	<td>${scrapEnter.ememEmail}</td>
																	<td>
																	<button class="btn btn-primary linkBtn" data-url="${cPath }/scrap/scrapEnterDelete.do?what=${scrapEnter.scrapSn}">삭제</button>
																	</td>
																</tr>
															</c:forEach>
												</c:otherwise>
											</c:choose>
										</tbody>
										<tfoot>
											<tr>	
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
				</div>
			</section>
		</div>
	</div>
</div>
<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>