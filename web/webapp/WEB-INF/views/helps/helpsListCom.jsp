<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
#insertAsk {
	float: right;
}
#boardTb {
	font-size: 10pt;
}
#search1 {
	width : 400px;
}
.hero__search__form{
	width : 600px;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-lg-3">
	            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>고객센터</span>
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
		        <h3>1:1 문의</h3><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-3">문의종류</th>
							<th class="col-md-5">제목</th>
							<th class="col-md-2">등록일</th>
							<th class="col-md-2">답변여부</th>
						</tr>
					</thead>
					<tbody id="listBody">
					</tbody>
					<tfoot>
						<tr>	
							<td colspan="4">
				 				<div class="pagingArea">
				 					
								</div>
								<c:choose>
									<c:when test="${authMember.userType eq 'Emember'}">
										<input type="button" class="site-btn linkBtn right" value="등록" data-url="${cPath }/helps/com/helpsInsert.do"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="site-btn linkBtn right" value="등록" data-url="${cPath }/helps/helpsInsert.do"/>
									</c:otherwise>
								</c:choose>
								<div id="searchUI">
									<select name="searchType" class="col-2 custom-select">
										<option value>키워드</option>
										<option value="title">제목</option>
										<option value="content">내용</option>
									</select>
									<input class="form-control-sm col-5" type="text" name="searchWord"/>
									<input id="searchBtn" type="button" class="site-btn btn" value="검색"/>
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
	let helps = $(this).data("helps");
	if(helps)
		location.href = CONTEXTPATH+"/helps/com/helpsView.do?what="+helps.helpsSn;
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
						, $("<td>").html('<button type="button" class="btn btn-success btn-sm" disabled>완료</button>')
					).data("helps", helps);
				}else{
					trTag.append(
						$("<td>").html(helps.helpsKind)
						, $("<td>").html(helps.helpsTitle)
						, $("<td>").html(helps.helpsDate)
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