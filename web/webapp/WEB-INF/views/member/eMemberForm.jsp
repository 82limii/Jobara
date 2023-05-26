<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import=" kr.co.jobara.enumpkg.ServiceResult"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    
 	<!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Main CSS-->
    <link href="${cPath }/resources/form/main.css" rel="stylesheet" media="all">
    
<!-- 장인슬 -->
<style>
.btn {
	display: inline-block;
	width: 120px;
	height: 45px;
}
#buttons {
	float: right;
}
.center {
	float: none;
	margin: 0 auto;
}
#com {
	margin-left: 5%;
}
div {
	order: none; 
	background: transparent;
}
</style>
  
<form:form commandName="emember" id="ememberForm" method="post" enctype="multipart/form-data" >
    <div class="center page-wrapper col-lg-9">
        <div class="wrapper">
            <div class="card card-6">
                <div class="card-heading">
                    <h2 class="title">기업 회원 가입</h2>
                    <h4 style="color: red;">모두 기입하여 주십시오.</h4>
                </div>
                <div class="card-body">
                  <div class="form-row">  
					<table class="col-lg-12">
						<tr>
							<th class="name">기업아이디</th>
							<td class="value">
								<form:input path="ememId" required="required" class="input--style-6" placeholder="아이디 중복체크 클릭" readonly="true"/>
								<form:errors path="ememId" element="span" cssClass="error" />	
							<td>	
								<input class="btn btn-success btn-sm" id="idCheckBtn" type="button" data-toggle="modal" data-target="#exampleModal" style="font-size: 11pt; padding-left: 5%; margin-left: -28%;" value="아이디중복체크"/>
							</td>
								<c:if test="${command eq 'INSERT' }">
									<!--모달 -->
									<div class="modal" id="exampleModal" tabindex="-1" role="dialog" >
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title">아이디 중복체크</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <div class="modal-body">
											<input type="text" name="userInputId" id="userInputId" placeholder="사용할 아이디를 입력하세요."/>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-secondary" data-dismiss="modal">창닫기</button>
									        <button type="button" class="btn btn-secondary" id="auth">인증하기</button>
									        <button type="button" class="btn btn-primary" id="saveChange">사용하기</button>
									      </div>
									    </div>
									  </div>
									</div>
								</c:if>
						</tr>
						<tr>
							<th class="name">비밀번호</th>
							<td><form:input type="password" path="ememPass" class="input--style-6" placeholder="8~16자 영문 ,숫자등을 사용하세요."/>
								<form:errors path="ememPass" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th class="name">사업자등록번호</th>
							<td><form:input path="ememNum" class="input--style-6" placeholder="사업자등록번호를 '-' 없이 입력"/>
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
							<td><input class="btn btn-success btn-sm" type="button" onclick="post()" style="padding-left: 10px; margin-left: -28%;" value="주소찾기"></td>	
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
								<span class="error">${errors["ememPic"] }</span>
							</td>
						</tr>
					</table>
				</div>
				<div id="buttons">
					<input type="submit" class="site-btn btn" value="저장" />
					<input type="reset" class="btn btn-danger" value="취소" onclick="location.href='${cPath}';"/>
				</div>
			</div>
		</div>
	</div>				
</div>
</form:form>

<script type="text/javascript">

function fn_save(){
	var frm = $("#ememberForm");
	
	alert("가입성공");
	frm.submit();
}
// function fn_save(){

// 	var frm = $("#ememberForm");
	
// 	var alrtMessage = "";
// 	var id = $("#ememId").val();
// 	var pass = $("#ememPass").val();
// 	var num = $("#ememNum").val();
// 	var nm = $("#ememNm").val();
// 	var ceo = $("#ememCeo ").val();
// 	var zip = $("#ememZip").val();
// 	var dadd = $("#ememDadd").val();
// 	var tel = $("#ememTel").val();
// 	var fax = $("#ememFax").val();
// 	var email = $("#ememEmail").val();
// 	var man = $("#ememMan").val();
// 	var manTel = $("#ememMantel").val();
	
// 	console.log(id)
	
// 	if(id == null || id == "" || id == undefiend)
// 		alrtMessage += "아이디\n";
// 	if(pass == null || pass == "" || pass == undefiend)
// 		alrtMessage += "비밀번호\n";
// 	if(nm == null || nm == "" || num == undefiend)
// 		alrtMessage += "사업자등록번호\n";
// 	if(bir == null || bir == "" || nm == undefiend)
// 		alrtMessage += "기업명\n";
// 	if(sex == null || sex == "" || ceo == undefiend)
// 		alrtMessage += "대표자명\n";
// 	if(zip == null || zip == "" || zip == undefiend)
// 		alrtMessage += "우편번호\n";
// 	if(dadd == null || dadd == "" || dadd == undefiend)
// 		alrtMessage += "상세주소\n";
// 	if(tel == null || tel == "" || tel == undefiend)
// 		alrtMessage += "전화번호\n";
// 	if(tel == null || tel == "" || fax == undefiend)
// 		alrtMessage += "FAX번호\n";
// 	if(tel == null || tel == "" || email == undefiend)
// 		alrtMessage += "이메일\n";
// 	if(tel == null || tel == "" || man == undefiend)
// 		alrtMessage += "담당자명\n";
// 	if(tel == null || tel == "" || manTel == undefiend)
// 		alrtMessage += "담당자연락처\n";

	
// 	if(alrtMessage.length!=0){
// 		alert(alrtMessage+"위 항목을 입력해주세요.");
// 		return;
// 	}else {
// 		alert("가입성공");		
// 	}
// 	frm.submit();
	
// }

//주소api
function post(){
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        	var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
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

//idCheck
$(document).ready(function () {
<c:if test="${command eq 'INSERT'}">
	let exampleModal = $("#exampleModal").on("hidden.bs.modal", function(){
		$(this).find("input").val("");
		userInputId.next("span.message").remove();
	}).on("show.bs.modal", function(){
		ememberForm.data("idValid", false);
		userInputId.data("idValid", false);
		$("#ememId").val("");
	});
	let userInputId = $("#userInputId").on("change", function(){
		userInputId.data("idValid", false);
		let inputId = $(this).val();
	$("#auth").on("click", function(){
		$.ajax({
			url : "${cPath }/member/idCheck.do",
			method : "post",
			data : {
				inputId:inputId
			},
			dataType : "json", 
			success : function(resp) {
				userInputId.next("span.message").remove();
				let message = null;
				if(resp.result=="${ServiceResult.OK }"){
					message = '사용가능';
					userInputId.data("idValid", true);
				}else{
					message = '이미 사용중인 아이디입니다.';
					userInputId.data("idValid", false);
					userInputId.focus();
				}
				userInputId.after($("<span>").addClass("message").html(message));
			}
		});
	});
	});

	$("#saveChange").on("click", function(){
		let idValid = userInputId.data("idValid");
		if(idValid){
			let inputId = userInputId.val();
			$("#ememId").val(inputId);
			ememberForm.data("idValid", true);
		}
 		exampleModal.modal("hide");
	});
	
	let ememberForm = $("#ememberForm").on("submit", function(){
		debugger;
		let idValid = ememberForm.data("idValid");
		return idValid;
	}).data("idValid", false);
</c:if>

})



</script>




