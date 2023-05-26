<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet" href="${cPath }/resources/form/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${cPath }/resources/form/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="${cPath }/resources/form/style.css">

<style>
.margin {
	margin: 5px;
}
</style>

  <div class="container col-12">
  	<h4 class="mb-5">개인 블랙리스트 목록</h4>
  		<div id="searchUI" class="form-inline">
			<select name="searchType" class="form-control">
				<option value>전체</option>
				<option value="name">이름</option>
				<option value="id">아이디</option>
			</select>
			<input type="text" name="searchWord"  class="form-control"/>
			<input type="button" class="btn btn-outline-success" value="검색" id="searchBtn"/>
			<button type="button" class="btn btn-danger linkBtn margin" data-url="#">삭제</button>
			<button type="button" class="btn btn-primary linkBtn margin" data-url="${cPath }/blackList/admin/pblackListInsert.do">등록</button>
		</div>
	<div class="table-responsive custom-table-responsive">		
		<table class="table custom-table">
		<thead>
			<tr>
				<th scope="col">
	              <label class="control control--checkbox">
	                <input type="checkbox"  class="js-check-all"/>
	                <div class="control__indicator"></div>
	              </label>
	            </th>
				<th>아이디</th>
				<th>이름</th>
				<th>신고내용</th>
				<th>신고날짜</th>
			</tr>
		</thead>
		<tbody id="listBody">
			<tr>
	            <th scope="row">
	               <label class="control control--checkbox">
	                 <input type="checkbox"/>
	                 <div class="control__indicator"></div>
	               </label>
	             </th>
	             <td>aaa</td>
	             <td>홍길떵</td>
	             <td><a href="#">내용</a></td>
	             <td>2021.10.04</td>
	        </tr>    
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example" class="right">
					  <ul class="pagination">
					    <li class="page-item">
					      <a class="page-link" href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <li class="page-item"><a class="page-link" href="#">1</a></li>
					    <li class="page-item"><a class="page-link" href="#">2</a></li>
					    <li class="page-item"><a class="page-link" href="#">3</a></li>
					    <li class="page-item">
					      <a class="page-link" href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					  </ul>
					</nav>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
</div>

<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>
<script type="text/javascript">
	$("[name=searchType]").val("${pagingVO.simpleSearch.searchType }");
	$("[name=searchWord]").val("${pagingVO.simpleSearch.searchWord}");
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	$("#searchForm").paging({
		listBody : listBody
		, pagingArea : pagingArea
		, searchUI : $("#searchUI")
		, searchBtn : "#searchBtn"
		, success : function(resp) {
			listBody.empty();
			pagingArea.empty();
			$("[name=page]").val("");
			let dataList = resp.dataList;
			let pagingHTML = resp.pagingHTMLBS;
			let trTags = [];
			if(dataList && dataList.length > 0){
				$.each(dataList, function(index, data){
					let trTag = $("<tr>");
					trTag.append(
						$("<td>").html(data.pMemId)
						, $("<td>").html(data.pMemName)
						, $("<td>").html(data.helpsContents)
					).data("blacklist", data);
					trTags.push(trTag);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr({
							colspan:"7"
						}).html("조건에 맞는 블랙리스트가 없음.")		
					)		
				);
			}
			listBody.append(trTags);
			pagingArea.html(pagingHTML);
		}
	});
</script>

<script src="/resources/form/popper.min.js"></script>
<script src="/resources/form/bootstrap.min.js"></script>
<script src="/resources/form/main.js"></script>

