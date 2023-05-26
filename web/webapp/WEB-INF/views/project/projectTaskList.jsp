<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<style>
#addBtn{
	float: right;
}
.btn {
	float: right;
	margin: 5px;
	margin-top: 0;
}
tr {
	text-align: center;
}
#trView:hover {
	background-color: lightgray;
}
</style>
<div class="col-lg-12 grid-margin stretch-card">
<div class="card">
    <div class="card-body">
      <h3 class="card-title">작업 관리</h3>
       <button id="addBtn" type="button" class="btn btn-outline-secondary linkBtn"
      	data-url="${cPath }/project/projTaskInsert.do?what=${param.what}"><i class="mdi mdi-plus"></i>등록</button>
      <table class="table">
        <thead>
          <tr>
            <th class="col-lg-1">NO</th>
            <th class="col-lg-3">업무명</th>
            <th class="col-lg-2">담당자</th>
            <th class="col-lg-2">시작일</th>
            <th class="col-lg-2">마감일</th>
            <th class="col-lg-1">중요도</th>
            <th class="col-lg-1">진행상태</th>
          </tr>
        </thead>
        <tbody id="listBody">
<!--           <tr> -->
<!--             <td> -->
<!--             	<input type="checkbox" > -->
<!--             </td> -->
<%--             <td><a href="${cPath }/project/projTaskInsert.do">페이지 구현</a></td> --%>
<!--             <td>최인수</td> -->
<!--             <td>2022-02-10</td> -->
<!--             <td>2022-02-28</td> -->
<!--             <td> -->
<!-- 				<select class="btn-outline-secondary nav-link dropdown-toggle"> -->
<!--            	 	<option>낮음</option> -->
<!--            	 	<option>보통</option> -->
<!--            	 	<option>높음</option> -->
<!--            	 	<option>긴급</option> -->
<!--            	 </select> -->
<!-- 			</td> -->
<!--             <td> -->
<!--            	 <select class="btn-outline-secondary nav-link dropdown-toggle"> -->
<!--            	 	<option>0%</option> -->
<!--            	 	<option>10%</option> -->
<!--            	 	<option>20%</option> -->
<!--            	 	<option>30%</option> -->
<!--            	 </select> -->
<!--             </td> -->
<!--             <td>홍길똥</td> -->
<!--           </tr> -->
        </tbody>
        <tfoot>
        	<tr>
				<td colspan="7">
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
<form id="pagingForm"> 
	<input type="hidden" name="page" placeholder="page"/>
</form>
<script type="text/javascript">
let listBody = $("#listBody").on("click", "tr", function(){
	let task = $(this).data("task");
	if(task){
		location.href=CONTEXTPATH+"/project/projTaskView.do?jobara="+task.protSn+"&what="+task.proSn;
	}
})

let pagingArea = $(".pagingArea");
$("#pagingForm").paging({
	listBody:listBody
	, pagingArea:pagingArea
	, success:function(resp){
		listBody.empty();
		pagingArea.empty();
		$("[name=page]").val("");
		let taskList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(taskList && taskList.length > 0){
			$.each(taskList, function(index, task){
				let trTag = $("<tr id='trView'>");
				trTag.append(
					 $("<td>").html(task.rnum)					
					, $("<td style='text-align : left;'>").html(task.protNm)					
					, $("<td>").html(task.protManager)					
					, $("<td>").html(task.protStartd)					
					, $("<td>").html(task.protEndd)				
					, $("<td><select class='form-control'><option value>").html(task.protImprt)
					, $("<td>").html(task.protStateNm)				
				).data("task", task);
				trTags.push(trTag);
			});
		}else{
			trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"7"	
						}).html("검색 조건에 맞는 게시글이 없음.")		
					)	
				);
			}// if~else end
		listBody.append(trTags);
		pagingArea.html(pagingHTML);
}
});

</script>













