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
#search1 {
	width : 400px;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-lg-3">
	            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>기업페이지</span>
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
	       <div class="col-lg-9 grid-margin stretch-card">
             <div class="card-body">
		        <h3>스크랩</h3><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-2">이름</th>
							<th class="col-md-3">제목</th>
							<th class="col-md-2">학력</th>
							<th class="col-md-2">경력</th>
							<th class="col-md-3">기술스택</th>
					</thead>
					<tbody>
						<tr>
							<td>전XX</td>
							<td><a href="${cPath }/searchboard/com/searchBoardView.do">구직글 제목</a></td>
							<td>대졸</td>
							<td>3년차</td>
							<td>기이술</td>
						</tr>
						<tr>
							<td>김XX</td>
							<td>구직글 제목</td>
							<td>고졸</td>
							<td>6년차</td>
							<td>기이술</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>	
							<td colspan="5">
				 				<div class="pagingArea">
				 					${pagingVO.pagingHTMLBS }
								</div>
								<div class="hero__search__form">
									<select>
										<option value>키워드</option>
										<option value="title">제목</option>
										<option value="content">학력</option>
										<option value="writer">경력</option>
									</select>
									<input id="search1" type="text" name="searchWord"/>
									<input type="button" class="site-btn btn" value="검색"/>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
        </div>
	</div>
	</div>
</div>