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
			<div class="card-body">
				<section class="ftco-section">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-md-6 text-center mb-4">
								<h4 class="heading-section">공고 &nbsp 스크랩</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-wrap">
									<table class="table myaccordion table-hover" id="accordion">
										<thead>
											<tr>							
												<th class="col-md-2">분류</th>
												<th class="col-md-2">기업명</th>
												<th class="col-md-4">제목</th>
												<th class="col-md-2">마감일</th>
												<th>&nbsp;</th>
											</tr>
										</thead>
										<tbody id="listBody">
										<c:set var="scrapPostList" value="${pagingVO.dataList }" />
												<c:choose>
													<c:when test="${empty scrapPostList }">
														<tr>
															<td colspan="7">스크랩 목록이 없음.</td>
														</tr>
													</c:when>
													<c:otherwise>
															<c:forEach items="${scrapPostList }" var="scrapPost">
															<c:url value="/jobboard/jobBoardView.do" var="viewURL">
																<c:param name="what" value="${scrapPost.jobSn }" />
															</c:url>
															<c:url value="/scrap/scrapPostDelete.do" var="deleteURL">
																<c:param name="what" value="${scrapPost.scrapSn }" />
															</c:url>
																<c:if test="${empty scrapPost.probTitle }">
																	<tr data-toggle="collapse" data-target="#collapseOne"
																	aria-expanded="false" aria-controls="collapseOne">
																		<th scope="row">채용</th>
																		<td>${scrapPost.ememNmJ}</td>
																		<td>
																			<a href="${viewURL }">
																				${scrapPost.jobTitle}
																			</a>
																		</td>
																		<td>${scrapPost.jobEndd}</td>
																		<td>
																		<button class="btn btn-primary linkBtn" data-url="${cPath }/scrap/scrapPostDelete.do?what=${scrapPost.scrapSn}">삭제</button>
																		</td>
																	</tr>
																</c:if>
																<c:url value="/proboard/proBoardView.do" var="viewURL">
																<c:param name="what" value="${scrapPost.probSn }" />
															</c:url>
																<c:if test="${empty scrapPost.jobTitle }">
																	<tr data-toggle="collapse" data-target="#collapseOne"
																	aria-expanded="false" aria-controls="collapseOne">
																		<th scope="row">프로젝트</th>
																		<td>${scrapPost.ememNmP}</td>
																		<td>
																			<a href="${viewURL }">
																				${scrapPost.probTitle}
																			</a>
																		</td>
																		<td>${scrapPost.probRendd}</td>
																		<td>
																		<button class="btn btn-primary linkBtn" data-url="${cPath }/scrap/scrapPostDelete.do?what=${scrapPost.scrapSn}">삭제</button>
																		</td>
																	</tr>
																</c:if>
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
				</section>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>