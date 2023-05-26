<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        <div class="col-lg-9">
			<div class="row">
			    <div class="col-xs-12 col-md-12">
			    <br>
			    <h2 class="text-center">게시글 보기</h2>
				    <div class="table table-responsive">
				        <table class="table">
				        <tr>
				            <th class="success col-lg-3">제목</th>
				            <td colspan="3">${qna.qnaTitle }</td>
				        </tr>
				         
				        <tr>
				            <th class="success col-lg-3">첨부파일</th>
				            <td colspan="3">
					            <c:if test="${not empty attatchList}">
									<c:forEach items="${attatchList }" var="attatch">
										<a href="${cPath }/download.do?what=${attatch.attSn}&num=${attatch.attNum}">
											<i class="fa fa-paperclip" aria-hidden="true"></i>&nbsp;${attatch.attNm }
										</a>&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
								</c:if>
							</td>
				        </tr>
				         
				        <tr>
				            <th class="success col-lg-3">질문 내용</th>
				            <td colspan="3">${qna.qnaContents }</td>
				        </tr>
				         
				        <tr>
				            <td colspan="4" class="text-center">
				            		<c:choose>
										<c:when test="${authMember.userType eq 'Emember'}">
							                <c:url value="${cPath }/qna/com/qnaInsert.do" var="insertURL">
							                	<c:param name="qnaSn2" value="${qna.qnaSn }" />
							                </c:url>
							                <input type="button" class="btn btn-success linkBtn right" data-url="${insertURL }"  value="답글 쓰기" />
							                <input type="button"  class="btn btn-primary linkBtn right" data-url="${cPath }/qna/com/qnaList.do" value="목록">
										</c:when>
										<c:otherwise>
											<c:url value="${cPath }/qna/qnaUpdate.do" var="updateURL">
												<c:param name="what" value="${qna.qnaSn }" />
											</c:url>
							    			<input type="button"  class="btn btn-primary linkBtn right" data-url="${cPath }/qna/qnaList.do" value="목록">
							    			<input type="button" class="btn btn-success linkBtn right" data-url="${updateURL }" value="수정" />
										</c:otherwise>
									</c:choose>
								<button type="button" class="btn btn-danger right" data-toggle="modal" data-target="#exampleModal">
								  삭제
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body center">
								      	  정말로 삭제하시겠습니까?
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
								        <c:choose>
											<c:when test="${authMember.userType eq 'Emember'}">
										        <button type="button" class="btn btn-primary linkBtn" data-url="${cPath }/qna/com/qnaDelete.do?what=${qna.qnaSn}">확인</button>
											</c:when>
											<c:otherwise>
										        <button type="button" class="btn btn-primary linkBtn" data-url="${cPath }/qna/qnaDelete.do?what=${qna.qnaSn}">확인</button>
											</c:otherwise>
										</c:choose>
								      </div>
								    </div>
								  </div>
								</div>           
				            </td>
				        </tr>
				        </table>
				    </div>
			    </div>
			</div>
        </div>
	</div>
</div>

 