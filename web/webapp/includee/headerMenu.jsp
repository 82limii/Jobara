<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.nav-link {
	text-align: center;
}
.right {
	float: right;
}
#header {
	margin-left: auto;
	margin-right: auto;
}
</style>

<!--/ Nav Star /-->
<nav class="navbar navbar-default navbar-trans navbar-expand-lg">
    <div class="container">
		<table class="col-10" id="header">
			<tr>
				<td rowspan="2" class="col-2">
					<a href="${cPath }/">
						<img src="<c:url value="/resources/estateAgency/img/jobaralogo.png" />" width="100px" height="100px"/>
					</a>
				</td>
				<td class="col-10">
					<div class="hero__search center">
						<div class="hero__search__form center">
							<form action="${cPath }/search/searchResult.do" method="get" class="center">
								<div class="hero__search__categories">
									Company Name
								</div>
								<input type="text" name="what" placeholder="검색어를 입력하세요">
								<button type="submit" class="site-btn btn">SEARCH</button>
							</form>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="col-12 navbar-collapse collapse justify-content-center">
						<ul class="navbar-nav col-12">
							<li class="nav-item col-2">
								<a class="nav-link" href="${cPath }/jobboard/jobBoardList.do">채용 공고</a>
							</li>
							<li class="nav-item col-3">
								<a class="nav-link" href="${cPath }/proboard/proBoardList.do">프로젝트 공고</a>
							</li>
<!-- 							<li class="nav-item col-3"> -->
<%-- 								<a class="nav-link" href="${cPath }/searchboard/searchBoardList.do">구직게시판</a> --%>
<!-- 							</li> -->
							<li class="nav-item dropdown col-3">
								<a class="nav-link dropdown-toggle" href="${cPath }/board/askItList.do" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="false" aria-expanded="true">
									개발자 톡톡
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${cPath }/board/askItList.do">Ask it</a>
								<a class="dropdown-item" href="${cPath }/board/shareItList.do">Share it</a>
								</div>
							</li>
							<li class="nav-item dropdown col-2">
								<a class="nav-link dropdown-toggle" href="${cPath }/cs/faq.do" id="navbarDropdown" role="button" data-toggle="dropdown"
								aria-haspopup="false" aria-expanded="true">
									고객센터
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${cPath }/faq/faqList.do">자주 묻는 질문</a>
								<a class="dropdown-item" href="${cPath }/helps/helpsList.do">1:1 문의</a>
								<a class="dropdown-item" href="${cPath }/notice/noticeList.do">공지사항</a>
								</div>
							</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
</nav>
<!--/ Nav End /-->
