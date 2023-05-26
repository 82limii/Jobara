<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<table class="table table-bordered">
					<thead>
						<tr>
							<th colspan="5"><h4>포지션 제안</h4></th>
						</tr>
						<tr>
							<th class="col-1">일련번호</th>
							<th class="col-3">구직글 제목</th>
							<th class="col-2">기업명</th>
							<th class="col-3">공고 제목</th>
							<th class="col-3">메세지</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><a href="${cPath }/searchboard/searchBoardView.do">안녕하세여 블라블라</a></td>
							<td>OO기업</td>
							<td><a href="${cPath }/jobboard/jobBoardView.do">IOS 개발자 채용</a></td>
							<td>OO포지션에 ~~~</td>
						</tr>
						<tr>
							<td>1</td>
							<td><a href="${cPath }/searchboard/searchBoardView.do">안녕하세여 블라블라</a></td>
							<td>OO기업</td>
							<td><a href="${cPath }/jobboard/jobBoardView.do">IOS 개발자 채용</a></td>
							<td>OO포지션에 ~~~</td>
						</tr>
						<tr>
							<td>1</td>
							<td><a href="${cPath }/searchboard/searchBoardView.do">안녕하세여 블라블라</a></td>
							<td>OO기업</td>
							<td><a href="${cPath }/jobboard/jobBoardView.do">IOS 개발자 채용</a></td>
							<td>OO포지션에 ~~~</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<nav aria-label="Page navigation example" class="right">
								  <ul class="pagination">
								    <li class="page-item">
								      <a class="page-link" href="#" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								      </a>
								    </li>
								    <li class="page-item"><a class="page-link" href="#">1</a></li>
								    <li class="page-item"><a class="page-link" href="#">2</a></li>
								    <li class="page-item"><a class="page-link" href="#">3</a></li>
								    <li class="page-item">
								      <a class="page-link" href="#" aria-label="Next">
								        <span aria-hidden="true">&raquo;</span>
								      </a>
								    </li>
								  </ul>
								</nav>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>