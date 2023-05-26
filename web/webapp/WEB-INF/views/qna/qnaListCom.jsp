<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		        <h3>Q&A</h3><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-2">글번호</th>
							<th class="col-md-6">제목</th>
							<th class="col-md-2">작성자 ID</th>
							<th class="col-md-2">등록일</th>
					</thead>
					<tbody id="listBody">
<!-- 						<tr> -->
<!-- 							<td>2</td> -->
<%-- 							<td><a href="${cPath }/qna/qnaView.do">질문드립니다.</a></td> --%>
<!-- 							<td>aaaa1234</td> -->
<!-- 							<td>2022.02.12</td> -->
<!-- 							<td><button class="btn btn-warning disabled">대기</button></td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>1</td> -->
<!-- 							<td>질문드립니다.</td> -->
<!-- 							<td>aaaa1234</td> -->
<!-- 							<td>2022.02.12</td> -->
<!-- 							<td><button class="btn btn-success disabled">완료</button></td> -->
<!-- 						</tr> -->
					</tbody>
					<tfoot>
						<tr>	
							<td colspan="4">
				 				<div class="pagingArea">
				 					
								</div>
									<c:choose>
										<c:when test="${authMember.userType eq 'Pmember'}">
											<input type="button" class="site-btn linkBtn right" value="등록"
												data-url="${cPath }/qna/qnaInsert.do"
											/><br>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								<div id="searchUI">
									<select name="searchType" class="col-2 custom-select">
										<option value>키워드</option>
										<option value="title">제목</option>
										<option value="content">내용</option>
										<option value="writer">작성자ID</option>
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
	let qna = $(this).data("qna");
	if(qna)
		location.href = CONTEXTPATH+"/qna/com/qnaView.do?what="+qna.qnaSn;
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
		let qnaList = resp.paging.dataList;
		let pagingHTML = resp.paging.pagingHTMLBS;
		
		let trTags = [];
		
		if(qnaList && qnaList.length > 0){
			$.each(qnaList, function(index, qna){
				let trTag = $("<tr>");
				if(qna.pmemId == null){
					trTag.append(
						$("<td>").html(qna.qnaSn)
						, $("<td>").html(qna.qnaTitle)
						, $("<td>").html("관리자")
						, $("<td>").html(qna.qnaDate)
					).data("qna", qna);
				}else{
					trTag.append(
						$("<td>").html(qna.qnaSn)
						, $("<td>").html(qna.qnaTitle)
						, $("<td>").html(qna.pmemId)
						, $("<td>").html(qna.qnaDate)
					).data("qna", qna);
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