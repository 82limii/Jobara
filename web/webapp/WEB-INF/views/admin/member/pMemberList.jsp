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

<div class="container col-12">
<h4 class="mb-5">개인 회원 목록 조회</h4>
	<div id="searchUI" class="form-inline">
		<select name="searchType" class="form-control">
			<option value>전체</option>
			<option value="name">회원명</option>
			<option value="id">회원아이디</option>
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
					<th>회원아이디</th>
					<th>회원명</th>
					<th>연락처</th>
					<th>주소1</th>
					<th>이메일</th>
					<th>생년월일</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set var="pmemberList" value="${pagingVO.dataList }" />
				<c:choose>
					<c:when test="${empty pmemberList }">
						<tr>
							<td colspan="7">조건에 맞는 상품이 없음.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${pmemberList }" var="pmemberList">
							<c:url value="/member/admin/pmemberView.do" var="viewURL">
								<c:param name="what" value="${pmemberList.pmemId }" />
							</c:url>
							<tr data-toggle="collapse" data-target="#collapseOne"
								aria-expanded="false" aria-controls="collapseOne">
								<th scope="row">${pmemberList.rnum}</th>
								<td>${pmemberList.pmemId}</td>
								<td><a href="${viewURL }">${pmemberList.pmemNm}</a></td>
								<td>${pmemberList.pmemTel}</td>
								<td>${pmemberList.pmemBadd}</td>
								<td>${pmemberList.pmemEmail}</td>
								<td>${pmemberList.pmemBir}</td>
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
