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
#trView:hover {
	background-color: lightgray;
}
</style>
  <div class="col-lg-12 grid-margin stretch-card">
  <div class="card">
    <div class="card-body">
      <h3 class="card-title">전달 사항</h3>
      <table class="table">
        <thead>
          <tr>
            <th class="col-md-1"><input id="allCheck" type="checkbox" name="allCheck" /></th>
            <th class="col-md-3">제목</th>
            <th class="col-md-2">중요도</th>
            <th class="col-md-2">등록일</th>
          </tr>
        </thead>
        <tbody id="listBody">
<!--           <tr> -->
<!--             <td> -->
<!--             	<input type="checkbox"> -->
<!--             </td> -->
<!--             <td>네가</td> -->
<!--             <td>53275531</td> -->
<!--             <td>12 May 2017</td> -->
<!--             <td>3조 팀장 갓</td> -->
<!--           </tr> -->
        </tbody>
        <tfoot>
        	<tr>
				<td colspan="5">
					<div class="pagingArea">
					 		${paging.pagingHTMLBS }			
					</div>
				</td>
        	</tr>
        </tfoot>
      </table>
      
    </div>
    <div>
    <input type="button" class="btn btn-outline-secondary btn-md" onclick="noticeDel()" value="삭제"
    	data-toggle="modal" data-target="#delModal"
    />
      <input type="button" class="btn btn-outline-secondary btn-md linkBtn" value="등록"
      	data-url="${cPath }/project/projNoticeInsert.do?what=${param.what}"
      />
    </div>
  </div>
</div>
<!-- delete Modal -->
<div class="modal fade" id="delModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
	<form id="deleteForm" method="post" action='<c:url value="/project/projNoticeDelete.do?what=${param.what }"/>'>
		<input type="hidden" name="notiSn" value="${notice.notiSn}" />
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        비밀번호 : <input type="password" name="boPass" id="boPass"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger" id="saveChange">삭제</button>
	      </div>
	    </div>
	</form>
  </div>
</div>
<form id="pagingForm"> 
	<input type="hidden" name="page" placeholder="page"/>
</form>
<script type="text/javascript">
	let listBody = $("#listBody").on("click", "tr", function(){
		let notice = $(this).data("notice");
		if(notice){
			location.href=CONTEXTPATH+"/project/projNoticeView.do?jobara="+notice.notiSn + "&what=" + notice.proSn;
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
		let noticeList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(noticeList && noticeList.length > 0){
			$.each(noticeList, function(index, notice){
				let trTag = $("<tr id='trView'>");
				trTag.append(
					$("<td><input type='checkbox' name='rowCheck' value='"+notice.notiSn+"'>")						
					, $("<td style='text-align : left;'>").html(notice.notiTitle)					
					, $("<td>").html(notice.notiImportance)					
					, $("<td>").html(notice.notiDate)									
				).data("notice", notice);
				trTags.push(trTag);
			});
		}else{
			trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"5"	
						}).html("검색 조건에 맞는 게시글이 없음.")		
					)	
				);
			}// if~else end
		listBody.append(trTags);
		pagingArea.html(pagingHTML);
	}
});

let pageLink = $(".page-link").on("click", function() {
	let pageVal = $(this).text();
	$("[name=page]").val(pageVal);
});

$(function(){
	var chkObj = document.getElementsByName("rowCheck");
	var rowCnt = chkObj.length;
	
	$("input[name='allCheck']").click(function(){
		var chk_listArr = $("input[name='rowCheck']");
		for(var i=0; i<chk_listArr.length; i++){
			chk_liskArr[i].checked = this.checked;
		}
	});
	$("input[name='rowCheck']").click(function(){
		if($("input[name='rowCheck']:checked").length == rowCnt){
			$("input[name='allCheck']")[0].checked = true;
		} else {
			$("input[name='allCheck']")[0].checked = false;
		}
	});
	
});
function noticeDel(){
	var url = "delete";
	var valueArr = new Array();
	var list = $("input[name='rowCheck']");
	for(var i=0; i<list.length; i++){
		if(list[i].checked){
			valueArr.push(list[i].value);
		}
	}
	if(valueArr.length == 0){
		alert("선택된 글이 없습니다.");
	} else {
		var chk = confirm("정말 삭제하시겠습니까?");
		$.ajax({
			url : "/project/projNoticeDelete.do",
			method : "post",
			data : {
				valueArr : valueArr
			},
			success : function(resp) {
				if(result = 1){
					alert("삭제 성공");
					location.replace("${cPath}/project/projNotice.do")
				}
			},
			error : function(xhr, status, error) {
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		});
	}
}


	
	
</script>




