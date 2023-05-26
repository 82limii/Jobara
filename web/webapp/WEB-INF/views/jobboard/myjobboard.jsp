<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
.nav {
	display: inline-block;
}
#menu {
	vertical-align: middle;
}
.right {
	float: right;
}
</style>
<c:set var="jobBoardList" value="${pagingVO.dataList }" />
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <c:choose>
						<c:when test="${fn:contains(menu.menuSort, 'com')}">
		                    <span>채용 공고</span>
						</c:when>
						<c:otherwise>
		                    <span>기업페이지</span>
						</c:otherwise>
					</c:choose>
                </div>
                <ul>
					<c:forEach items="${menuList }" var="menu">
						<c:choose>
							<c:when test="${not empty menu.menuUpper }">
								<br>
								<h5>${menu.menuUpper }</h5>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty menu.menuFlag }">
										<c:if test="${menu.menuFlag eq 'ememId' }">
											<c:url value="${menu.menuUrl }" var="viewURL">
												<c:param name="what" value="${jobBoardList[0].ememId }" />
											</c:url>
											<li><a href="${cPath }/${viewURL }">${menu.menuName }</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<li><a href="${cPath }/${menu.menuUrl }">${menu.menuName }</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
            </div>
        </div>
        
        <div class="col-lg-9 grid-margin stretch-card">
             <div class="card-body">
             	<c:choose>
					<c:when test="${fn:contains(menu.menuSort, 'com')}">
				        <h4>내 공고 관리</h4><br>
					</c:when>
					<c:otherwise>
						<h4>채용공고 모아보기</h4><br>				
					</c:otherwise>
				</c:choose>
				
				<table id="boardTb" class="table table-bordered">
				<tr>
		 			<th class="col-1" style="text-align: center;">순번</th>
					<th class="col-8" style="text-align: center;">공고명 및 지원조건</th>
					<th class="col-2" style="text-align: center;">공고등록일</th>
		 		</tr>
			 	<tbody id="listBody">
			 	</tbody>
				 
				 <tfoot>
					<tr>	
						<td colspan="3">
							<div class="pagingArea">
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
     	   </div>
		</div>
    </div>
</div>
<c:choose>
	<c:when test="${authMember.userType eq 'Emember' }">
		<form id="searchForm" hidden="true" action="${cPath }/jobboard/com/myJobBoard.do">
			<input type="text" name="page" />
			<input type="text" name="isMine" value="y" />
		</form>
	</c:when>

	<c:otherwise>
		<form id="searchForm" hidden="true" action="${cPath }/jobboard/myJobBoard.do">
			<input type="text" name="page"/>
			<input type="text" name="what" value="${jobBoardList[0].ememId}"/>
		</form>
	</c:otherwise>
</c:choose>

<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json"
		, success:function(resp) {
			listBody.empty();
			pagingArea.empty();
			$("[name=page]").val("");
			let myList = resp.dataList;
			let pagingHTML = resp.pagingHTMLBS;
			let trTags = [];
			
			if(myList && myList.length > 0) {
				$.each(myList, function(index, data) {
					let trTag = $("<tr>");
					trTag.append(
							$("<td>").css("text-align","center").html(data.rnum)
							<c:choose>
								<c:when test="${authMember.userType eq 'Emember' }">
									,$("<td>").html("<a href='${cPath }/jobboard/com/jobBoardView.do?what="+data.jobSn+"' class='h5'>"+data.jobTitle+"</a>")
								</c:when>
								<c:otherwise>
									,$("<td>").html("<a href='${cPath }/jobboard/jobBoardView.do?what="+data.jobSn+"' class='h5'>"+data.jobTitle+"</a>")
								</c:otherwise>
							</c:choose>
											.append($("<hr>"))
											.append($("<h6>").html(
													data.jobEdu+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"
													+data.jobCar+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"
													+data.jobLoc+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"
													+data.jobEmp
													+"<br>"
													+data.jobDept+"&nbsp;&nbsp;|&nbsp;&nbsp;"
													+data.jobSkill
											))
							,$("<td>").css("text-align","center").html(data.jobDate+"<hr>"+data.jobStartd+" ~<br>"+data.jobEndd)
					);
					trTags.push(trTag);
				});
			} else {
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({colspan:3}).html("현재 기업의 공고가 없습니다.")		
					)	
				);
			}	// if ~ else end
			listBody.append(trTags);
			pagingArea.html(pagingHTML);
		}
	}).submit();
	
	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		searchForm[0].page.value=page;
		searchForm.submit();
		return false;
	});
</script>