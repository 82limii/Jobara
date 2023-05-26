<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.btn {
	width: 65px;
	height: 45px;
	margin-left: 5px;
	margin-right: 5px;
}
</style>

<div class="container">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card-body">
	    	<h3>1:1 문의</h3><br>
			<table id="boardTb" class="table table-bordered">
				<thead>
					<tr>
						<th class="col-md-2">문의종류</th>
						<th class="col-md-6">제목</th>
						<th class="col-md-2">등록일</th>
						<th class="col-md-2">답변여부</th>
					</tr>
				</thead>
				<tbody id="listBody">
<!-- 					<tr> -->
<!-- 						<td>문의종류코드</td> -->
<!-- 						<td> -->
<%-- 							<a href="${cPath }/cs/admin/helpsView.do">문의드립니다2</a> --%>
<!-- 						</td> -->
<!-- 						<td>2022. 02. 15</td> -->
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${helps.ans eq true}"> --%>
<!-- 								<td><button type="button" class="btn btn-success btn-sm" disabled>완료</button></td> -->
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<td><button type="button" class="btn btn-secondary btn-sm" disabled>대기</button></td> -->
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
<!-- 					</tr> -->
				</tbody>
				<tfoot>
					<tr>	
						<td colspan="4">
			 				<div class="pagingArea">
			 					
							</div>
							<div id="searchUI">
								<select name="searchType" class="custom-select col-2">
									<option value>키워드</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select>
								<input type="text" class="form-control-sm col-5" name="searchWord"/>
								<input id="searchBtn" type="button" class="site-btn btn" value="검색"/>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
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
	let helps = $(this).data("helps");
	if(helps)
		location.href = CONTEXTPATH+"/helps/admin/helpsView.do?what="+helps.helpsSn;
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
		let helpsList = resp.list;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(helpsList && helpsList.length > 0){
			$.each(helpsList, function(index, helps){
				let trTag = $("<tr>");
				if(helps.ans){
					trTag.append(
						$("<td>").html(helps.helpsKind)
						, $("<td>").html(helps.helpsTitle)
						, $("<td>").html(helps.helpsDate)
						//, $("<td>").$("<button>").attr({"type" : "button", "class":"btn btn-success btn-sm disabled"}).html("완료")
						, $("<td>").html('<button type="button" class="btn btn-success btn-sm" disabled>완료</button>')
					).data("helps", helps);
				}else{
					trTag.append(
						$("<td>").html(helps.helpsKind)
						, $("<td>").html(helps.helpsTitle)
						, $("<td>").html(helps.helpsDate)
						//, $("<td>").$("<button>").attr({"type" : "button", "class":"btn btn-secondary btn-sm disabled"}).html("대기")
						, $("<td>").html('<button type="button" class="btn btn-secondary btn-sm" disabled>대기</button>')
					).data("helps", helps);
				}
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"4"	
					}).html("검색 조건에 맞는 게시글이 없음.")		
				)	
			);
		}// if~else end
		listBody.append(trTags);
		pagingArea.html(pagingHTML);
	}
});
		
</script>