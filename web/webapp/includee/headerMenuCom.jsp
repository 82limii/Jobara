<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--/ Nav Star /-->
<nav class="navbar navbar-default navbar-trans navbar-expand-lg">
    <div class="container">
		<table class="col-12">
			<tr>
				<td rowspan="2" class="col-2">
					<a href="${cPath }/indexCom.do">
						<img src="<c:url value="/resources/estateAgency/img/jobaralogo.png" />" width="150px" height="150px"/>
					</a>
				</td>
				<td class="col-10">
				</td>
			</tr>
			<tr>
				<td>
					<div class="navbar-collapse collapse justify-content-center">
						<ul class="navbar-nav col-12">
							<li class="nav-item dropdown col-2">
								<a class="nav-link dropdown-toggle" href="${cPath }/jobboard/com/jobBoardList.do" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="false" aria-expanded="true">
									채용공고
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${cPath }/jobboard/com/jobBoardList.do">전체공고</a>
								<a class="dropdown-item" href="${cPath }/jobboard/com/myJobBoard.do">내 공고 관리</a>
								<a class="dropdown-item" href="${cPath }/jobboard/com/jobBoardInsert.do">공고 등록</a>
								</div>
							</li>
							<li class="nav-item dropdown col-3">
								<a class="nav-link dropdown-toggle" href="${cPath }/proboard/com/proBoardList.do" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="false" aria-expanded="true">
									프로젝트 공고
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${cPath }/proboard/com/proBoardList.do">전체공고</a>
								<a class="dropdown-item" href="${cPath }/proboard/com/myProBoard.do">내 공고 관리</a>
								<a class="dropdown-item" href="${cPath }/proboard/com/proBoardInsert.do">공고 등록</a>
								</div>
							</li>
<!-- 							<li class="nav-item col-2"> -->
<%-- 								<a class="nav-link" href="${cPath }/searchboard/com/searchBoardList.do">구직게시판</a> --%>
<!-- 							</li> -->
							<li class="nav-item dropdown col-3">
								<a class="nav-link dropdown-toggle" href="${cPath }/info/com/info.do" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="false" aria-expanded="true">
									기업페이지
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${cPath }/info/com/info.do">기업정보</a>
								<a class="dropdown-item" href="${cPath }/qna/com/qnaList.do">Q&A</a>
								<a class="dropdown-item" href="${cPath }/incumbent/com/incumbentList.do">재직자 관리</a>
								<a class="dropdown-item" href="${cPath }/applicant/com/jobApplicantList.do">지원자 관리</a>
								<a class="dropdown-item" href="${cPath }/contact/com/contactList.do">연락처 관리</a>
								<a class="dropdown-item" href="${cPath }/scrap/com/scrapSearch.do">스크랩</a>
								</div>
							</li>
							<li class="nav-item dropdown col-2">
								<a class="nav-link dropdown-toggle" href="${cPath }/faq/com/faqList.do" id="navbarDropdown" role="button" data-toggle="dropdown"
								aria-haspopup="false" aria-expanded="true">
									고객센터
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${cPath }/faq/com/faqList.do">자주 묻는 질문</a>
								<a class="dropdown-item" href="${cPath }/helps/com/helpsList.do">1:1 문의</a>
								<a class="dropdown-item" href="${cPath }/notice/com/noticeList.do">공지사항</a>
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