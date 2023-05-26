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
#searchText {
	width: 300pt;
}
#trTag:hover{
	background-color: lightgray;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>개발자 톡톡</span>
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
		        <h3>ASK IT</h3><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-1" style="text-align: center;">글번호</th>
							<th class="col-md-6" style="text-align: center;">제목</th>
							<th class="col-md-3" style="text-align: center;">등록일</th>
							<th class="col-md-2" style="text-align: center;">작성자</th>
						</tr>
					</thead>
					<tbody id="listBody">
						
					</tbody>
					<tfoot>
						<tr>	
							<td colspan="5">
				 				<div class="pagingArea">
				 					${pagingVO.pagingHTMLBS }
								</div>
								<input id="insertAsk" type="button" class="btn btn-outline-success linkBtn" value="등록"
									data-url="${cPath }/board/askItInsert.do"
								/><br>
								<div>
<!-- 										<select class="custom-select col-2"> -->
<!-- 											<option value>키워드</option> -->
<!-- 											<option value="title">제목</option> -->
<!-- 											<option value="content">내용</option> -->
<!-- 											<option value="writer">작성자</option> -->
<!-- 										</select> -->
<!-- 										<input type="text" class="form-control-md col-lg-3" name="searchWord"/> -->
<!-- 										<input type="button" class="btn btn-outline-dark btn" id="searchBtn" value="검색"/> -->
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
	let askIt = $(this).data("askIt");
	if(askIt)
		location.href = CONTEXTPATH+"/board/askItView.do?what="+askIt.boardSn;
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
		let askItList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(askItList && askItList.length > 0){
			$.each(askItList, function(index, askIt){
				let trTag = $("<tr id='trTag'>");
				trTag.append(
					$("<td>").html(askIt.rnum).css("text-align","center")
					, $("<td>").html(askIt.boardTitle)
					, $("<td>").html(askIt.boardDate).css("text-align","center")
					, $("<td>").html(askIt.pmemNm).css("text-align","center")
				).data("askIt", askIt);
				trTags.push(trTag);
			});
		}else{
			trTags.push(
				$("<tr>").html(
					$("<td>").attr({
						colspan:"5"	
					}).html("게시글이 없습니다.")		
				)	
			);
		}// if~else end
		listBody.append(trTags);
		pagingArea.html(pagingHTML);
	}
});
		
</script>








