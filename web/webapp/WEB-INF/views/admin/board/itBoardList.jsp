<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#insertAsk {
	float: right;
}
#boardTb {
	font-size: 10pt;
}
#searchText {
	width: 300pt;
}
</style>

<div class="container">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card-body">
        <h3>개발자 톡톡</h3><br>
		 <table id="boardTb" class="table table-bordered" style="background-color: #fff">
			<thead>
				<tr>
					<td colspan="5">
						<select class="left">
							<option>게시판 분류</option>
							<option>Ask It</option>
							<option>Share It</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="col-md-2">게시판 분류</th>
					<th class="col-md-1">글번호</th>
					<th class="col-md-4">제목</th>
					<th class="col-md-3">등록일</th>
					<th class="col-md-2">작성자</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Ask It</td>
					<td>1</td>
					<td><a href="${cPath }/board/admin/askItView.do">안녕하세요.</a></td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
				<tr>
					<td>Ask It</td>
					<td>1</td>
					<td>안녕하세요.</td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
				<tr>
					<td>Ask It</td>
					<td>1</td>
					<td>안녕하세요.</td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
				<tr>
					<td>Share It</td>
					<td>1</td>
					<td><a href="${cPath }/board/admin/shareItView.do">안녕하세요.</a></td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
				<tr>
					<td>Share It</td>
					<td>1</td>
					<td>안녕하세요.</td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
				<tr>
					<td>Share It</td>
					<td>1</td>
					<td>안녕하세요.</td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
				<tr>
					<td>Ask It</td>
					<td>1</td>
					<td>안녕하세요.</td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
				<tr>
					<td>Ask It</td>
					<td>1</td>
					<td>안녕하세요.</td>
					<td>2021.12.13</td>
					<td>최인수</td>
				</tr>
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
			</tbody>
			<tfoot>
				<tr>	
					<td colspan="5">
		 				<div class="pagingArea">
		 					${pagingVO.pagingHTMLBS }
						</div>
						<input id="insertAsk" type="button" class="btn btn-outline-success linkBtn" value="등록"
							data-url="${cPath }/board/askItInsert.do"
						/><br>
						<div class="hero__search__form">
							<select>
								<option value>키워드</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="writer">작성자</option>
							</select>
							<input type="text" name="searchWord" id="searchText"/>
							<input type="button" class="btn btn-outline-dark btn" id="searchBtn" value="검색"/>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
   	   </div>
	</div>
</div>