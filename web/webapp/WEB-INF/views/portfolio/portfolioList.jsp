<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
#portListI {
	float: right;
}

#portListD {
	float: right;

}
</style>
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
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-6 text-center mb-4">
							<h3 class="heading-section">포트폴리오 조회</h3>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="table-wrap">
								<table class="table myaccordion table-hover" id="accordion">
									<thead>
										<tr>
											<th>순번</th>
											<th>파일명</th>
											<th>파일크기</th>
											<th>생성일자</th>
											<th></th>
										</tr>
									</thead>
									<tbody  id="listBody">
												<c:choose>
													<c:when test="${empty portfolioList }">
														<tr>
															<td colspan="7">조건에 맞는 상품이 없음.</td>
														</tr>
													</c:when>
													<c:otherwise>
														<c:forEach items="${portfolioList }" var="portfolio">
															
															<tr data-toggle="collapse" data-target="#collapseOne"
																aria-expanded="false" aria-controls="collapseOne">
																<th scope="row">${portfolio.rnum}</th>
																<c:set var="portfolioList" value="${pagingVO.dataList }" />
																	<c:set value="${portfolio.attatch }" var="attatch" />
																<td>
																	<a href="${cPath }/download.do?what=${attatch.attSn}&num=1">
																		${attatch.attNm}
																	</a>
																</td>
																<td>${attatch.attFancy}</td>
																<td>${attatch.attDate}</td>
																<td>
																<button type="button" class="btn btn-outline-danger linkBtn" data-url="${cPath }/myPage/portfolioDelete.do?what=${portfolio.portSn}">삭제</button>
																</td>
															</tr>
															
														</c:forEach>
													</c:otherwise>
												</c:choose>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="5">
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
			</section>
		</div>
	</div>
</div>