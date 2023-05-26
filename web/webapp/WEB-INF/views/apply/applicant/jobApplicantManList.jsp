<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <div class="col-lg-9">
             <div class="card-body">
		        <h3>해당 공고</h3><br>
				 <table class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-5">제목</th>
							<th class="col-md-2">등록일자</th>
							<th class="col-md-2">마감일자</th>
							<th class="col-md-3">직무</th>
					</thead>
					<tbody>
						<tr>
							<td>${jobapp.jobTitle }</td>
							<td>${jobapp.jobStartd }</td>
							<td>${jobapp.jobEndd }</td>
							<td>${jobapp.jobDeptCd }</td>
						</tr>
					</tbody>
				</table>
	        </div>
			<form>
				<div class="card-body"><h4>지원자 리스트</h4></div>
			    <table class="table table-bordered">
			        <thead>
			            <tr>
			                <th class="col-md-1">No</th>
			                <th class="col-md-2">이름</th>
			                <th class="col-md-2">학력</th>
			                <th class="col-md-2">경력</th>
			                <th class="col-md-4">이력서 제목</th>
			            </tr>
			        </thead>
			        <tbody id="listBody">
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								<label class="checkbox-inline"> -->
<%-- 									<input type="checkbox" class="chk" name="cchk" onclick="cchkClicked()" value="${board.boardIdx}"> --%>
<!-- 								</label> -->
<!-- 							</td> -->
<!-- 							<td>김XX</td> -->
<!-- 						    <td>27</a></td> -->
<!-- 						    <td>대졸</td> -->
<!-- 						    <td>4년</td> -->
<!-- 						    <td>지원합니다.</td> -->
<!-- 						</tr> -->
						<tr>	
							<td colspan="5">
								<div class="pagingArea">
					 					
								</div>
							</td>
						</tr>
			        </tbody>
			    </table>
			</form>
        </div>
	</div>
</div>
<form id="searchForm"> 
	<input type="hidden" name="page" placeholder="page"/>
</form>

<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">
let listBody = $("#listBody").on("click", "tr", function(){
	let resume = $(this).data("resume");
	if(resume)
		location.href = CONTEXTPATH+"/applicant/com/jobApplicantView.do?what="+resume.reSn;
});

let pagingArea = $(".pagingArea");
let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	, success:function(resp){
		listBody.empty();
		pagingArea.empty();
		$("[name=page]").val("");
		let resumeList = resp.dataList;
		let pagingHTML = resp.pagingHTMLBS;
		let trTags = [];
		if(resumeList && resumeList.length > 0){
			$.each(resumeList, function(index, resume){
				let trTag = $("<tr>");
				if(resume.jobMonth == null){
					trTag.append(
						$("<td>").html(resume.rnum)
						, $("<td>").html(resume.pmemNm)
						, $("<td>").html(resume.ediSn)
						, $("<td>").html("없음")
						, $("<td>").html(resume.reTitle)
					).data("resume", resume);
				}else{
					trTag.append(
						$("<td>").html(resume.rnum)
						, $("<td>").html(resume.pmemNm)
						, $("<td>").html(resume.ediSn)
						, $("<td>").html(resume.jobYear + "년 " + resume.jobMonth + "개월")
						, $("<td>").html(resume.reTitle)
					).data("resume", resume);
				}
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
