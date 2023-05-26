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
                    <span>마이페이지</span>
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
						<th colspan="3">개인정보조회</th>
					</tr>
				</thead>
				<tr>
					<th>프로필사진</th>
					<td><img id="thum" src="<spring:url value='/image/${pmember.pmemPic }'/>" /></td>
				</tr>
				<tr>
					<th>회원아이디</th>
					<td>${pmember.pmemId}</td>
				</tr>
				<tr>
					<th>회원명</th>
					<td>${pmember.pmemNm}</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<c:forEach items="${genList }" var="gen">
							<c:if test="${gen.commCd eq pmember.pmemSex }">
								${gen.commNm }
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>${pmember.pmemBir}</td>
				</tr>
				<tr>
					<th>휴대폰번호</th>
					<td>${pmember.pmemTel}</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${pmember.pmemZip}</td>
				</tr>
				<tr>
					<th>주소1</th>
					<td>${pmember.pmemBadd}</td>
				</tr>
				<tr>
					<th>주소2</th>
					<td>${pmember.pmemDadd}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${pmember.pmemEmail}</td>
				</tr>
				<tr>
					<th>재직상태</th>
					<td>
						<c:forEach items="${tenList }" var="ten">
							<c:if test="${ten.commCd eq pmember.empStateCd }">
								${ten.commNm }
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>기술스택</th>
					<td>${pmember.pmemSkill}</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<c:url value="/member/pmemberUpdate.do" var="updateURL" />
						<input type="button" value="내정보 수정" class="btn btn-primary" onclick="location.href='${updateURL}';" />
						<input type="button" value="비밀번호 변경" class="btn btn-primary" onclick="location.href='${cPath }/find/updatePwPmem.do';" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
