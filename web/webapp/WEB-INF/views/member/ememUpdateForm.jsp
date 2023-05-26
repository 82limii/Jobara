<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import=" kr.co.jobara.enumpkg.ServiceResult"%>

    
 	<!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Main CSS-->
    <link href="${cPath }/resources/form/main.css" rel="stylesheet" media="all">
    
<!-- 장인슬 -->
<style>
.btn {
	display: inline-block;
	width: 110px;
	height: 45px;
}
#buttons {
	float: right;
}
.center {
	float: none;
	margin: 0 auto;
}
div.buttons {
	display: inline-block;
	flex: auto;
}
</style>
  
<form:form commandName="emember" id="ememberForm" method="post" enctype="multipart/form-data" action="${cPath }/member/com/ememberUpdate.do">
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
					<th colspan="3" style="text-align: center;">내 정보 수정</th>
				</tr>
			</thead>
						<tr>
							<th class="name">기업아이디</th>
							<td class="value">
								<form:input path="ememId" required="required" class="input--style-6" readonly="true" style="background-color: lightgray;"/></td>
			
						</tr>
						<tr>
							<th class="name">사업자등록번호</th>
							<td><form:input path="ememNum" class="input--style-6"/>
								<form:errors path="ememNum" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">기업명</th>
							<td><form:input path="ememNm" class="input--style-6" />
								<form:errors path="ememNm" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">대표자명</th>
							<td><form:input path="ememCeo" class="input--style-6" />
								<form:errors path="ememCeo" element="span" cssClass="error" /></td>
						</tr>		
						<tr>
							<th class="name">우편번호</th>
							<td><form:input path="ememZip" id="add1" class="input--style-6" placeholder="주소찾기 클릭" readonly="true"/>
								<form:errors path="ememZip" element="span" cssClass="error" /></td>
							<td><input class="btn btn-success btn-sm" type="button" onclick="post()" style="padding-left: 3%;"value="주소찾기"></td>	
						</tr>
						<tr>
							<th class="name">주소</th>
							<td><form:input path="ememBadd" id="add2" class="input--style-6" readonly="true"/>
								<form:errors path="ememBadd" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">상세주소</th>
							<td><form:input path="ememDadd" id="add3" class="input--style-6"/>
								<form:errors path="ememDadd" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">전화번호</th>
							<td><form:input path="ememTel" class="input--style-6" placeholder="전화번호를 '-' 없이 입력"/>
								<form:errors path="ememTel" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">FAX번호</th>
							<td><form:input path="ememFax" class="input--style-6" placeholder="FAX번호를 '-' 없이 입력"/>
								<form:errors path="ememFax" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">이메일</th>
							<td><form:input path="ememEmail" type="email" class="input--style-6" placeholder="이메일 형식으로 입력"/>
								<form:errors path="ememEmail" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">담당자명</th>
							<td><form:input path="ememMan" class="input--style-6" />
								<form:errors path="ememMan" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">담당자 연락처</th>
							<td><form:input path="ememMantel" class="input--style-6" placeholder="연락처를 '-' 없이 입력" />
								<form:errors path="ememMantel" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th>기업로고 이미지</th>
							<td>
								<input class="label--file" type="file" name="ememImg" />
							</td>
						</tr>
					</table>
				</div>
				<div id="buttons" style="text-align: center; display: inline-block;">
					<input type="submit" class="site-btn btn" value="저장" />
					<input type="reset" class="btn btn-danger" value="취소" />
				</div>
			</div>
		</div>
	</div>				
</div>
</form:form>

<script type="text/javascript">
//주소 검색 api
function post(){
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);

        	var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 검색한 값을 인풋에 넣어주기 
            document.getElementById('add1').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("add2").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("add3").value = jibunAddr;
            }
        }
    }).open();
}

</script>




