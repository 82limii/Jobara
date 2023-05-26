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
.align {
	text-align: center;
}
</style>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>채용 공고</span>
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
												<c:param name="what" value="${authMember.ememId }" />
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
        <div class="col-lg-9">
			<table class="table">
				<thead>
					<tr>
						<th>채용공고 상세검색</th>
					</tr>
				</thead>
				<tr>
					<td>
						<div id="searchUI" class="form-inline">
							<div class="col-12">
								<select name="jobEduCd" class="custom-select">
									<option value>학력</option>
									<c:forEach items="${eduList }" var="edu">
										<option value="${edu.commCd }">${edu.commNm }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<select name="jobCarCd" class="custom-select col-3">
									<option value>경력</option>
									<c:forEach items="${carList }" var="car">
										<option value="${car.commCd }">${car.commNm }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<select name="jobLocCd" class="custom-select col-3">
									<option value>지역</option>
									<c:forEach items="${cityList }" var="city">
										<option value="${city.locCd }">${city.locCityNm }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<select name="jobDeptCd" class="custom-select col-3">
									<option value>직무</option>
									<c:forEach items="${deptList }" var="dept">
										<option value="${dept.commCd }">${dept.commNm }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-12">
							<select name="jobOrder" class="custom-select left">
								<option value>등록일순</option>
								<option value="jobEnd">마감일순</option>
							</select>
							<select class="custom-select" name="staDiv">
								<option value>기술분류</option>
								<c:forEach items="${staDivList }" var="staDiv">
									<option value="${staDiv['commCd'] }">${staDiv.commNm }</option>
								</c:forEach>
							</select>
							<select name="jobSkill" class="custom-select">	
								<option value>스택명</option>
								<c:forEach items="${stackList }" var="stack">
									<option class="${stack['commCd'] }" value="${stack['comsNm'] }">${stack.comsNm }</option>
								</c:forEach>
							</select>
							<button class="btn btn-outline-secondary right" id="searchBtn">상세검색</button>
							</div>
						</div>
					</td>
				</tr>
			</table>

			<table id="boardTb" class="table table-bordered">
			<tr>
				<th class="col-1" style="text-align: center;">순번</th>
				<th class="col-2" style="text-align: center;">기업명</th>
				<th class="col-7" style="text-align: center;">공고명 및 지원조건</th>
				<th class="col-2" style="text-align: center;">공고마감일</th>
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
<form id="searchForm" hidden="true">
	<input type="text" name="jobEduCd" />
	<input type="text" name="jobCarCd" />
	<input type="text" name="jobLocCd" />
	<input type="text" name="jobDeptCd" />
	<input type="text" name="jobOrder" />
	<input type="text" name="jobSkill" />
	<input type="text" name="page" />
</form>

<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>
<script type="text/javascript">
	let jobEduCd = $("[name=jobEduCd]").val("${pagingVO.detailSearch.jobEduCd}");
	let jobCarCd = $("[name=jobCarCd]").val("${pagingVO.detailSearch.jobCarCd}");
	let jobLocCd = $("[name=jobLocCd]").val("${pagingVO.detailSearch.jobLocCd}");
	let jobDeptCd = $("[name=jobDeptCd]").val("${pagingVO.detailSearch.jobDeptCd}");
	let jobOrder = $("[name=jobOrder]").val("${pagingVO.detailSearch.jobOrder}");
	let jobSkill = $("[name=jobSkill]").val("${pagingVO.detailSearch.jobSkill}");
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let searchUI = $("#searchUI");
	let searchFrom = $("#searchForm").paging({
		listBody:listBody
		, pagingArea:pagingArea
		, searchUI:searchUI
		, searchBtn:"#searchBtn"
		, success: function(resp) {
			console.log(resp);
			listBody.empty();
			pagingArea.empty();
			$("[name=page]").val("");
			let jobList = resp.dataList;
			let pagingHTML = resp.pagingHTMLBS;
			
			let trTags = [];
			
			if(jobList && jobList.length > 0) {
				$.each(jobList, function(index, jobBoard) {
					let trTag = $("<tr>");
					trTag.append(
						$("<td>").css("text-align","center").html(jobBoard.rnum)
						, $("<td>").html("<a href='${cPath }/info/info.do?what="+jobBoard.ememId+"'>"+jobBoard.ememNm+"</a>")
						<c:choose>
							<c:when test="${authMember.userType eq 'Emember' }">
								,$("<td>").html("<a href='${cPath }/jobboard/com/jobBoardView.do?what="+jobBoard.jobSn+"'>"+jobBoard.jobTitle+"</a>")
							</c:when>
							<c:otherwise>
								,$("<td>").html("<a href='${cPath }/jobboard/jobBoardView.do?what="+jobBoard.jobSn+"'>"+jobBoard.jobTitle+"</a>")
							</c:otherwise>
						</c:choose>
										.append($("<hr>"))
										.append($("<h6>").html(
												jobBoard.jobEdu+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"
												+jobBoard.jobCar+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"
												+jobBoard.jobLoc+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"
												+jobBoard.jobEmp
												+"<br>"
												+jobBoard.jobDept+"&nbsp;&nbsp;|&nbsp;&nbsp;"
												+jobBoard.jobSkill
										))
						,$("<td>").css("text-align","center").html(jobBoard.jobEndd)
					).data("jobBoard", jobBoard);
					trTags.push(trTag);
				});
			} else {
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({colspan:"4"}).html("조건에 맞는 공고를 찾을 수 없습니다.")		
					)	
				);
			}	// if~else end
			listBody.append(trTags);
			pagingArea.html(pagingHTML);
		}
	});
	
	let staDiv = $("[name=staDiv]").on("change", function() {
		let stack = $("[name=jobSkill]");
		let comm_cd = $(this).val();
		console.log(comm_cd);
		stack.find("option").hide();
		stack.find("option:first").show();
		stack.find("."+comm_cd).show();
	});
</script>
