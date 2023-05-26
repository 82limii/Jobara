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
			    <h3 class="text-center">재직자 정보</h3>
				    <div class="table table-responsive">
				        <table class="table">
				        <tr>
				            <th class="success col-lg-2">이름</th>
				            <td class="col-lg-4">${incumbent.pmemNm }</td>
				            <th class="success col-lg-2">직급</th>
				            <td class="col-lg-4">${incumbent.empPosiCd }</td>
				        </tr>
				        <tr>
				            <th class="success col-lg-2">직무</th>
				            <td class="col-lg-4">${incumbent.empDeptCd }</td>
				            <th class="success col-lg-2">나이</th>
				            <td class="col-lg-4">${incumbent.manYear }살</td>
				        </tr>
				        <tr>
				            <th class="success col-lg-2">성별</th>
				            <td class="col-lg-4">${incumbent.pmemSex }</td>
				            <th class="success col-lg-2">입사일</th>
				            <td class="col-lg-4">${incumbent.empStartd }</td>
				        </tr>
				        <tr>
				            <th class="success col-lg-2">연봉</th>
				            <td colspan="3">${incumbent.empSalary }만원</td>
				        </tr>
				         
				        <tr>
				            <td colspan="4" class="text-center">
								<c:url value="${cPath }/incumbent/com/incumbentUpdate.do" var="updateURL">
									<c:param name="what" value="${incumbent.empSn }" />
								</c:url>
				    			<input type="button" class="btn btn-success linkBtn right" data-url="${updateURL }" value="수정" />
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
								        <button type="button" class="btn btn-primary linkBtn" data-url="${cPath }/incumbent/com/incumbentDelete.do?what=${incumbent.empSn}">확인</button>
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

 