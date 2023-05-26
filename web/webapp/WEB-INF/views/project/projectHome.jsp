<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<style>
#projAddBtn {
	float: right;
}
#calendar {
  max-width: 900px;
  margin: 0 auto;
}
</style>

<h1 style="text-align:center;"> 일정 </h1>
<div id='calendar-container'>
  <div id='calendar'></div>
</div>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    let data;
	$.ajax({
		url:"${cPath}/project/projHome.do",
		method:"get",
		dataType:"json",
		success:function(result){
			data = result;
			
			console.log("result : " + JSON.stringify(result));
			console.log("data : " + JSON.stringify(data));
			
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
			      events: data,
			      eventClick: function(info){
			      }
			    });
			    calendar.render();
		}
	});
	
	
	
  });

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

<br>
<div>
<c:set value="${requestScope['javax.servlet.forward.servlet_path'] }" var="servletPath" />
	<c:choose>
		<c:when test="${fn:contains(servletPath,'com') }">
			 <input id="projAddBtn" type="button" class="btn btn-outline-secondary btn-md linkBtn" value="프로젝트 생성"
			             	data-url="${cPath }/project/com/projInsert.do"
			             />
		</c:when>
	</c:choose>
</div>






















