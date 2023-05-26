<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
#trView:hover {
	background-color: lightgray;
}
</style>
<div class="col-lg-12 grid-margin stretch-card">
<div class="card">
    <div class="card-body">
      <h3 class="card-title">보고서</h3>
      <table class="table">
        <thead>
          <tr>
            <th class="col-md-6">제목</th>
            <th class="col-md-3">작성자</th>
            <th class="col-md-3">작성일</th>
          </tr>
        </thead>
        <tbody id="listBody">
<!--           <tr> -->
<!--             <td> -->
<!--             	<input type="checkbox"> -->
<!--             </td> -->
<%--             <td><a href="${cPath }/project/projReportView.do">네가</td> --%>
<!--             <td>53275531</td> -->
<!--             <td>12 May 2017</td> -->
<!--           </tr> -->
<!--           <tr> -->
<!--              <td> -->
<!--             	<input type="checkbox"> -->
<!--             </td> -->
<!--             <td>알던</td> -->
<!--             <td>53275532</td> -->
<!--             <td>15 May 2017</td> -->
<!--           </tr> -->
        </tbody>
        <tfoot>
        	<tr>
        		<td>
        			<div class="pagingArea">
					 	${pagingVO.pagingHTMLBS }			
					</div>
        		</td>
        	</tr>
        </tfoot>
      </table>
      
    </div>
    <div>
       <button type="button" class="btn btn-outline-secondary btn-md">삭제</button>
       <button type="button" class="btn btn-outline-secondary btn-md linkBtn"
      	data-url="${cPath }/project/projReportInsert.do?what=${param.what }">등록</button>
    </div>
  </div>
</div>
<form id="pagingForm">
	<input type="hidden" name="page" placeholder="page"/>
</form>

<script type="text/javascript">
let listBody = $("#listBody").on("click", "tr", function(){
	let report = $(this).data("report");
	if(report){
		location.href=CONTEXTPATH+"/project/projReportView.do?jobara="+report.repoSn + "&what=" + report.proSn;
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
		let reportList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(reportList && reportList.length > 0){
			$.each(reportList, function(index, report){
				let trTag = $("<tr id='trView'>");
				trTag.append(
  					  $("<td>").html(report.repoTitle)					
					, $("<td>").html(report.pmemId)					
					, $("<td>").html(report.repoDate)					
				).data("report", report);
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











