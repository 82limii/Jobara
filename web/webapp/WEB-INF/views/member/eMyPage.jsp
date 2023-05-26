<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<!-- 장인슬 -->
<style>
#thum {
	width: auto;
	height: 100px;
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
        <div class="col-lg-9">
		<table class="col-md-12 table">
			<thead class="thead-dark">
				<tr>
					<th colspan="3" style="text-align: center;">기업정보조회</th>
				</tr>
			</thead>
				<tr>
					<th>기업로고사진</th>
					<td>
						<img id="thum" src="<spring:url value='/image/${emember.ememPic }'/>" />
					</td>
				</tr>
				<tr>
					<th>기업아이디</th>
					<td>${emember.ememId}</td>
				</tr>
				<tr>
					<th>사업자등록번호</th>
					<td>${emember.ememNum}</td>
				</tr>
				<tr>
					<th>기업명</th>
					<td>${emember.ememNm}</td>
				</tr>
				<tr>
					<th>대표자명</th>
					<td>${emember.ememCeo}</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${emember.ememZip}</td>
				</tr>
				<tr>
					<th>주소1</th>
					<td>${emember.ememBadd}</td>
				</tr>
				<tr>
					<th>주소2</th>
					<td>${emember.ememDadd}</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${emember.ememTel}</td>
				</tr>
				<tr>
					<th>FAX번호</th>
					<td>${emember.ememFax}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${emember.ememEmail}</td>
				</tr>
				<tr>
					<th>담당자명</th>
					<td>${emember.ememMan}</td>
				</tr>
				<tr>
					<th>담당자 연락처</th>
					<td>${emember.ememMantel}</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:url value="/member/com/ememberUpdate.do" var="updateURL" />
						<input type="button" value="내정보 수정" class="btn btn-primary" onclick="location.href='${updateURL}';" />
						<input type="button" value="비밀번호 변경" class="btn btn-primary" onclick="location.href='${cPath }/find/updatePwEmem.do';" />
						
					</td>
				</tr>
			</table>
		</div>
	</div>	
</div>
