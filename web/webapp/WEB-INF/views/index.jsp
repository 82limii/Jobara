<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
		
<!-- 장인슬 -->
		
		<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!--    Google Fonts-->
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!--Fontawesom-->
        <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/main/css/font-awesome.min.css">

        <!--Animated CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/main/css/animate.min.css">

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath }/resources/main/css/bootstrap.min.css" rel="stylesheet">
        <!--Bootstrap Carousel-->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/main/css/carousel.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/main/css/isotope/style.css">

        <!--Main Stylesheet-->
        <link href="${pageContext.request.contextPath }/resources/main/css/style.css" rel="stylesheet">
        <!--Responsive Framework-->
        <link href="${pageContext.request.contextPath }/resources/main/css/responsive.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>-->
        <script src="${pageContext.request.contextPath }/resources/main/js/jquery-1.12.3.min.js"></script>

        <!--Counter UP Waypoint-->
        <script src="${pageContext.request.contextPath }/resources/main/js/waypoints.min.js"></script>
        <!--Counter UP-->
        <script src="${pageContext.request.contextPath }/resources/main/js/jquery.counterup.min.js"></script>
		
		<script>
            //for counter up
            $('.counter').counterUp({
                delay: 10,
                time: 1000
            });
        </script>



        <!--Isotope-->
        <script src="${pageContext.request.contextPath }/resources/main/js/isotope/min/scripts-min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/main/js/isotope/cells-by-row.js"></script>
        <script src="${pageContext.request.contextPath }/resources/main/js/isotope/isotope.pkgd.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/main/js/isotope/packery-mode.pkgd.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/main/js/isotope/scripts.js"></script>


        <!--Back To Top-->
        <script src="${pageContext.request.contextPath }/resources/main/js/backtotop.js"></script>


        <!--JQuery Click to Scroll down with Menu-->
        <script src="${pageContext.request.contextPath }/resources/main/js/jquery.localScroll.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/main/js/jquery.scrollTo.min.js"></script>
        <!--WOW With Animation-->
        <script src="${pageContext.request.contextPath }/resources/main/js/wow.min.js"></script>
        <!--WOW Activated-->
        <script>
            new WOW().init();
        </script>


        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="${pageContext.request.contextPath }/resources/main/js/bootstrap.min.js"></script>
        <!-- Custom JavaScript-->
        <script src="${pageContext.request.contextPath }/resources/main/js/main.js"></script>
    </head>
		
		

        <!--Start of slider section-->

	<div class="col-12 center row">
        <div class="col-9">
        <section id="slider" >
            <div id="carousel-example-generic" class="carousel slide carousel-fade" data-ride="carousel" data-interval="3000">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <div class="slider_overlay">
                            <img src="<c:url value='/resources/img/img1.png' />" alt="...">
                            <div class="carousel-caption">
                                <div class="slider_text">
                                    <h3>(주)대덕기업</h3>
                                    <h2 style="color: white;">근로자를 위한 최고의 기업</h2>
                                    <p>지금 보고있는 당신을 기다리고 있습니다.</p>
<!--                                     <a href="#" class="custom_btn">Read  More</a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--End of item With Active-->
                    <div class="item">
                        <div class="slider_overlay">
                            <img src="<c:url value='/resources/img/img2.jpg' />" alt="...">
                            <div class="carousel-caption">
                                <div class="slider_text">
                                    <h3>(주)취업해결사</h3>
                                    <h2 style="color: white;">취업준비때문에 스트레스 받으시나요?</h2>
                                    <p>복잡한 문제를 해결해드립니다.</p>
<!--                                     <a href="#" class="custom_btn">Read  More</a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--End of Item-->
                    <div class="item">
                        <div class="slider_overlay">
                            <img src="<c:url value='/resources/img/img3.jpg'/>" alt="...">
                            <div class="carousel-caption">
                                <div class="slider_text">
                                    <h3>JOBARA</h3>
                                    <h2 style="color: white;">더이상 늦기전에 기회를 잡아라</h2>
                                    <p>당신을 위한 맞춤서비스 추천 시스템.</p>
