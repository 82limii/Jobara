<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <ul class="nav">
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#manage-member" aria-expanded="false" aria-controls="manage-member">
        <span class="icon-bg"><i class="mdi mdi-account menu-icon"></i></span>
        <span class="menu-title">회원관리</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="manage-member">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> <a class="nav-link" href="${cPath }/member/admin/pmemberList.do">개인회원 관리</a></li>
          <li class="nav-item"> <a class="nav-link" href="${cPath }/member/admin/ememberList.do">기업회원 관리</a></li>
        </ul>
      </div>
    </li>	
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#manage-board" aria-expanded="false" aria-controls="manage-board">
        <span class="icon-bg"><i class="mdi mdi-book-open menu-icon"></i></span>
        <span class="menu-title">게시판 관리</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="manage-board">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> <a class="nav-link" href="${cPath }/board/admin/jobBoardList.do">채용공고 관리</a></li>
          <li class="nav-item"> <a class="nav-link" href="${cPath }/board/admin/proBoardList.do">프로젝트 공고 관리</a></li>
          <li class="nav-item"> <a class="nav-link" href="${cPath }/board/admin/itBoardList.do">개발자 톡톡 관리</a></li>
        </ul>
      </div>
    </li>	
    <li class="nav-item">
      <a class="nav-link" data-toggle="collapse" href="#cs-center" aria-expanded="false" aria-controls="cs-center">
        <span class="icon-bg"><i class="mdi mdi-comment-account menu-icon"></i></span>
        <span class="menu-title">고객센터</span>
        <i class="menu-arrow"></i>
      </a>
      <div class="collapse" id="cs-center">
        <ul class="nav flex-column sub-menu">
          <li class="nav-item"> <a class="nav-link" href="${cPath }/faq/admin/faqList.do">자주묻는 질문</a></li>
          <li class="nav-item"> <a class="nav-link" href="${cPath }/helps/admin/helpsList.do">1:1문의</a></li>
          <li class="nav-item"> <a class="nav-link" href="${cPath }/notice/admin/noticeList.do">공지사항</a></li>
        </ul>
      </div>
    </li>	
    
    <!--  -->
    
    <li class="nav-item sidebar-user-actions">
      <div class="sidebar-user-menu">
        <a href="${cPath }/index.do" class="nav-link"><i class="mdi mdi-home menu-icon"></i>
          <span class="menu-title">JOBARA</span>
        </a>
      </div>
    </li>
    <li class="nav-item sidebar-user-actions">
      <div class="sidebar-user-menu">
        <a href="${cPath }/admin/logout.do" class="nav-link">
        <i class="mdi mdi-logout menu-icon"></i>
          <span class="menu-title">Log Out</span></a>
      </div>
    </li>
  </ul>
</nav>