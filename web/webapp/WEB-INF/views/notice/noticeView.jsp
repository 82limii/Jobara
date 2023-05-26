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
			<div class="row">
			    <div class="col-xs-12 col-md-12">
			    <br>
			    <h2 class="text-center">공지사항</h2>
			    <div class="table table-responsive">
			        <table class="table">
			        <tr>
			            <th class="success col-lg-2">제목</th>
			            <td class="col-lg-4">${notice.noticeTitle }</td>
			            <th class="success col-lg-2">등록일</th>
			            <td class="col-lg-4">${notice.noticeDate }</td>
			        </tr>
			         
			        <tr>
			            <th class="success col-lg-2">내용</th>
			            <td colspan="3">${notice.noticeContents }</td>
			        </tr>
			         
			        <tr>
			            <td colspan="4" class="text-center">
			            	<c:choose>
								<c:when test="${authMember.userType eq 'Emember'}">
					    			<input type="button" class="btn btn-success linkBtn right" value="목록" data-url="${cPath }/notice/com/noticeList.do">
								</c:when>
								<c:otherwise>
					    			<input type="button" class="btn btn-success linkBtn right" value="목록" data-url="${cPath }/notice/noticeList.do">
								</c:otherwise>
							</c:choose>
			            </td>
			        </tr>
			        </table>
			    </div>
			    </div>
			</div>
        </div>
	</div>
</div>

 