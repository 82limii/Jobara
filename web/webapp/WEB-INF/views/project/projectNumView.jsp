<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.main-section .table th { width: 120px; }
.main-section .table td { width: 280px; text-align: left; }
</style>

<div class="container">
	<div class="row">
        <div class="col-lg-12">
			<section class="main-section">
				<div class="card-body"><h3>연락처 상세보기</h3></div>
				<table class="table table-bordered">
					<tr>
						<th>거래처명</th>
						<td>${contact.contacBuyern }</td>
						<th>부서명</th>
						<td>${contact.contacName }</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${contact.contacPers }</td>
						<th>직급</th>
						<td>${contact.contacPosiCd }</td>
					</tr>	
					<tr>
						<th>이메일</th>
						<td>${contact.contacEmail }</td>
						<th>전화번호</th>
						<td>${contact.contacTel }</td>
					</tr>	
					<tr>
						<th>거래처 주소</th>
						<td>${contact.contacBuyera }</td>
						<th>거래처 번호</th>
						<td>${contact.contacBuyert }</td>
					</tr>	
				</table>
				<div class="btns">
				<c:choose>
					<c:when test="${authMember.userType eq 'Emember'}">
						<button type="button" class="btn btn-outline-secondary btn-md" data-toggle="modal" data-target="#exampleModal">
						  삭제
						</button>
						
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">연락처 삭제</h5>
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
								        <button type="button" class="btn btn-primary linkBtn" data-url="${cPath }/project/com/projPhoneNumDelete.do?what=${contact.contacSn}">확인</button>
									</c:when>
									<c:otherwise>
								        <button type="button" class="btn btn-primary linkBtn" data-url="${cPath }/project/projPhoneNumDelete.do?what=${contact.contacSn}">확인</button>
									</c:otherwise>
								</c:choose>
						      </div>
						    </div>
						  </div>
						</div>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
					<c:choose>
						<c:when test="${authMember.userType eq 'Emember'}">
							<c:url value="${cPath }/project/com/projPhoneNumUpdate.do" var="updateURL">
								<c:param name="what" value="${contact.contacSn }" />
							</c:url>
							<input type="button" class="btn btn-outline-secondary btn-md  linkBtn" value="수정" data-url="${updateURL }"/>
							<c:url value="${cPath }/project/com/projPhoneNum.do" var="listComURL">
								<c:param name="what" value="${contact.proSn }" />
							</c:url>
					  		<input type="button" class="btn btn-outline-secondary btn-md linkBtn" value="목록" data-url="${listComURL }" />		
						</c:when>
						<c:otherwise>
							<c:url value="${cPath }/project/projPhoneNum.do" var="listURL">
								<c:param name="what" value="${contact.proSn }" />
							</c:url>
					  		<input type="button" class="btn btn-outline-secondary btn-md linkBtn" value="목록" data-url="${listURL }" />		
						</c:otherwise>
					</c:choose>
				</div>
			</section>	
        </div>
	</div>
</div>


