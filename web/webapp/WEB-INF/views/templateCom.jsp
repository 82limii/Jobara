<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
    integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
    crossorigin="anonymous" />

	<!-- Favicons -->
	<link href="${cPath }/resources/estateAgency/img/favicon.png" rel="icon">
	<link href="${cPath }/resources/estateAgency/img/apple-touch-icon.png" rel="apple-touch-icon">
	
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
	
	<!-- Bootstrap CSS File -->
	<link href="${cPath }/resources/estateAgency/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Libraries CSS Files -->
	<link href="${cPath }/resources/estateAgency/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="${cPath }/resources/estateAgency/lib/animate/animate.min.css" rel="stylesheet">
	<link href="${cPath }/resources/estateAgency/lib/ionicons/css/ionicons.min.css" rel="stylesheet">
	<link href="${cPath }/resources/estateAgency/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
	
	<!-- Main Stylesheet File -->
	<link href="${cPath }/resources/estateAgency/css/style.css" rel="stylesheet">
	
	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
	
	<!-- Css Styles -->
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/font-awesome.min.css" type="text/css">
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/elegant-icons.css" type="text/css">
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/nice-select.css" type="text/css">
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/jquery-ui.min.css" type="text/css">
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/owl.carousel.min.css" type="text/css">
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/slicknav.min.css" type="text/css">
	<link rel="stylesheet" href="${cPath }/resources/ogani-master/css/style.css" type="text/css">
	
	<!-- Project -->
	
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">
	
	<link href='${cPath }/resources/packages/core/main.css' rel='stylesheet' />
	<link href='${cPath }/resources/packages/daygrid/main.css' rel='stylesheet' />
	<script src="<c:url value='/resources/packages/core/main.js'/>"></script>
	<script src="<c:url value='/resources/packages/interaction/main.js'/>"></script>
	<script src="<c:url value='/resources/packages/daygrid/main.js'/>"></script>

	
<style type="text/css"> 
header {  
	margin: auto;
	width: 100%;
	align-items: center;
	position: relative;
	z-index: 1;
}

main {
	float:none; 
	margin:0 auto;
}

.center {
	float: none;
	margin: 0 auto;
}

footer {
	width: 100%;
	margin: auto;
	padding: 0;
	height: auto;
	bottom: 0;
}
.table td, .table th, .table thead th {
	text-align: center;
	vertical-align: middle;
}
li {
	list-style: none;
}
.content-wrapper {
	background-color: #F0F1F6;
}
</style>
</head>
<body>
<c:set value="${requestScope['javax.servlet.forward.servlet_path'] }" var="servletPath"/>
	<header>
			<tiles:insertAttribute name="headerMenuCom" />
	</header>
	  <div class="row">
	    <main role="main" class="col-lg-12">
	   		<tiles:insertAttribute name="main" />
	    </main>
	</div>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>		
	
	<!-- JavaScript Libraries -->
	<script src="<c:url value='/resources/estateAgency/lib/jquery/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/estateAgency/lib/jquery/jquery-migrate.min.js'/>"></script>
	<script src="<c:url value='/resources/estateAgency/lib/popper/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/estateAgency/lib/bootstrap/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/estateAgency/lib/easing/easing.min.js'/>"></script>
	<script src="<c:url value='/resources/estateAgency/lib/owlcarousel/owl.carousel.min.js'/>"></script>
	<script src="<c:url value='/resources/estateAgency/lib/scrollreveal/scrollreveal.min.js'/>"></script>
	<!-- Contact Form JavaScript File -->
	<script src="<c:url value='/resources/estateAgency/contactform/contactform.js'/>"></script>
	
	<!-- Template Main Javascript File -->
	<script src="<c:url value='/resources/estateAgency/js/main.js'/>"></script>
	
	<!-- Js Plugins -->
	<script src="<c:url value='/resources/ogani-master/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/resources/ogani-master/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/ogani-master/js/jquery.nice-select.min.js'/>"></script>
	<script src="<c:url value='/resources/ogani-master/js/jquery-ui.min.js'/>"></script>
	<script src="<c:url value='/resources/ogani-master/js/jquery.slicknav.js'/>"></script>
	<script src="<c:url value='/resources/ogani-master/js/mixitup.min.js'/>"></script>
	<script src="<c:url value='/resources/ogani-master/js/owl.carousel.min.js'/>"></script>
	<script src="<c:url value='/resources/ogani-master/js/main.js'/>"></script>
	
	<tiles:insertAttribute name="postScript" />
	<jsp:include page="/includee/postScript.jsp" />   
</body>
</html>