<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
 <c:set value="${requestScope['javax.servlet.forward.servlet_path'] }" var="servletPath"/>
  <ul class="nav">
  <li class="nav-item">
   <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
     <span class="icon-bg"><i class="mdi mdi-crosshairs-gps menu-icon"></i></span>
     <span class="menu-title">프로젝트 목록</span>
     <i class="menu-arrow"></i>
   </a>
   <div class="collapse" id="ui-basic">
     <ul class="nav flex-column sub-menu" id="proListTag">
      
     </ul>
   </div>
 </li>
 	<c:choose>
      	<c:when test="${fn:contains(servletPath,'com') }">
		   <li class="nav-item">
		      <a class="nav-link" href="${cPath }/project/com/projMemList.do">
		        <span class="icon-bg"><i class="mdi mdi-account-multiple menu-icon"></i></span>
		        <span class="menu-title">구성원</span>
		      </a>
		    </li>
	    </c:when>
    </c:choose>
  <li class="nav-item sidebar-user-actions">
      <div class="sidebar-user-menu">
   	<c:choose>
      	<c:when test="${fn:contains(servletPath,'com') }">
	        <a href="${cPath }/indexCom.do" class="nav-link"><i class="mdi mdi-home menu-icon"></i>
	          <span class="menu-title">JOBARA</span>
	        </a>
        </c:when>
        <c:otherwise>
	        <a href="${cPath }/index.do" class="nav-link"><i class="mdi mdi-home menu-icon"></i>
	          <span class="menu-title">JOBARA</span>
	        </a>
        </c:otherwise>
       </c:choose>
      </div>
    </li>
 </ul>
</nav>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    let data;
    
	$.ajax({
		url:"${cPath}/project/com/projHome.do",
		method:"get",
		dataType:"json",
		success:function(result){
			data = result;
			
			console.log("result : " + JSON.stringify(result));
			console.log("data : " + JSON.stringify(data));
			
			let proListTag = $("#proListTag").on("click", "tr", function(){
				if(data != null){
// 					location.href=CONTEXTPATH+"/project/com/projHome.do?what=" + data.sn1;
				}
			});
			$.each(data, function(index, datas){
				let trTag ='';
				trTag += "<li class='nav-item'><a class='nav-link' href='${cPath}/project/com/projHome.do?what="+datas.sn1+"'>";
				trTag += datas.title;
				trTag += "</a></li>";
				proListTag.append(trTag);
			})
		}
	});
	$.ajax({
		url:"${cPath}/project/projHome.do",
		method:"get",
		dataType:"json",
		success:function(result){
			data = result;
			
			console.log("result : " + JSON.stringify(result));
			console.log("data : " + JSON.stringify(data));
			
			let proListTag = $("#proListTag").on("click", "tr", function(){
				if(data != null){
					location.href=CONTEXTPATH+"/project/projHome.do?what=" + data.sn1;
				}
			});
			$.each(data, function(index, datas){
				let trTag ='';
				trTag += "<li class='nav-item'><a class='nav-link' href='${cPath}/project/projHome.do?what="+datas.sn1+"'>";
				trTag += datas.title;
				trTag += "</a></li>";
				proListTag.append(trTag);
			})
		}
	});
  });

</script>
<script src="<c:url value='${cPath }/resources/calendar20/js/main.js'/>"></script>