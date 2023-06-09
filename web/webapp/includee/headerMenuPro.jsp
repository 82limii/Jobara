<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
#name {
	color: #8e94a9;
}
#docIcon:hover {
	background: lightgray;
}
</style>

<nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
  <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
    <c:set value="${requestScope['javax.servlet.forward.servlet_path'] }" var="servletPath"/>
   <c:choose>
      	<c:when test="${fn:contains(servletPath,'com') }">
		    <a class="navbar-brand brand-logo" href="${cPath }/project/com/projHome.do">
		    	<h4 id="name">PROJECT</h4>
		    </a>
		</c:when>
		<c:otherwise>
		    <a class="navbar-brand brand-logo" href="${cPath }/project/projHome.do">
		    	<h4 id="name">PROJECT</h4>
		    </a>
		</c:otherwise>
  </c:choose>
    <a class="navbar-brand brand-logo-mini" href="index.html"><img src="${cPath }/resources/connect-plus-1.0.0/assets/images/logo-mini.svg" alt="logo" /></a>
  </div>
  <div class="navbar-menu-wrapper d-flex align-items-stretch">
    <div class="search-field d-none d-xl-block">
      <form class="d-flex align-items-center h-100" action="#">
      </form>
    </div>
    <ul class="navbar-nav navbar-nav-right">
      <li class="nav-item" id="docIcon">
	      <a class="nav-link" href="${cPath }/ftp/fileBrowser.do">
	        <span class="icon-bg"><i class="mdi mdi-file-document menu-icon"></i></span>
	        <span class="menu-title">&nbsp;문서함</span>&nbsp;&nbsp;&nbsp;
	      </a>
	  </li>
      <li class="nav-item nav-profile dropdown">
        <c:choose>
        	<c:when test="${fn:contains(servletPath,'com') }">
		        <a class="nav-link dropdown-toggle" id="profileDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
			          <div class="nav-profile-img">
			            <img src="<spring:url value='/image/${authMember.ememPic }'/>" alt="">
			          </div>
			          <div class="nav-profile-text">
			            <p class="mb-1 text-black">${authMember.ememNm }</p>
			          </div>
		        </a>
		        <div class="dropdown-menu navbar-dropdown dropdown-menu-right p-0 border-0 font-size-sm" aria-labelledby="profileDropdown" data-x-placement="bottom-end">
		          <div class="p-3 text-center bg-primary">
		            <img class="img-avatar img-avatar48 img-avatar-thumb" src="<spring:url value='/image/${authMember.ememPic }'/>" alt="">
		          </div>
		          <div class="p-2">
		          <form id="logoutForm" method="post" action="${cPath }/login/logout.do">
		            <a id="logoutTag" class="dropdown-item py-1 d-flex align-items-center justify-content-between" href="#">
		              <span>Log Out</span>
		              <i class="mdi mdi-logout ml-1"></i>
		            </a>
		            </form>
		             <script type="text/javascript">
				      	$("#logoutTag").on("click", function(event){
				      		event.preventDefault();
				      		$("#logoutForm").trigger("submit");
							return false;
				      	});
				      </script>
		          </div>
		        </div>
        	</c:when>
        	<c:otherwise>
		        <a class="nav-link dropdown-toggle" id="profileDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
			          <div class="nav-profile-img">
			            <img src="<spring:url value='/image/${authMember.pmemPic }'/>" alt="">
			          </div>
			          <div class="nav-profile-text">
			            <p class="mb-1 text-black">${authMember.pmemNm }</p>
			          </div>
		        </a>
		        <div class="dropdown-menu navbar-dropdown dropdown-menu-right p-0 border-0 font-size-sm" aria-labelledby="profileDropdown" data-x-placement="bottom-end">
		          <div class="p-3 text-center bg-primary">
		            <img class="img-avatar img-avatar48 img-avatar-thumb" src="<spring:url value='/image/${authMember.pmemPic }'/>" alt="">
		          </div>
		          <div class="p-2">
		          <form id="logoutForm" method="post" action="${cPath }/login/logout.do">
		            <a id="logoutTag" class="dropdown-item py-1 d-flex align-items-center justify-content-between" href="#">
		              <span>Log Out</span>
		              <i class="mdi mdi-logout ml-1"></i>
		            </a>
		            </form>
		             <script type="text/javascript">
				      	$("#logoutTag").on("click", function(event){
				      		event.preventDefault();
				      		$("#logoutForm").trigger("submit");
							return false;
				      	});
				      </script>
		          </div>
		        </div>
        	</c:otherwise>
        </c:choose>
      </li>
       
    </ul>
    <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
      <span class="mdi mdi-menu"></span>
    </button>
  </div>
</nav>