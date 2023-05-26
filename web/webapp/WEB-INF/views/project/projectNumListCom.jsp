<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<style>
#boardTb {
	font-size: 10pt;
}

#btn1 {
	float:right;
}
</style>
<div class="container">
	<div class="row">
		<div class="col-lg-12">
		    <div class="card-body">
		      <h3 class="card-title">연락처 관리</h3>
		      	<table id="boardTb" class="table table-bordered">
			        <thead>
			          	<tr>
							<th class="col-md-3">기업명</th>
							<th class="col-md-3">부서명</th>
							<th class="col-md-1">이름</th>
							<th class="col-md-1">직책</th>
							<th class="col-md-2">이메일</th>
							<th class="col-md-2">전화번호</th>
						</tr>
			        </thead>
			        <tbody id="listBody">
			        </tbody>
			        <tfoot>
						<tr>	
							<td colspan="6">
				 				<div class="pagingArea">
				 					
								</div>
								<input id="btn1" type="button" class="btn btn-outline-secondary btn-md linkBtn right" value="등록" data-url="${cPath }/project/com/projPhoneNumInsert.do"/>
								<div id="searchUI row">
									<select name="searchType" class="col-2 custom-select">
										<option value>키워드</option>
										<option value="title">이름</option>
										<option value="content">기업명</option>
									</select>
									<input class="col-5" type="text" name="searchWord"/>
									<input id="searchBtn" type="button" class="site-btn btn btn-outline-secondary btn-md" value="검색"/>
								</div>
							</td>
						</tr>
					</tfoot>
		      	</table>
 			</div>
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
	let contact = $(this).data("contact");
	if(contact)
		location.href = CONTEXTPATH+"/project/com/projPhoneNumView.do?what="+contact.contacSn;
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
		let contactList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(contactList && contactList.length > 0){
			$.each(contactList, function(index, contact){
				let trTag = $("<tr>");
				trTag.append(
					$("<td>").html(contact.contacBuyern)
					, $("<td>").html(contact.contacName)
					, $("<td>").html(contact.contacPers)
					, $("<td>").html(contact.contacPosiCd)
					, $("<td>").html(contact.contacEmail)
					, $("<td>").html(contact.contacTel)
				).data("contact", contact);
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"6"	
					}).html("검색 조건에 연락처가 없음.")		
				)	
			);
		}// if~else end
		listBody.append(trTags);
		pagingArea.html(pagingHTML);
	}
});
		
</script>