<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
#logo {
	height: 30px;
	width: 30px;
	background-color: gray;
}
#name {
	display: inline-block;
	color: #8e94a9;
}
.right {
	text-align: right;
}
.middle-align {
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>

<nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
  <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
    <a class="navbar-brand brand-logo" href="${cPath }/indexAdmin.do">
    	<img id="logo" src="${cPath }/resources/estateAgency/img/jobaralogo.png">
    	<h4 id="name">JOBARA</h4>
    </a>
  </div>
  <div class="navbar-menu-wrapper d-flex align-items-stretch">
  	<div class="row col-12 middle-align">
       <span class="right">관리자명</span>
	</div>
  </div>
</nav>