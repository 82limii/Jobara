<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
#search1 {
	width : 400px;
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
        <div class="col-lg-9">
		<section class="ftco-section">
			<div class="container">
				<div class="row justify-content-center">
					<div class="card-body">
					<div class="col-md-12 text-center">
						<h4 class="heading-section">자주묻는 질문</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="table-wrap">
							<table class="table myaccordion table-hover" id="accordion">
							  <thead>
							    <tr>
							      <th class="text-center">No</th>
							      <th class="text-center">질문유형코드</th>
							      <th class="col-8 text-center">제목</th>
							    </tr>
							  </thead>
							  <tbody id="listBody">
							  </tbody>
							  <tfoot>
								<tr>	
									<td colspan="5">
						 				<div class="pagingArea">
						 					
										</div>
										<div id="searchUI">
											<select name="searchType" class="col-2 custom-select">
												<option value>키워드</option>
												<option value="title">제목</option>
												<option value="content">내용</option>
											</select>
											<input class="form-control-sm col-5" id="search1" type="text" name="searchWord"/>
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
			</div>
		</section>
		</div>
    </div>
</div>
<form id="searchForm"> 
	<input type="hidden" name="page" placeholder="page"/>
	<input type="hidden" name="searchType" placeholder="searchType"/>
	<input type="hidden" name="searchWord" placeholder="searchWord"/>
</form>

<script type="text/javascript">
let listBody = $("#listBody") 
// .on("click", "tr", function(){
// 	let faq = $(this).data("faq");
// 	if(faq)
// 		location.href = CONTEXTPATH+"/faq/admin/faqView.do?what="+faq.faqSn;
// });

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
		let faqList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(faqList && faqList.length > 0){
			$.each(faqList, function(index, faq){
				let trTag = $("<tr>").attr({
					"data-toggle": "collapse",
					"data-target": "#collapse"+index,
					"aria-expanded":"false",
					"aria-controls":"collapseOne",
					"class":"collapsed"
				});
				trTag.append(
					$("<th>").attr("scope","row").html(faq.rnum)
					, $("<td>").html(faq.combSnNm)
					, $("<td>").attr("class","col-8 ").html(faq.faqTitle)
				).data("faq", faq);
				
				let trTag2 = $("<tr>");
				trTag2.append(
					$("<td>").attr({
						"colspan":"5",
						"id":"collapse"+index,
						"class":"collapse acc",
						"data-parent":"#accordion"
					}).append(
						$("<p>").html(faq.faqContents)
						,$("<hr>")
						,$("<p>").html(faq.faqReq)
					)
				).data("faq",faq);
				
				trTags.push(trTag);
				trTags.push(trTag2);
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
</script>

