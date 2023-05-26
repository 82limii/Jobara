<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
#delBtn, #ban, #listBtn, #updateBtn {
	float: right;
}
.preBtn {
	margin: 5px;
	margin-top: 0;
}
#replyText{
	margin-right: 0;
	float: left;
}
#replyBtn{
	float: left;
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
			  <table class="table table-bordered">
			  	<tbody>
			  		<tr>
				  		<th colspan="4">${askIt.boardTitle }</th>
			  		</tr>
			  		<tr>
				  		<td>${askIt.pmemNm }</td>
				  		<td>${askIt.boardDate }</td>
				  		<td colspan="2">
		  					<button class="btn btn-outline-danger">
								 <i class="fa fa-bell"></i>
									신고
							</button>
				  		</td>
			  		</tr>
			  		<tr>
			  			<td colspan="4">
			  				${askIt.boardContents }
			  			</td>
			  		</tr>
			  		<tr>
			  			<td>첨부파일</td>
			  			<td colspan="3">
			  				<c:if test="${not empty attatchList }">
			  					<c:forEach items="${attatchList }" var="attatch">
			  						<a href="${cPath }/download.do?what=${attatch.attSn}&num=${attatch.attNum}">
				  						<i class="fa fa-paperclip" aria-hidden="true"></i>&nbsp;${attatch.attNm }
	             					</a>&nbsp;&nbsp;&nbsp;&nbsp;
			  					</c:forEach>
			  				</c:if>
			  			</td>
			  		
			  		</tr>
			  	</tbody>
			  </table>
			  <table class="table table-bordered" id="replyTable">
			  	<thead class="table-warning">
				  	<tr>
				  		<th colspan="4">댓글</th>
				  	</tr>
			  	</thead>
				  	<tr>
				  		<th class="col-2">작성자</th>
				  		<th class="col-8">내용</th>
				  		<th class="col-2">작성일</th>
				  	</tr>
			  	<tbody id="listBody" class="overflow-auto">
			  	</tbody>
			  </table>
			  <div id="pagingArea"></div>
			  <form method="post" id="replyInsertForm"
			   action="${cPath }/board/${askIt.boardSn }/reply" >
			  	<input type="hidden" name="commSn" />
			  	<input type="hidden" name="boardSn" value="${askIt.boardSn }" />
			  <div>
				<input type="text" class="form-control col-6" name="commContents" id="replyText"/>
				<input type="submit" class="btn btn-outline-dark col-2" id="replyBtn" value="댓글등록"/><br>
			  </div>
			  </form>
			<form id="searchForm" action="${cPath }/board/${askIt.boardSn }/reply" method="get">
				<input type="hidden" name="page" />
			</form>  
			<!--  댓글 수정 Modal -->
			  <div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
				 <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="replyModalLabel">댓글 수정</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				        	<span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form action="${cPath }/board/${askIt.boardSn}/reply" method="post">
				      	<input type="hidden" name="_method" value="put">
				      	<input type="hidden" name="commSn" required/>
				      	<input type="hidden" name="boardSn" value="${askIt.boardSn }"/>
					      <div class="modal-body">
					      	<table class="table">
					      		<tr>
					      			<td colspan="2">
										<div class="input-group">
										<input class="form-control col-12" placeholder="내용 200자 이내" name="commContents" />
										</div>
									</td>
					      		</tr>
					      	</table>
					      </div>
					      <div class="modal-footer">
					        <button type="submit" class="btn btn-outline-primary">수정</button>
					        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
					      </div>
				      </form>
				    </div>
				  </div>
				</div>
				<!-- 댓글 수정 Modal end -->
			  <br>
			  <div class="form-group">
				<input type="button" id="listBtn" class="btn btn-outline-success linkBtn preBtn" value="목록"
					data-url="${cPath }/board/askItList.do"
				/>
			 	 <c:choose>
  					<c:when test="${authMember.pmemId eq askIt.pmemId }">
		  				<input id="delBtn" type="button" class="btn btn-outline-danger preBtn" value="삭제" 
		  				data-toggle="modal" data-target="#deleteModal"
		  				/>
		  				<c:url value="/board/askItUpdate.do" var="updateURL">
							<c:param name="what" value="${askIt.boardSn }"/>
						</c:url>
						<input type="button" id="updateBtn" class="btn btn-outline-secondary btn-md linkBtn btnControl preBtn" value="수정"
							data-url="${updateURL }"
						/>
  					</c:when>
  				</c:choose>
			  </div>
			<!--  댓글 삭제 Modal -->
			 <div class="modal fade" id="replyDeleteModal" tabindex="-1" aria-labelledby="replyDeleteModalLabel" aria-hidden="true">
				 <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="replyModalLabel">댓글 삭제</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				        	<span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form action="${cPath}/board/${askIt.boardSn}/reply" method="post">
				      	<input type="hidden" name="_method" value="delete">
				      	<input type="hidden" name="boardSn" required value="${askIt.boardSn }"/>
				      	<input type="hidden" name="commSn" required/>
					      <div class="modal-body">
				   				<p>삭제하시겠습니까?</p>
					      </div>
					      <div class="modal-footer">
					        <button type="submit" class="btn btn-primary">삭제</button>
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
					      </div>
				      </form>
				    </div>
				  </div>
				</div>
			  
			  
			  
			  <!-- 게시글 삭제 확인 Modal -->
				<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<form id="deleteForm" method="post" action='<c:url value="/board/askItDelete.do"/>'>
						<input type="hidden" name="boardSn" value="${askIt.boardSn }" />
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					   		<p>삭제하시겠습니까?</p>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					        <button type="submit" class="btn btn-danger" id="saveChange">삭제</button>
					      </div>
					    </div>
					</form>
				  </div>
				</div>
			<!-- Modal end -->
			</div>
	</div>
</div>
</div>
<form id="searchForm" action="${cPath }/board/${askIt.boardSn }/reply" method="get">
	<input type="hidden" name="page"  />
</form>
<script type="text/javascript" src="${cPath }/resources/js/board/freeReply.js"></script>

