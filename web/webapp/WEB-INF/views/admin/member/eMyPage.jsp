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
						<th colspan="3">기업정보조회</th>
					</tr>
				</thead>
					<tr>
						<th>기업로고사진</th>
						<td>
							<img id="thum" src="<spring:url value='/image/${ememberList.ememPic }'/>" />
						</td>
					</tr>
					<tr>
						<th>기업아이디</th>
						<td>${ememberList.ememId}</td>
					</tr>
					<tr>
						<th>사업자등록번호</th>
						<td>${ememberList.ememNum}</td>
					</tr>
					<tr>
						<th>기업명</th>
						<td>${ememberList.ememNm}</td>
					</tr>
					<tr>
						<th>대표자명</th>
						<td>${ememberList.ememCeo}</td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>${ememberList.ememZip}</td>
					</tr>
					<tr>
						<th>주소1</th>
						<td>${ememberList.ememBadd}</td>
					</tr>
					<tr>
						<th>주소2</th>
						<td>${ememberList.ememDadd}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${ememberList.ememTel}</td>
					</tr>
					<tr>
						<th>FAX번호</th>
						<td>${ememberList.ememFax}</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${ememberList.ememEmail}</td>
					</tr>
					<tr>
						<th>담당자명</th>
						<td>${ememberList.ememMan}</td>
					</tr>
					<tr>
						<th>담당자 연락처</th>
						<td>${ememberList.ememMantel}</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="btn btn-outline-success" type="button" value="목록으로" onclick="location.href='${cPath}/member/admin/ememberList.do'" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