<!--                                     <a href="#" class="custom_btn">Read  More</a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--End of item-->
                </div>
                <!--End of Carousel Inner-->
            </div>
        </section>      
        </div>
        <!--end of slider section-->  
       <security:authorize access="permitAll()" >
       	<security:authentication property="principal" var="authMember"/>
       	<security:authentication property="details" var="details"/>
        
		<div class="col-3 row" id="login" style="display: inline-block; float: right; margin-top: 2%;">
		<c:set var="authMember" value="${sessionScope.authMember }" />
		    <c:choose>
		    	<c:when test="${not empty authMember }">
			        <img src="<spring:url value='/image/${authMember.pmemPic }'/>" style="float: left; margin: 5px; width:100px; height:110px;"/>			
					<div style="margin-top: 20px;">
						<p>${authMember.pmemNm}님</p>
					<form id="logoutForm" method="post" action="${cPath }/login/logout.do"></form>	
				        <input type="button" id="logoutBtn" class="btn btn-outline-danger" value="로그아웃">
					<script type="text/javascript">
						$("#logoutBtn").on("click", function(event){
							event.preventDefault();
							$("#logoutForm").trigger("submit");
							return false;
						});
					</script>
					
					</div>	
					<div class="col-12 row" style="margin:0px; padding:0px;">
					<a href="${cPath }/myPage/resumeList.do" class="btn btn-outline-secondary col-6">마이페이지</a>
			        <a href="${cPath }/fitme/survey.do" class="btn btn-outline-secondary col-6">설문조사</a>
					<a href="${cPath }/myPage/applyList.do" class="btn btn-outline-secondary col-6">지원현황</a>
			        <a href="${cPath }/scrap/scrapPost.do" class="btn btn-outline-secondary col-6">스크랩</a>
			        <a href="${cPath }/project/projHome.do" class="btn btn-outline-info col-12">프로젝트 홈</a>
					</div>
		        </c:when>
		    	<c:otherwise>
		    		<div class="col-12 row">
				        <input type="button" style="margin: 15px;" class="btn btn-success btn-lg" value="로그인" onclick="location.href='${cPath }/login/loginForm.do'"/>
				        <input type="button" style="margin: 15px;" class="btn btn-success btn-lg" value="회원가입" onclick="location.href='${cPath }/member/memberForm.do'"/>
		     		</div>
		     	</c:otherwise>
			</c:choose>  
		</div>
	</security:authorize> 
	</div>


        <!--Start of volunteer-->
        <section id="volunteer">
        	<div class="container">
                <div class="row vol_area">
                    <div class="col-md-8">
                        <div class="volunteer_content">
                            <h3>당신을 위한 <span>모든것을 누리세요.</span></h3>
                            <p>다양한 혜택과 기능을 이용하실 수 있습니다.</p>
                        </div>
                    </div>
                    <!--End of col-md-8-->
                    <div class="col-md-3 col-md-offset-1">
                        <div class="join_us">
                            <a href="${cPath }/member/memberForm.do" class="vol_cust_btn">join us</a>
                        </div>
                    </div>
                    <!--End of col-md-3-->
                </div>
                <!--End of row and vol_area-->
            </div>
            <!--End of container-->
        </section>
        <!--end of volunteer-->    


        <!--start of event-->
        <section id="event">
            <div class="container" style="text-align: center;">

                <!--End of row-->
                <div class="row">
                    <!--End of col-md-8-->
                    <div class="col-md-6">
                    	<h2>채용공고<br><br></h2>
                    	<c:forEach items="${jobList }" var="job">
	                        <div class="event_news">
	                            <div class="event_single_item fix">
	                                <div class="event_news_img floatleft">
	                                	<img id="thumnail" src="<spring:url value='/image/${job.ememPic }'/>"alt="companyPic" />
	                                </div>
	                                <div class="event_news_text">
	                                    <a href="${cPath }/info/info.do?what=${job.ememId}"><h4>${job.ememNm }</h4></a>
	                                    <p><a href="${cPath }/jobBoard/jobBoardView.do?what=${job.jobSn}">${job.jobTitle }</a></p>
	                                </div>
	                            </div>
	                        </div>
                    	</c:forEach>
                       	<a href="${cPath }/jobboard/jobBoardList.do" class="btn btn-outline-dark">Read  More</a>
                    </div>
                    <div class="col-md-6">
                    	<h2>프로젝트 공고<br><br></h2>
                        <c:forEach items="${proList }" var="pro">
	                        <div class="event_news">
	                            <div class="event_single_item fix">
	                                <div class="event_news_img floatleft">
	                                	<img id="thumnail" src="<spring:url value='/image/${pro.ememPic }'/>"alt="companyPic" />
	                                </div>
	                                <div class="event_news_text">
	                                    <a href="${cPath }/info/info.do?what=${pro.ememId}"><h4>${pro.ememNm }</h4></a>
	                                    <p><a href="${cPath }/jobBoard/jobBoardView.do?what=${pro.probSn}">${pro.probTitle }</a></p>
	                                </div>
	                            </div>
	                        </div>
                    	</c:forEach>
                       	<a href="${cPath }/proboard/proBoardList.do" class="btn btn-outline-dark">Read  More</a>
                    </div>
                    <!--End of col-md-4-->
                </div>
                <!--End of row-->
            </div>
            <!--End of container-->
        </section>
        <!--end of event-->



        <!--Start of testimonial-->
        <section id="testimonial">
            <div class="testimonial_overlay">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="testimonial_header text-center">
                                <h2>기업 리뷰</h2>
                                <p>재직자 및 퇴직자가 남긴 다양한 기업 리뷰</p>
                            </div>
                        </div>
                    </div>
                    <!--End of row-->
                    <section id="carousel">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12 text-center">
                                    <div class="carousel slide" id="fade-quote-carousel" data-ride="carousel" data-interval="3000">
                                        <!-- Carousel indicators -->
                                        <ol class="carousel-indicators">
                                            <li data-target="#fade-quote-carousel" data-slide-to="0" class="active"></li>
                                            <li data-target="#fade-quote-carousel" data-slide-to="1"></li>
                                        </ol>
                                        <!-- Carousel items -->
                                        <div class="carousel-inner">
                                            <div class="active item">
                                                <div class="row">
                                                	<c:forEach items="${reviewList1 }" var="review">
	                                                    <div class="col-md-6">
	                                                    	<div class="profile-circle">
	                                                        </div>
	                                                        <div class="testimonial_content">
	                                                            <i class="fa fa-quote-left"></i>
	                                                            <p>${review.reviewComment }</p>
	                                                        </div>
	                                                        <div class="testimonial_author">
	                                                            <h5>${review.coceNm}</h5>
	                                                            <p>${review.ememNm }</p>
	                                                        </div>
	                                                    </div>
                                                	</c:forEach>
                                                </div>
                                            </div>
                                            <!--End of item with active-->
                                            <div class="item">
                                                <div class="row">
                                                	<c:forEach items="${reviewList2 }" var="review">
	                                                    <div class="col-md-6">
	                                                    	<div class="profile-circle">
	                                                        </div>
	                                                        <div class="testimonial_content">
	                                                            <i class="fa fa-quote-left"></i>
	                                                            <p>${review.reviewComment }</p>
	                                                        </div>
	                                                        <div class="testimonial_author">
	                                                            <h5>${review.coceNm}</h5>
	                                                            <p>${review.ememNm }</p>
	                                                        </div>
	                                                    </div>
                                                	</c:forEach>
                                                </div>
                                            </div>
                                            <!--ENd of item-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--End of row-->
                        </div>
                        <!--End of container-->
                    </section>
                    <!--End of carousel-->
                </div>
            </div>
            <!--End of container-->
        </section>
        <!--end of testimonial-->





        <!--Start of Purches-->
        <section id="purches">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h2 class="purches_title">JOBARA 후원하기</h2>
                    </div>
                    <div class="col-md-2 col-md-offset-4" style="float: right;">
                        <a href="${cPath }/pay/payForm.do" class="purches_btn">SUPPORT</a>
                    </div>
                </div>
                <!--End of row-->
            </div>
            <!--End of container-->
        </section>
        <!--End of purches-->


        <!--Scroll to top-->
        <a href="#" id="back-to-top" title="Back to top">&uarr;</a>
        <!--End of Scroll to top-->


