<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
	<title><tiles:getAsString name="title" /></title>

	<tiles:insertAttribute name="preScript" />
	<!-- plugins:css -->
    <link rel="stylesheet" href="${cPath }/resources/connect-plus-1.0.0/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${cPath }/resources/connect-plus-1.0.0/assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="${cPath }/resources/connect-plus-1.0.0/assets/vendors/css/vendor.bundle.base.css">
    <!-- Plugin css for this page -->
    <link rel="stylesheet" href="${cPath }/resources/connect-plus-1.0.0/assets/vendors/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${cPath }/resources/connect-plus-1.0.0/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <!-- Layout styles -->
    <link rel="stylesheet" href="${cPath }/resources/connect-plus-1.0.0/assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="${cPath }/resources/connect-plus-1.0.0/assets/images/favicon.png" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css" />
    
    <!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">
	
	<link href='${cPath }/resources/packages/core/main.css' rel='stylesheet' />
	<link href='${cPath }/resources/packages/daygrid/main.css' rel='stylesheet' />
	<script src="<c:url value='/resources/packages/core/main.js'/>"></script>
	<script src="<c:url value='/resources/packages/interaction/main.js'/>"></script>
	<script src="<c:url value='/resources/packages/daygrid/main.js'/>"></script>
<style>
.noDisplay {
	display:none;
}
header {
	margin: auto;
	width: 100%;
	position: relative;
	display: block;
}
body {
	padding-top: 64px;
}
main {
	float:none; 
	margin:0 auto;
}
#sidebar {
	height: 100%;
}
</style>
</head>
<body>
<c:set value="${requestScope['javax.servlet.forward.servlet_path'] }" var="servletPath"/>
	<div class="container-scroller">
		<header>
				<tiles:insertAttribute name="headerMenuPro" />
		</header>
		<div class="container-fluid page-body-wrapper">
			<sidebar>
				<tiles:insertAttribute name="sidebarCommonPro" />
			</sidebar>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<main role="main" class="col-lg-12">
							<tiles:insertAttribute name="main" />
						</main>
					</div>
				</div>
			</div>
		</div>
	</div>
	
    <!-- plugins:js -->
    <script src="${cPath }/resources/connect-plus-1.0.0/assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="${cPath }/resources/connect-plus-1.0.0/assets/vendors/chart.js/Chart.min.js"></script>
    <script src="${cPath }/resources/connect-plus-1.0.0/assets/vendors/jquery-circle-progress/js/circle-progress.min.js"></script>
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="${cPath }/resources/connect-plus-1.0.0/assets/js/off-canvas.js"></script>
    <script src="${cPath }/resources/connect-plus-1.0.0/assets/js/hoverable-collapse.js"></script>
    <script src="${cPath }/resources/connect-plus-1.0.0/assets/js/misc.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="${cPath }/resources/connect-plus-1.0.0/assets/js/dashboard.js"></script>
    <!-- End custom js for this page -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
	<tiles:insertAttribute name="postScript" />
</body>
</html>