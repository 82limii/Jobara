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
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>프로젝트 공고</span>
                </div>
                <ul>
					<c:forEach items="${menuList }" var="menu">
						<c:choose>
							<c:when test="${not empty menu.menuUpper }">
								<br>
								<h5>${menu.menuUpper }</h5>
							</c:when>
							<c:otherwise>
								<li><a href="${cPath }/${menu.menuUrl }">${menu.menuName }</a>
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
						<th colspan="3">프로젝트 공고 상세검색</th>
					</tr>
				</thead>
				<tr>
					<td>
						<div id="searchUI" class="form-inline">
							<div class="col-12">
								<select name="probEduCd" class="custom-select">
									<option value>학력</option>
									<c:forEach items="${eduList }" var="edu">
										<option value="${edu.commCd }">${edu.commNm }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<select name="probCarCd" class="custom-select col-3">
									<option value>경력</option>
									<c:forEach items="${carList }" var="car">
										<option value="${car.commCd }">${car.commNm }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<select name="probLocCd" class="custom-select col-3">
									<option value>지역</option>
									<c:forEach items="${cityList }" var="city">
										<option value="${city.locCd }">${city.locCityNm }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<select name="probDeptCd" class="custom-select col-3">
									<option value>직무</option>
									<c:forEach items="${deptList }" var="dept">
										<option value="${dept.commCd }">${dept.commNm }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-12">
							<select name="proOrder" class="custom-select left">
								<option value>등록일순</option>
								<option value="proRend">마감일순</option>
								<option value="proStart">프로젝트 시작일순</option>
								<option value="proEnd">프로젝트 종료일순</option>
							</select>
							<select class="custom-select" name="staDiv">
								<option value>기술분류</option>
								<c:forEach items="${staDivList }" var="staDiv">
									<option value="${staDiv['commCd'] }">${staDiv.commNm }</option>
								</c:forEach>
							</select>
							<select name="probSkill" class="custom-select">	
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
			<tbody id="listBody">
				<tr>
					<th class="col-1" style="text-align: center;">순번</th>
					<th class="col-2" style="text-align: center;">기업명</th>
					<th class="col-7" style="text-align: center;">공고명 및 지원조건</th>
					<th class="col-2" style="text-align: center;">공고마감일/<br>프로젝트 기간</th>
				</tr>
				<c:set  var="probBoardList" value="${pagingVO.dataList }"/>
				<c:choose>
					<c:when test="${empty probBoardList }">
						<tr>
							<td colspan="4">조건에 맞는 공고를 찾을 수 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${probBoardList }" var="probBoard">
							<c:url value="/proboard/proBoardView.do" var="viewURL">
								<c:param name="what" value="${probBoard.probSn }" />
							</c:url>
							<c:url value="/proboard/com/proBoardView.do" var="viewURLCom">
								<c:param name="what" value="${probBoard.probSn }" />
							</c:url>
							<c:url value="${cPath }/info/info.do" var="retrieveURL">
								<c:param name="what" value="${probBoard.ememId }" />
							</c:url>
							
							<tr>
								<td style="text-align: center;">${probBoard.rnum }</td>
								<td><a href="${retrieveURL}">${probBoard.ememNm }</a></td>
								<td>
								<c:choose>
									<c:when test="${authMember.userType eq 'Emember'}">
											<a href="${viewURLCom }">${probBoard.probTitle }</a>
									</c:when>
									<c:otherwise>
											<a href="${viewURL }">${probBoard.probTitle }</a>
									</c:otherwise>
								</c:choose>
									<hr>
									<h6>
										${probBoard.probEdu }&nbsp;&nbsp;&nbsp;
										|&nbsp;&nbsp;&nbsp;${probBoard.probCar }&nbsp;&nbsp;&nbsp;
										|&nbsp;&nbsp;&nbsp;${probBoard.probLoc }
										<br>
										${probBoard.probDept }&nbsp;&nbsp;|&nbsp;&nbsp;${probBoard.probSkill }
									</h6>
								</td>
								<td style="text-align: center;">
									${probBoard.probRendd }
									<hr>
									${probBoard.probStartd }
									<br>
									~&nbsp;${probBoard.probEndd }
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			
			<tfoot>
				<tr>	
					<td colspan="3">
						<div class="pagingArea">
							${pagingVO.pagingHTMLBS }
						</div>
					</td>
				</tr>
			</tfoot>
			</table>
		</div>
    </div>
</div>
<form id="searchForm" hidden="true">
	<input type="text" name="probEduCd" />
	<input type="text" name="probCarCd" />
	<input type="text" name="probLocCd" />
	<input type="text" name="probDeptCd" />
	<input type="text" name="proOrder" />
	<input type="text" name="probSkill" />
	<input type="text" name="page" />
</form>

<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>
<script type="text/javascript">
	let probEduCd = $("[name=probEduCd]").val("${pagingVO.detailSearch.probEduCd}");
	let probCarCd = $("[name=probCarCd]").val("${pagingVO.detailSearch.probCarCd}");
	let probLocCd = $("[name=probLocCd]").val("${pagingVO.detailSearch.probLocCd}");
	let probDeptCd = $("[name=probDeptCd]").val("${pagingVO.detailSearch.probDeptCd}");
	let proOrder = $("[name=proOrder]").val("${pagingVO.detailSearch.proOrder}");
	let probSkill = $("[name=probSkill]").val("${pagingVO.detailSearch.probSkill}");
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let searchUI = $("#searchUI");
	let searchFrom = $("#searchForm").paging({
		listBody:listBody
		, pagingArea:pagingArea
		, searchUI:searchUI
		, searchBtn:"#searchBtn"
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
