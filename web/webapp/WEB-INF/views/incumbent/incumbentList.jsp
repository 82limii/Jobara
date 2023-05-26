<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#boardTb {
	font-size: 10pt;
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
			<div class="card-body"><h3>재직자 관리</h3></div>
			<form>
			    <table id="boardTb" class="table table-bordered">
			        <thead>
			        	<tr>
							<th class="col-md-2">이름</th>
							<th class="col-md-2">직급</th>
							<th class="col-md-2">직무</th>
							<th class="col-md-2">나이</th>
							<th class="col-md-2">성별</th>
							<th class="col-md-2">입사일</th>
						</tr>
			        </thead>
			        <tbody id="listBody">
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								<label class="checkbox-inline"> -->
<%-- 									<input type="checkbox" class="chk" name="cchk" onclick="cchkClicked()" value="${board.boardIdx}"> --%>
<!-- 								</label> -->
<!-- 							<td>김XX</td> -->
<!-- 							<td>인사과</td> -->
<!-- 							<td>4년차</td> -->
<!-- 						    <td>웹디자인</td> -->
<!-- 						    <td>27</td> -->
<!-- 						    <td>남자</td> -->
<!-- 						    <td>2022. 01. 01</td> -->
<!-- 						</tr> -->
			        </tbody>
			        <tfoot>
			        	<tr>	
							<td colspan="6">
								<div class="pagingArea">
					 					
								</div>
								<div id="searchUI">
									<select name="searchType" class="col-2 custom-select">
										<option value>키워드</option>
										<option value="name">이름</option>
									</select>
									<input class="form-control-sm col-5" type="text" name="searchWord"/>
									<input id="searchBtn" type="button" class="site-btn btn" value="검색"/>
								</div>
							</td>
						</tr>
			        </tfoot>
			    </table>
				<div class="text-right">
		            <a href='${cPath}/applicant/com/jobApplicantList.do' class="btn btn-success">추가</a>            
				</div>
				<br>
			</form>
        </div>
	</div>
</div>
<form id="searchForm"> 
	<input type="hidden" name="page" placeholder="page"/>
	<input type="hidden" name="searchType" placeholder="searchType"/>
	<input type="hidden" name="searchWord" placeholder="searchWord"/>
</form>

<script type="text/javascript">
let listBody = $("#listBody").on("click", "tr", function(){
	let incumbent = $(this).data("incumbent");
	if(incumbent)
		location.href = CONTEXTPATH+"/incumbent/com/incumbentView.do?what="+incumbent.empSn;
});

let pagingArea = $(".pagingArea");
$("#searchForm").paging({
	listBody:listBody
	, pagingArea:pagingArea
	, searchUI:$("#searchUI")
	, searchBtn:"#searchBtn"
	, success:function(resp){
		listBody.empty();
		pagingArea.empty();
		$("[name=page]").val("");
		let incumbentList = resp.pagingVO.dataList;
		let pagingHTML = resp.pagingVO.pagingHTMLBS;
		
		let trTags = [];
		
		if(incumbentList && incumbentList.length > 0){
			$.each(incumbentList, function(index, incumbent){
				let trTag = $("<tr>");
				trTag.append(
					$("<td>").html(incumbent.pmemNm)
					, $("<td>").html(incumbent.empPosiCd)
					, $("<td>").html(incumbent.empDeptCd)
					, $("<td>").html(incumbent.manYear+"살")
					, $("<td>").html(incumbent.pmemSex)
					, $("<td>").html(incumbent.empStartd)
				).data("incumbent", incumbent);
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"6"	
					}).html("검색 조건에 맞는 게시글이 없음.")		
				)	
			);
		}// if~else end
		listBody.append(trTags);
		pagingArea.html(pagingHTML);
	}
});
		
</script>
