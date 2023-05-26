<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	     	<h3>공지사항</h3>
	     	<br>
			<table id="boardTb" class="table table-bordered">
				<thead>
					<tr>
						<th class="col-md-2">일련번호</th>
						<th class="col-md-7">제목</th>
						<th class="col-md-3">등록일</th>
					</tr>
				</thead>
				<tbody id="listBody">
<!-- 					<tr> -->
<!-- 						<td>2022. 02. 14</td> -->
<%-- 						<td><a href="${cPath }cs/admin/noticeView.do">안녕하세요.</a></td> --%>
<!-- 					</tr> -->
				</tbody>
				<tfoot>
					<tr>	
						<td colspan="3">
							<div class="pagingArea">
								
							</div>
							<div id="searchUI">
								<select name="searchType" class="custom-select col-2">
									<option value>키워드</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select>
								<input class="form-control-sm col-6" type="text" name="searchWord"/>
								<input id="searchBtn" type="button" class="btn btn-secondary" value="검색"/>
								<input type="button" class="btn btn-success linkBtn right" value="등록"
									data-url="${cPath }/notice/admin/noticeInsert.do"
								/>
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
	let notice = $(this).data("notice");
	if(notice)
		location.href = CONTEXTPATH+"/notice/admin/noticeView.do?what="+notice.noticeSn;
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
		let noticeList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(noticeList && noticeList.length > 0){
			$.each(noticeList, function(index, notice){
				let trTag = $("<tr>");
				trTag.append(
					$("<td>").html(notice.rnum)
					, $("<td>").html(notice.noticeTitle)
					, $("<td>").html(notice.noticeDate)
				).data("notice", notice);
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"3"	
					}).html("검색 조건에 맞는 게시글이 없음.")		
				)	
			);
		}// if~else end
		listBody.append(trTags);
		pagingArea.html(pagingHTML);
	}
});
		
</script>