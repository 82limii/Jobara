<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>   
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">

<style>
#calendar{
	float: left;
}
#sideComName, #projHome {
	float: right;
}
#calendar-container {
	height: 760px;
}
.margin-top {
	margin-top: 50px;
}
.margin {
	margin: 5px;
}
#logOutBtn {
	margin: auto;
}
.center {
	text-align: center;
}
#logoImg {
	width: 120px;
	height: auto;
}
#trView:hover {
	background-color: lightgray;
}
</style>
<div class="container">
	<div class="main-panel col-12 row">
        <div class="col-lg-8" id="calendar-container" >
       	<div id='calendar'></div>
        </div>
        <div class="col-lg-4" id="sideComName">
	       <div class="card-body">
	       	  <div class="card">
			     <c:set var="authMember" value="${sessionScope.authMember }" />
			        <c:choose>
					   <c:when test="${not empty authMember }">
	       	 	          <div class="row no-gutters">
				             <div class="col-md-5 col-5 center">
				                <img src="<spring:url value='/image/${authMember.ememPic }'/>" id="logoImg" />
				                <form id="logoutForm" method="post" action="${cPath }/login/logout.do"></form>	
				                <button type="button" class="btn btn-rounded btn-secondary" id="logOutBtn">로그아웃</button>
							      <script type="text/javascript">
									$("#logOutBtn").on("click", function(event){
										event.preventDefault();
										$("#logoutForm").trigger("submit");
										return false;
									});
								</script>
				    		 </div>
							 <div class="col-md-7 col-7">
			      			 	<div class="card-body center">
			        			   <h5 class="card-title">${authMember.ememNm} 기업</h5>
							       <p class="card-text">
							          <button type="button" class="margin btn btn-outline-dark linkBtn" data-url="${cPath }/incumbent/com/incumbentList.do">재직자 관리</button>
							          <button type="button" class="margin btn btn-outline-dark linkBtn" data-url="${cPath }/applicant/com/jobApplicantList.do">지원자 관리</button>
							       </p>

			      			   </div>
						   </div>
			    		</div>
			        </c:when>
    			</c:choose>
		   	 	<button type="button" class="btn btn-outline-success linkBtn" id="projHome" data-url="${cPath }/project/com/projHome.do">프로젝트 홈</button>
	       	 </div>
	  		 </div>
		</div>
	</div>
</div>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    let data;
    
	$.ajax({
		url:"${cPath}/project/com/projHome.do",
		method:"get",
		dataType:"json",
		success:function(result){
			data = result;
			
			console.log("result : " + JSON.stringify(result));
			console.log("data : " + JSON.stringify(data));
			
// 			let listBody = $("#listBody").on("click", "tr", function(){
// 				if(data != null){
// 					location.href=CONTEXTPATH+"/project/com/projHome.do?what=" + data.sn1;
// 				}
// 			});
// 			$.each(data, function(index, datas){
// 				let trTag ='';
// 				trTag += "<tr id='trView'>";

// 				trTag += "<td>";
// 				trTag += datas.title;
// 				trTag += "</td>";
				
// 				trTag += "</tr>";
// 				listBody.append(trTag);
// 			})
			
			var calendar = new FullCalendar.Calendar(calendarEl, {
			      plugins: [ 'interaction', 'dayGrid' ],
			      header: {
			        left: 'prevYear,prev,next,nextYear today',
			        center: 'title',
			        right: 'dayGridMonth,dayGridWeek'
			      },
			      navLinks: true, // can click day/week names to navigate views
			      editable: true,
//			       selectable: true,
//			       selectMirror:true,
			      eventLimit: true, // allow "more" link when too many events
			      events: data   	
			    });
			    calendar.render();
		}
	});
  });

</script>
<script src="<c:url value='${cPath }/resources/calendar20/js/main.js'/>"></script>