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
        <div class="col-lg-9">
			<table class="col-md-12 table" style="background-color: #fff">
				<thead class="thead-dark">
					<tr>
						<th colspan="3">개인정보조회</th>
					</tr>
				</thead>
				<tr>
					<th>프로필사진</th>
					<td><img id="thum" src="<spring:url value='/image/${pmemberList.pmemPic }'/>" /></td>
				</tr>
				<tr>
					<th>회원아이디</th>
					<td>${pmemberList.pmemId}</td>
				</tr>
				<tr>
					<th>회원명</th>
					<td>${pmemberList.pmemNm}</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>${pmemberList.pmemBir}</td>
				</tr>
				<tr>
					<th>휴대폰번호</th>
					<td>${pmemberList.pmemTel}</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${pmemberList.pmemZip}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${pmemberList.pmemBadd}</td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td>${pmemberList.pmemDadd}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${pmemberList.pmemEmail}</td>
				</tr>
				<tr>
					<th>재직상태</th>
					<td>${pmemberList.empStateCd}</td>
				</tr>
				<tr>
					<th>기술스택</th>
					<td>${pmemberList.pmemSkill}</td>
				</tr>
				<tr>
					<th>만료날짜</th>
					<td>${pmemberList.pmemExpdate}</td>
				</tr>
				<tr>
					<td colspan="2">
						<input class="btn btn-outline-success" type="button" value="목록으로" onclick="location.href='${cPath}/member/admin/pmemberList.do'" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
