<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
   
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>기업페이지</span>
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
												<c:param name="what" value="${infoe.ememId }" />
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
			<div class="card-body"><h4>기업정보</h4></div>
			<div class="col-12">
	   		<div class="card-body left">
	        	<img src="<spring:url value='/image/${infoe.ememPic }'/>" style="float: left; margin: 0px; width:250px; height:110px;"/>
	        </div>
	        <div class="card-body center">	
				<h2>${infoe.ememNm }</h2>
			</div>
			<div>
				<c:choose>
					<c:when test="${authMember.userType eq 'Pmember'}">
						<button class="linkBtn btn btn-outline-warning right" data-url="${cPath }/scrap/scrapEnterInsert.do?what=${infoe.ememId}" >스크랩</button>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
			<table class="table table-bordered col-12" style="text-align: center" >
			   <tr style="text-align: center;">
			      <td>사원수</td>
			      <td>${infoe.comHeadcnt }</td>
			      <td>설립일</td>
			      <td>${infoe.comFounddate }</td>
			   <tr style="text-align: center;">
			      <td>매출액</td>
			      <td>${infoe.comTake }</td>
			      <td>주요사업</td>
			      <td>${infoe.comBussiness }</td>
			   </tr>
			   <tr style="text-align: center;">
			      <td>주소</td>
			      <td>${infoe.comAdd }</td>
			      <td>홈페이지</td>
			      <td><a href="https://${infoe.comHome }/" target="_blank">${infoe.comHome }</a></td>
			   </tr>
			   <tr style="text-align: center;">
			      <td>복리후생</td>
			      <td colspan="3">${infoe.comWelfare }</td>
			   </tr>
			</table>
			</div>
			<div class="card-body"><h4>공고현황</h4></div>
			<table class="table table-bordered col-12" >
				<thead>
					<tr>
						<th>접수시작일</th>
						<th>접수마감일</th>
						<th>제목</th>
					</tr>
				</thead>
				<tbody id="listBody">
<!-- 					<tr> -->
<!-- 						<td>2022. 02. 17</td> -->
<!-- 						<td>2022년 2월 신입공채 모집</td> -->
<!-- 						<td>채용공고</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>2022. 01. 04</td> -->
<!-- 						<td>2021년 1월 신입공채 모집</td> -->
<!-- 						<td>채용공고</td> -->
<!-- 					</tr> -->
					<tr>	
						<td colspan="3">
							<div class="pagingArea">
				 					
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${authMember.userType eq 'Emember'}">
					<input type="button" class="site-btn linkBtn right" value="더보기"
										data-url="${cPath }/jobboard/com/myJobBoard.do"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="site-btn linkBtn right" value="더보기"
										data-url="${cPath }/jobboard/jobBoardList.do"/>
				</c:otherwise>
			</c:choose>
			<div class="card-body"></div>
			<h4>재직자 정보 통계</h4>
			<div class="card-body">
				<table class="table table-bordered col-12">
					<thead>
						<tr>
							<td>연령 통계</td>
							<td>연봉정보</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="col-6">
								<div id="donutChart"></div>
							</td>
							<td class="col-6">
								<div id="categoryData"></div>
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</div>
    </div>
</div>
<form id="searchForm"> 
	<input type="hidden" name="page" placeholder="page"/>
</form>

<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">

let listBody = $("#listBody").on("click", "tr", function(){
	let info = $(this).data("info");
	if(info)
		location.href = CONTEXTPATH+"/jobboard/com/jobBoardView.do?what="+info.jobSn;
});

let pagingArea = $(".pagingArea");
let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(resp){
		listBody.empty();
		pagingArea.empty();
		$("[name=page]").val("");
		let infoList = resp.dataList;
		let pagingHTML = resp.pagingHTMLBS;
		let trTags = [];
		if(infoList && infoList.length > 0){
			$.each(infoList, function(index, info){
				let trTag = $("<tr>");
				trTag.append(
					$("<td>").html(info.jobTitle)
					, $("<td>").html(info.jobStartd)
					, $("<td>").html(info.jobEndd)
				).data("info", info);
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"3"	
					}).html("해당 기업의 공고가 없음.")
				)
			);
		}// if~else end
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

<script>
var chart1 = bb.generate({
	  data: {
	    columns: [
	    	<c:forEach items="${infoYear }" var="year">
				["${year.empYearDiv}", ${year.yearCnt}],
			</c:forEach>
// 			["20대", 25],
// 			["30대", 40],
// 			["40대", 85]
	    ],
	    type: "donut", // for ESM specify as: donut()
	  },
	  donut: {
	    title: "연령통계"
	  },
	  bindto: "#donutChart"
	});


var chart2 = bb.generate({
	data: {
		columns: [
			<c:forEach items="${infoMoney }" var="money">
				["${money.empMoneyDiv}", ${money.empSalary}],
			</c:forEach>
		],
		type: "bar",
	},
	bar: {
		width: {
			ratio: 1
		}
	},
	bindto: "#categoryData"
});

</script>