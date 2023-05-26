<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<style>
#portM {
	width: 80px;
	height: 45px;
	float: left;
}

#portS {
	float: right;
	width: 80px;
	height: 45px;
	margin: 5px;
	margin-top: 0;
}

#portR {
	float: right;
	width: 80px;
	height: 45px;
	margin: 5px;
	margin-top: 0;
}
</style>
<form:form method="post" id="portfolioForm" class="form-group" modelAttribute="portfolio" enctype="multipart/form-data">
<div class="container">
	<div class="row">
		<div class="col-lg-3">
			<div class="hero__categories">
				<div class="hero__categories__all">
					<i class="fa fa-bars"></i> <span>마이페이지</span>
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
			<div class="card-body">
				<div>
					<h4 class="heading-section">포트폴리오 등록</h4>
				</div>


				<table class="table">
					<tr>
						<td colspan="2">
						<div class="col-12 row attatch">
							<label for="attatchId" class="col-2">첨부파일</label>
							<div class="col-10" id="attDiv">
								<input type="file" path="portfolioFiles" name="portfolioFiles" class="form-control col-12" />
							</div>
							<form:errors path="portfolioFiles" element="span" cssClass="error"/>
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button id="portM" type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/myPage/portfolioList.do">목록</button>
							<button id="portR" type="reset" class="btn btn-danger btnSize">취소</button>
							<button id="portS"  type="submit" class="btn btn-success btnSize">저장</button>
							
						</td>
					</tr>
				</table>

			</div>
		</div>
	</div>
</div>
</form:form>
