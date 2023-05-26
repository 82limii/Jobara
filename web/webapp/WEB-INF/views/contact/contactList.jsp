<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
			<div class="card-body">
		        <h3>연락처 관리</h3><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-2">기업명</th>
							<th class="col-md-2">부서명</th>
							<th class="col-md-2">이름</th>
							<th class="col-md-2">직책</th>
							<th class="col-md-2">이메일</th>
							<th class="col-md-2">전화번호</th>
						</tr>
					</thead>
					<tbody id="listBody">
<!-- 						<tr> -->
<!-- 							<td>대덕인재개발원</td> -->
<!-- 							<td>인사과</td> -->
<%-- 							<td><a href="${cPath }/contact/com/contactView.do">김승현</a></td> --%>
<!-- 							<td>과장</td> -->
<!-- 							<td><a href="#">aaa123@naver.com</a></td> -->
<!-- 							<td>010-0000-0000</td> -->
<!-- 						</tr> -->
					</tbody>
					<tfoot>
						<tr>	
							<td colspan="6">
				 				<div class="pagingArea">
				 					
								</div>
								<input type="button" class="site-btn btn linkBtn right" value="등록" data-url="${cPath }/contact/com/contactInsert.do"/>
								<div id="searchUI">
									<select name="searchType" class="col-2 custom-select">
										<option value>키워드</option>
										<option value="content">기업명</option>
										<option value="writer">이름</option>
									</select>
									<input class="col-5" type="text" name="searchWord"/>
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
	let contact = $(this).data("contact");
	if(contact)
		location.href = CONTEXTPATH+"/contact/com/contactView.do?what="+contact.contacSn;
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
