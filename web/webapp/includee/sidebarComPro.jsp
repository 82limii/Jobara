<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <ul class="nav">
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/com/projHome.do">
        <span class="icon-bg"><i class="mdi mdi-calendar menu-icon"></i></span>
        <span class="menu-title">프로젝트 관리</span>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/ftp/com/fileBrowser.do">
        <span class="icon-bg"><i class="mdi mdi-file-document menu-icon"></i></span>
        <span class="menu-title">문서함</span>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/com/projReport.do?what=${param.what}">
        <span class="icon-bg"><i class="mdi mdi-table-large menu-icon"></i></span>
        <span class="menu-title">보고서</span>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/com/projPhoneNum.do?what=${param.what}">
        <span class="icon-bg"><i class="mdi mdi-phone-classic menu-icon"></i></span>
        <span class="menu-title">연락처</span>
      </a>
    </li>
    <li class="nav-item sidebar-user-actions">
      <div class="sidebar-user-menu">
        <a href="${cPath }/indexCom.do" class="nav-link"><i class="mdi mdi-home menu-icon"></i>
          <span class="menu-title">JOBARA</span>
        </a>
      </div>
    </li>
  </ul>
</nav>





