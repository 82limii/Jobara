<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#insertAsk {
	float: right;
}
#boardTb {
	font-size: 10pt;
}
#search1 {
	width:400px;
}
</style>

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
								<li><a href="${cPath }/${menu.menuUrl }">${menu.menuName }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
	        </div>
	       </div>
	       <div class="col-lg-9 grid-margin stretch-card">
             <div class="card-body">
             	<p>
             	<h3>프로젝트 지원자 관리</h3>
             	</p>
             	<h4>진행중인 공고</h4><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-1">No</th>
							<th class="col-md-5">제목</th>
							<th class="col-md-2">등록일자</th>
							<th class="col-md-2">마감일자</th>
							<th class="col-md-2">직무</th>
					</thead>
					<tbody id="listBody1">
<!-- 						<tr> -->
<!-- 							<td>2</td> -->
<%-- 							<td><a href="${cPath }/applicant/com/applicantManList.do">프로젝트 공고 지원</a></td> --%>
<!-- 							<td>2022.02.12</td> -->
<!-- 							<td>2022.03.20</td> -->
<!-- 							<td>디자인</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>1</td> -->
<!-- 							<td>프로젝트 공고 지원</td> -->
<!-- 							<td>2022.01.10</td> -->
<!-- 							<td>2022.03.02</td> -->
<!-- 							<td>웹개발</td> -->
<!-- 						</tr> -->
						<tr>
							<td colspan="5">
								<div class="pagingArea1">
					 					
								</div>
							</td>
						</tr>
					</tbody>
				</table>
	        </div>
             <div class="card-body">
		        <h4>마감된 공고</h4><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-1">No</th>
							<th class="col-md-5">제목</th>
							<th class="col-md-2">등록일자</th>
							<th class="col-md-2">마감일자</th>
							<th class="col-md-2">직무</th>
					</thead>
					<tbody id="listBody2">
<!-- 						<tr> -->
<!-- 							<td>2</td> -->
<!-- 							<td><a href="#">프로젝트 공고 지원</a></td> -->
<!-- 							<td>2022.02.12</td> -->
<!-- 							<td>2022.03.20</td> -->
<!-- 							<td>웹개발</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>1</td> -->
<!-- 							<td>프로젝트 공고 지원</td> -->
<!-- 							<td>2022.01.10</td> -->
<!-- 							<td>2022.03.02</td> -->
<!-- 							<td>디자인</td> -->
<!-- 						</tr> -->
						<tr>	
							<td colspan="5">
								<div class="pagingArea2">
					 					
								</div>
							</td>
						</tr>						
					</tbody>
				</table>
	        </div>
		</div>
	</div>
</div>
<form id="searchForm1"> 
	<input type="hidden" name="page" placeholder="page"/>
</form>
<form id="searchForm2"> 
	<input type="hidden" name="page" placeholder="page"/>
</form>

<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">

//--------------------------------------------------------------- 진행중인 공고

let listBody1 = $("#listBody1").on("click", "tr", function(){
	let proapp = $(this).data("proapp");
	if(proapp)
		location.href = CONTEXTPATH+"/applicant/com/proApplicantManList.do?what="+proapp.probSn;
});

var date = new Date();

var year = date.getFullYear(); //년도
var month = date.getMonth()+1; //월
var day = date.getDate(); //일

if ((day+"").length < 2) {       // 일이 한자리 수인 경우 앞에 0을 붙여주기 위해
    day = "0" + day;
}

var getToday = year+"-"+month+"-"+day;

console.log("getToday", getToday);

let pagingArea1 = $(".pagingArea1");
let searchForm1 = $("#searchForm1").ajaxForm({
	dataType:"json"
	, success:function(resp){
		listBody1.empty();
		pagingArea1.empty();
		$("[name=page]").val("");
		let proappList = resp.list;
		let pagingHTML = resp.pagingHTMLBS;
		let trTags = [];
		if(proappList && proappList.length > 0){
			$.each(proappList, function(index, proapp){
				let trTag = $("<tr>");
				if(new Date(proapp.probRendd) > new Date(getToday)){  // 여기가 뭔가 이상함 - 재대로 먹히지 않음
					trTag.append(
						$("<td>").html(proapp.rnum)
						, $("<td>").html(proapp.probTitle)
						, $("<td>").html(proapp.probRstartd)
						, $("<td>").html(proapp.probRendd)
						, $("<td>").html(proapp.probDeptCd)
					).data("proapp", proapp);
				};
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"5"	
					}).html("해당 기업의 지원자가 없음.")
				)	
			);
		}// if~else end
		listBody1.append(trTags);
		pagingArea1.html(pagingHTML);
	}
}).submit();

pagingArea1.on("click", "a", function(event) {
	event.preventDefault();
	let page = $(this).data("page");
	searchForm1[0].page.value=page;
	searchForm1.submit();
	return false;
});
// ---------------------------------------------------------- 마감된 공고
</script>
<script>
let listBody2 = $("#listBody2").on("click", "tr", function(){
	let proapp = $(this).data("proapp");
	if(proapp)
		location.href = CONTEXTPATH+"/applicant/com/proApplicantManList.do?what="+proapp.probSn;
});

var date = new Date();

var year = date.getFullYear(); //년도
var month = date.getMonth()+1; //월
var day = date.getDate(); //일

if ((day+"").length < 2) {       // 일이 한자리 수인 경우 앞에 0을 붙여주기 위해
    day = "0" + day;
}

var getToday = year+"-"+month+"-"+day;

console.log("getToday", getToday);

let pagingArea2 = $(".pagingArea2");
let searchForm2 = $("#searchForm2").ajaxForm({
	dataType:"json"
	, success:function(resp){
		listBody2.empty();
		pagingArea2.empty();
		$("[name=page]").val("");
		let proappList = resp.list2;
		let pagingHTML = resp.pagingHTMLBS;
		let trTags = [];
		if(proappList && proappList.length > 0){
			$.each(proappList, function(index, proapp){
				let trTag = $("<tr>");
				if(new Date(proapp.probRendd) < new Date(getToday)){  // 여기가 뭔가 이상함 - 재대로 먹히지 않음
					trTag.append(
						$("<td>").html(proapp.rnum)
						, $("<td>").html(proapp.probTitle)
						, $("<td>").html(proapp.probRstartd)
						, $("<td>").html(proapp.probRendd)
						, $("<td>").html(proapp.probDeptCd)
					).data("proapp", proapp);
				};
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"5"	
					}).html("해당 기업의 지원자가 없음.")
				)	
			);
		}// if~else end
		listBody2.append(trTags);
		pagingArea2.html(pagingHTML);
	}
}).submit();

pagingArea2.on("click", "a", function(event) {
	event.preventDefault();
	let page = $(this).data("page");
	searchForm2[0].page.value=page;
	searchForm2.submit();
	return false;
});
		
</script>