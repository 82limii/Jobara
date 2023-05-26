<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="${cPath }/resources/form/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${cPath }/resources/form/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="${cPath }/resources/form/style.css">

<style>
.table th, .table td {
	display: table-cell;
	vertical-align: middle;
}
</style>

<div class="container">
	<div class="col-lg-12">
       <div id="searchUI" class="form-inline">
			<select name="proOrder" class="custom-select">
				<option value>등록일순</option>
				<option value="proRend">마감일순</option>
				<option value="proRstart">시작일순</option>
			</select>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="ememNm" placeholder="기업명을 입력하세요." class="form-control col-4"/>
			&nbsp;&nbsp;
			<input type="button" class="btn btn-outline-success" value="검색" id="searchBtn"/>
		</div>	
		<br>
		<table class="table table-bordered" style="background-color: #fff">
			<tbody id="listBody">
				<tr>
					<th class="col-1" style="text-align: center;">순번</th>
					<th class="col-2" style="text-align: center;">기업명</th>
					<th class="col-7" style="text-align: center;">공고명 및 지원조건</th>
					<th class="col-2" style="text-align: center;">공고마감일/<br>프로젝트 기간</th>
				</tr>
				<c:set  var="probBoardList" value="${pagingVO.dataList }"/>
				<c:choose>
					<c:when test="${empty probBoardList }">
						<tr>
							<td colspan="4">조건에 맞는 공고를 찾을 수 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${probBoardList }" var="probBoard">
							<c:url value="/board/admin/proBoardView.do" var="viewURL">
								<c:param name="what" value="${probBoard.probSn }" />
							</c:url>
							<c:url value="${cPath }/member/admin/ememberView.do" var="retrieveURL">
								<c:param name="what" value="${probBoard.ememId }" />
							</c:url>
							<tr>
								<td style="text-align: center;">${probBoard.rnum }</td>
								<td><a href="retrieveURL">${probBoard.ememNm }</a></td>
								<td>
									<a href="${viewURL }">${probBoard.probTitle }</a>
									<hr>
									<h6>
										${probBoard.probEdu }&nbsp;&nbsp;&nbsp;
										|&nbsp;&nbsp;&nbsp;${probBoard.probCar }&nbsp;&nbsp;&nbsp;
										|&nbsp;&nbsp;&nbsp;${probBoard.probLoc }
										<br>
										${probBoard.probDept }&nbsp;&nbsp;|&nbsp;&nbsp;${probBoard.probSkill }
									</h6>
								</td>
								<td style="text-align: center;">
									${probBoard.probRendd }
									<hr>
									${probBoard.probStartd }
									<br>
									~&nbsp;${probBoard.probEndd }
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

<form id="searchForm">
	<input type="text" name="ememNm" />
	<input type="text" name="proOrder" />
	<input type="text" name="page" />
</form>
<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>
<script type="text/javascript">
	let ememNm = $("[name=ememNm]").val("${pagingVO.detailSearch.ememNm}");
	let proOrder = $("[name=proOrder]").val("${pagingVO.detailSearch.proOrder}");
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let searchUI = $("#searchUI");
	let searchFrom = $("#searchForm").paging({
		listBody:listBody
		, pagingArea:pagingArea
		, searchUI:searchUI
		, searchBtn:"#searchBtn"
	});
</script>