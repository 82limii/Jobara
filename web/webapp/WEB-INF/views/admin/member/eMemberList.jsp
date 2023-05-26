<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- 장인슬 -->

<link rel="stylesheet" href="${cPath }/resources/form/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${cPath }/resources/form/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="${cPath }/resources/form/style.css">

  <div class="container col-12 ">
	<h4 class="mb-5">기업 회원 목록 조회</h4>
		<div id="searchUI" class="form-inline">
			<select name="searchType" class="form-control">
				<option value>전체</option>
				<option value="name">기업명</option>
				<option value="id">기업아이디</option>
			</select>
			<input type="text" name="searchWord"  class="form-control"/>
			<input type="button" class="btn btn-outline-success" value="검색" id="searchBtn"/>
		</div>	
      <div class="table-responsive custom-table-responsive">
        <table class="table custom-table">
		  <thead>
			<tr>
	            <th scope="row">
	            </th>		
				<th>기업아이디</th>
				<th>사업자번호</th>
				<th>기업명</th>
				<th>대표자명</th>
				<th>주소</th>
				<th>연락처</th>
			</tr>
		  </thead>
		  <tbody  id="listBody">
			<c:set var="ememberList" value="${pagingVO.dataList }" />
			<c:choose>
				<c:when test="${empty ememberList }">
					<tr>
						<td colspan="7">조건에 맞는 상품이 없음.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ememberList }" var="ememberList">
						<c:url value="/member/admin/ememberView.do" var="viewURL">
							<c:param name="what" value="${ememberList.ememId }" />
						</c:url>
						<tr data-toggle="collapse" data-target="#collapseOne"
							aria-expanded="false" aria-controls="collapseOne">
							<th scope="row">${ememberList.rnum}</th>
							<td>${ememberList.ememId}</td>
							<td>${ememberList.ememNum}</td>
							<td><a href="${viewURL }">${ememberList.ememNm}</a></td>
							<td>${ememberList.ememCeo}</td>
							<td>${ememberList.ememBadd}</td>
							<td>${ememberList.ememTel}</td>
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


<script src="/resources/form/popper.min.js"></script>
<script src="/resources/form/bootstrap.min.js"></script>
<script src="/resources/form/main.js"></script>
