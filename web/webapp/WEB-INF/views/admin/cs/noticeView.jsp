<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container">
	<div class="row">
	    <div class="col-xs-12 col-md-12">
	    <br>
	    <h2 class="text-center">공지사항</h2><p>&nbsp;</p>
	    <div class="table table-responsive">
	        <table class="table">
	        <tr>
	            <th class="success col-lg-2">제목</th>
	            <td class="col-lg-4">${notice.noticeTitle }</td>
	            <th class="success col-lg-2">등록일</th>
	            <td class="col-lg-4">${notice.noticeDate }</td>
	        </tr>
	         
	        <tr>
	            <th class="success col-lg-3">내용</th>
	            <td colspan="3">${notice.noticeContents }</td>
	        </tr>
	         
	        <tr>
	            <td colspan="4" class="text-center">
	    			<input type="button" class="btn btn-success linkBtn right" value="목록" data-url="${cPath }/notice/admin/noticeList.do">
	    			<c:url value="${cPath }/notice/admin/noticeUpdate.do" var="updateURL">
	    				<c:param name="what" value="${notice.noticeSn }" />
	    			</c:url>
	    			<input type="button" class="btn btn-primary linkBtn right" value="수정" data-url="${updateURL }">          
	    			<button type="button" class="btn btn-danger right" data-toggle="modal" data-target="#exampleModal">
					  삭제
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">공지사항 삭제</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body center">
					      	  정말로 삭제하시겠습니까?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					        <button type="button" class="btn btn-primary linkBtn" data-url="${cPath }/notice/admin/noticeDelete.do?what=${notice.noticeSn}">확인</button>
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

 