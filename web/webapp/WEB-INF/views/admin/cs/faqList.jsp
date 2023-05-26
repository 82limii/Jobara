<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.btn {
	width: 65px;
	height: 45px;
	margin-left: 5px;
	margin-right: 5px;
}
</style>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
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
							      <th>No</th>
							      <th>질문유형코드</th>
							      <th class="col-8">제목</th>
							    </tr>
							  </thead>
							  <tbody id="listBody">
<!-- 							    <tr data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"> -->
<!-- 								    <th scope="row">1</th> -->
<!-- 								    <td>로그인 관련 질문</td> -->
<!-- 								    <td> -->
<!-- 							      		<i class="fa" aria-hidden="true"></i> -->
<!-- 					        		</td> -->
<!-- 							    </tr> -->
<!-- 							    <tr> -->
<!-- 							    	<td colspan="6" id="collapseOne" class="collapse show acc" data-parent="#accordion"> -->
<!-- 							    		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Porro iste, facere sunt sequi nostrum ipsa, amet doloremque magnam reiciendis tempore sapiente. Necessitatibus recusandae harum nam sit perferendis quia inventore natus.</p> -->
<!-- 										<input type="button" class="btn btn-warning linkBtn" value="수정" data-url="#">  -->
<!-- 										<input type="button" class="btn btn-danger" value="삭제"> -->
<!-- 							    	</td> -->
<!-- 							    </tr> -->
							  </tbody>
							  <tfoot>
								<tr>	
									<td colspan="3">
						 				<div class="pagingArea">
						 					
										</div>
						    			<input type="button" class="btn btn-success linkBtn right" value="등록" data-url="${cPath }/faq/admin/faqInsert.do">          
										<div id="searchUI">
											<select name="searchType" class="custom-select col-2">
												<option value>키워드</option>
												<option value="title">제목</option>
												<option value="content">내용</option>
											</select>
											<input type="text" class="form-control-sm col-5" name="searchWord"/>
											<input id="searchBtn" type="button" class="btn btn-secondary" value="검색"/>
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
					, $("<td>").attr("class","col-8").html(faq.faqTitle)
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
						, $("<input>").attr({
							"type":"button",
							"class":"btn btn-secondary linkBtn",
							"value":"수정",
							"data-url":"${cPath }/faq/admin/faqUpdate.do?what="+faq.faqSn
						})
						, $("<button>").attr({
							"class":"btn btn-danger linkBtn",
							"data-url":"${cPath }/faq/admin/faqDelete.do?what="+faq.faqSn
						}).html("삭제")
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


