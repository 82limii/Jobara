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
			    <h3 class="text-center">지원자 정보</h3>
				    <div class="table table-responsive">
				        <table class="table">
				        <tr>
				            <th class="success col-lg-2">이름</th>
				            <td colspan="3">${proApplicantMan.pmemNm }</td>
				        </tr>
				        <tr>
				            <th class="success col-lg-2">학력</th>
				            <td colspan="3">${proApplicantMan.ediSn }</td>
				        </tr>
				        <tr>
				        	<c:choose>
								<c:when test="${proApplicantMan.jobMonth eq null}">
						            <th class="success col-lg-2">경력</th>
						            <td colspan="3">없음</td>
								</c:when>
								<c:otherwise>
						            <th class="success col-lg-2">경력</th>
						            <td colspan="3">${proApplicantMan.jobYear }년 ${proApplicantMan.jobMonth}월</td>
								</c:otherwise>
							</c:choose>
				        </tr>
				        <tr>
				            <th class="success col-lg-2">이력서 제목</th>
				            <td colspan="3">${proApplicantMan.reTitle }</td>
				        </tr>
				         
				        <tr>
				            <td colspan="4" class="text-center">
								<c:url value="${cPath }/applicant/com/proApplicantInsert.do" var="insertURL">
									<c:param name="what" value="${proApplicantMan.reSn }" />
								</c:url>
				    			<input type="button" class="btn btn-success linkBtn right" data-url="${insertURL }" value="재직자 등록" />
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
								        <button type="button" class="btn btn-primary linkBtn" data-url="${cPath }/incumbent/com/proIncumbentDelete.do?what=${proApplicantMan.appSn }">확인</button>
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

 