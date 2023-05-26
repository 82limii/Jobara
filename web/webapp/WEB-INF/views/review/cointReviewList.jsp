<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>

#coteListI {
	float: right;
	width: 90px;
	height: 45px;
	margin: 5px;
	margin-top: 0;
}

.coteSear {
	width: 320px;
	height: 50px;
	border: 1px solid #ebebeb;
	position: relative;
	float: left;
}
	
#coteListC {
	float: right;
	width: 90px;  
	height: 45px;
	margin: 5px;
	margin-top: 0;
}
</style>
<c:set var="cointList" value="${pagingVO.dataList }" />
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
			<section class="ftco-section">
				<div class="card-body">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-md-6 text-center mb-4">
								<h4 class="heading-section">코테/면접 리뷰 조회</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table-wrap">
									<table class="table myaccordion table-hover" id="accordion">
										<thead>
											<tr>
												<th>순번</th>
												<th>분류</th>
												<th>리뷰 제목</th>
												<th>등록일</th>
											</tr>
										</thead>
										<tbody id="listBody">
											<c:choose>
												<c:when test="${empty cointList }">
													<tr>
														<td colspan="7">조건에 맞는 리뷰가 없음.</td>
													</tr>
												</c:when>
												<c:otherwise>
															<c:forEach items="${cointList }" var="coint">
																<c:url value="/review/coteReviewView.do" var="viewCURL">
																<c:param name="what" value="${coint.coteSn }" />
																</c:url>
																<c:url value="/review/interReviewView.do" var="viewIURL">
																<c:param name="what" value="${coint.coteSn }" />
																</c:url>
																<c:if test="${coint.cote eq 'cote'}">
																	<tr data-toggle="collapse" data-target="#collapseOne"
																	aria-expanded="false" aria-controls="collapseOne">
																		<th scope="row">${coint.rnum}</th>
																		<td>코테</td>
																		<td>
																		<a href="${viewCURL }">
																			${coint.coteTitle}
																		</a>
																		</td>
																		<td>${coint.coteDate}</td>
																	</tr>
																</c:if>
																<c:if test="${coint.cote eq 'inter'}">
																	<tr data-toggle="collapse" data-target="#collapseOne"
																	aria-expanded="false" aria-controls="collapseOne">
																		<th scope="row">${coint.rnum}</th>
																		<td>면접</td>
																		<td>
																			<a href="${viewIURL }">
																				${coint.coteTitle}</a>
																		</td>
																		<td>
																			<a href="${viewIURL }">
																				${coint.coteDate}
																			</a>	
																		</td>
																	</tr>
																</c:if>
															</c:forEach>
												</c:otherwise>
											</c:choose>
										</tbody>
										<tfoot>
										<c:choose>
											<c:when test="${authMember.userType eq 'Pmember'}">
												
												<button type="button" class="btn btn-outline-success linkBtn" id="coteListC" data-url="${cPath }/review/certiInsert.do?what=${ememId }">등록</button>
											</c:when>
										</c:choose>
												
												
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
<script type="text/javascript">
	$.fn.paging = function(obj) {
		let success = obj.success;
		let listBody = obj.listBody;
		let pagingArea = obj.pagingArea;
		let searchUI = obj.searchUI;
		let searchBtn = obj.searchBtn;
		let searchForm = this;
		if (success) {
			this.on("submit", function(event) {
				event.preventDefault();
				let url = this.action;
				let method = this.method;
				let data = $(this).serialize();
				$.ajax({
					url : url,
					method : method,
					data : data,
					dataType : "json", // Accept, Content-Type
					success : success
				});
				return false;
			}).submit();
		}

		searchUI.on("click", searchBtn, function() {
			let inputs = searchUI.find(":input[name]");
			$.each(inputs, function(index, input) {
				let value = $(input).val();
				let name = $(input).attr("name");
				searchForm.find("[name=" + name + "]").val(value);
			});
			searchForm.submit();
		});

		pagingArea.on("click", "a", function(event) {
			event.preventDefault();
			let page = $(this).data("page");
			searchForm[0].page.value = page;
			searchForm.submit();
			return false;
		});

		return this;
	}
</script>


