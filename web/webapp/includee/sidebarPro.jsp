<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <ul class="nav">
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/projHome.do">
        <span class="icon-bg"><i class="mdi mdi-calendar menu-icon"></i></span>
        <span class="menu-title">프로젝트 관리</span>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/projTaskList.do?what=${param.what}">
        <span class="icon-bg"><i class="mdi mdi-chart-gantt menu-icon"></i></span>
        <span class="menu-title">프로젝트 작업</span>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/projNotice.do?what=${param.what}">
        <span class="icon-bg"><i class="mdi mdi-format-list-bulleted menu-icon"></i></span>
        <span class="menu-title">전달사항</span>
      </a>
    </li>
  
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/projReport.do?what=${param.what}">
        <span class="icon-bg"><i class="mdi mdi-table-large menu-icon"></i></span>
        <span class="menu-title">보고서</span>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="javascript:zoom()">
        <span class="icon-bg"><i class="mdi mdi-camcorder menu-icon"></i></span>
        <span class="menu-title">화상회의</span>
      </a>
    </li>
<!--     <li class="nav-item"> -->
<%--       <a class="nav-link" href="${cPath }/project/projMemList.do?what=${param.what}"> --%>
<!--         <span class="icon-bg"><i class="mdi mdi-account-multiple menu-icon"></i></span> -->
<!--         <span class="menu-title">구성원</span> -->
<!--       </a> -->
<!--     </li> -->
    <li class="nav-item">
      <a class="nav-link" href="${cPath }/project/projPhoneNum.do?what=${param.what}">
        <span class="icon-bg"><i class="mdi mdi-phone-classic menu-icon"></i></span>
        <span class="menu-title">연락처</span>
      </a>
    </li>
    <li class="nav-item sidebar-user-actions">
      <div class="sidebar-user-menu">
        <a href="${cPath }/index.do" class="nav-link"><i class="mdi mdi-home menu-icon"></i>
          <span class="menu-title">JOBARA</span>
        </a>
      </div>
    </li>
  </ul>
</nav>

<script>
	function zoom(){
		var popupWidth = 900;
		var popupHeight = 200;
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		var pop= 'location=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY;
		
		window.open("${cPath }/zoom/zoomIndex.do", "meeting", pop);
	}
</script>








