<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.widthSize.col-12 {
	min-width: 100%;
}
</style>

<link rel="stylesheet" href="${cPath }/resources/form/style.css">

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
				<div class="content">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-md-6 text-center mb-4">
								<h2 class="heading-section">이력서 현황</h2>
							</div>
						</div>
						<div class="row">
								<div class="table-responsive custom-table-responsive">
									<table class="table custom-table widthSize col-12">
										<thead>
											<tr>
												<th class="col-2" style="text-align: center;">순번</th>
												<th class="col-6" style="text-align: center;">이력서 제목</th>
												<th class="col-2" style="text-align: center;">등록일</th>
												<th class="col-2" style="text-align: center;"></th>
											</tr>
										</thead>
										<tbody id="listBody">
											<c:set var="resumeList" value="${pagingVO.dataList }" />
											<c:choose>
												<c:when test="${empty resumeList }">
													<tr>
														<td colspan="7">조건에 맞는 상품이 없음.</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${resumeList }" var="resume">
														<c:url value="/myPage/resumeView.do" var="viewURL">
															<c:param name="what" value="${resume.reSn }" />
														</c:url>
														<tr data-toggle="collapse" data-target="#collapseOne"
															aria-expanded="true" aria-controls="collapseOne">
															<th scope="row" style="text-align: center;">${resume.rnum}</th>
															<td>
																<a href="${viewURL }">${resume.reTitle}
																</a>
															</td>
															<td style="text-align: center;">${resume.reDate}</td>
															<td style="text-align: center;"><input id="coteListI" class="btn btn-outline-danger linkBtn"
																type="button" value="삭제" data-url="${cPath }/myPage/resumeDelete.do?what=${resume.reSn}" /></td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>
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
