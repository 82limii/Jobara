<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.nav {
	display: inline-block;
}
#menu {
	vertical-align: middle;
}
.right {
	float: right;
}
</style>
<c:set var="proBoardList" value="${pagingVO.dataList }" />
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <c:choose>
						<c:when test="${fn:contains(menu.menuSort, 'com') }">
		                    <span>프로젝트 공고</span>
						</c:when>
						<c:otherwise>
		                    <span>기업페이지</span>
						</c:otherwise>
					</c:choose>
                </div>
                <ul>
                	<c:forEach items="${menuList }" var="menu">
						<c:choose>
							<c:when test="${not empty menu.menuUpper }">
								<br>
								<h5>${menu.menuUpper }</h5>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty menu.menuFlag }">
										<c:if test="${menu.menuFlag eq 'ememId' }">
											<c:url value="${menu.menuUrl }" var="viewURL">
												<c:param name="what" value="${proBoardList[0].ememId }" />
											</c:url>
											<li><a href="${cPath }/${viewURL }">${menu.menuName }</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<li><a href="${cPath }/${menu.menuUrl }">${menu.menuName }</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
                </ul>
            </div>
        </div>
        <div class="col-lg-9 grid-margin stretch-card">
             <div class="card-body">
		        <c:choose>
					<c:when test="${fn:contains(menu.menuSort, 'com') }">
				        <h4>내 공고 관리</h4><br>
					</c:when>
					<c:otherwise>
						<h4>프로젝트 공고 모아보기</h4><br>				
					</c:otherwise>
				</c:choose>
				 <table id="boardTb" class="table table-bordered">
				 	<tbody id="listBody">
				 		<tr>
				 			<th class="col-1" style="text-align: center;">순번</th>
							<th class="col-8" style="text-align: center;">공고명 및 지원조건</th>
							<th class="col-2" style="text-align: center;">공고등록일/<br>프로젝트 기간</th>
				 		</tr>
				 	<c:choose>
				 		<c:when test="${empty proBoardList }">
				 			<tr>
				 				<td colspan="3">해당 기업에 맞는 공고를 찾을 수 없습니다.</td>
				 			</tr>
				 		</c:when>
				 		<c:otherwise>
				 			<c:forEach items="${proBoardList }" var="proBoard">
				 				<c:url value="/proboard/proBoardView.do" var="viewURL">
									<c:param name="what" value="${proBoard.probSn }" />
								</c:url>
								<c:url value="/proboard/com/proBoardView.do" var="viewURLCom">
									<c:param name="what" value="${proBoard.probSn }" />
								</c:url>
								<tr>
								<td style="text-align: center;">${proBoard.rnum }</td>
								<td>
								<c:choose>
									<c:when test="${authMember.userType eq 'Emember'}">
											<a href="${viewURLCom }">${proBoard.probTitle }</a>
									</c:when>
									<c:otherwise>
											<a href="${viewURL }">${proBoard.probTitle }</a>
									</c:otherwise>
								</c:choose>
									<hr>
									<h6>
										${proBoard.probEdu }&nbsp;&nbsp;&nbsp;
										|&nbsp;&nbsp;&nbsp;${proBoard.probCar }&nbsp;&nbsp;&nbsp;
										|&nbsp;&nbsp;&nbsp;${proBoard.probLoc }
										<br>
										${proBoard.probDept }&nbsp;&nbsp;|&nbsp;&nbsp;${proBoard.probSkill }
									</h6>
								</td>
								<td style="text-align: center;">
									${proBoard.probDate }
									<hr>
									${proBoard.probStartd }
									<br>
									~&nbsp;${proBoard.probEndd }
								</td>
							</tr>
				 			</c:forEach>
				 		</c:otherwise>
				 	</c:choose>
				 </tbody>
				 
				 <tfoot>
					<tr>	
						<td colspan="3">
							<div class="pagingArea">
								${pagingVO.pagingHTMLBS }
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
     	   </div>
		</div>
    </div>
</div>
<form id="searchForm" hidden="true">
	<input type="text" name="page" />
</form>

<script type="text/javascript" src="${cPath }/resources/js/paging.js"></script>
<script type="text/javascript">
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let searchFrom = $("#searchForm").paging({
		listBody:listBody
		, pagingArea:pagingArea
	});
</script>