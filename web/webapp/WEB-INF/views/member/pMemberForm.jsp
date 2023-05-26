<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import=" kr.co.jobara.enumpkg.ServiceResult"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
<!--     장인슬 -->
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
<form:form modelAttribute="pmember" id="pmemberForm" method="post" enctype="multipart/form-data"
>	  
    <div class="center page-wrapper col-lg-9">
        <div class="wrapper">
            <div class="card card-6">
                <div class="card-heading">
                    <h2 class="title">개인 회원 가입</h2>
                </div>
                <div class="card-body">
                  <div class="form-row">  
					<table class="col-lg-12">
						<tr>
							<th class="name">회원아이디</th> 
							<td class="value">
								<form:input path="pmemId" required="required" class="input--style-6" placeholder="아이디 중복체크 클릭" readonly="true"/>
<%-- 								<form:errors path="pmemId" element="span" cssClass="error" /> --%>
							<td>	
								<input class="btn btn-success" id="idCheckBtn" type="button" data-toggle="modal" data-target="#exampleModal" style="font-size: 11pt; padding-left: 5%; margin-left: -28%;" value="아이디중복체크"/>
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
							<td class="value">
								<form:input type="password" path="pmemPass" class="input--style-6" placeholder="8~16자 영문 ,숫자등을 사용하세요."/>
<%-- 								<form:errors path="pmemPass" element="span" cssClass="error" /> --%>
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td><form:input path="pmemNm" class="input--style-6" />
<%-- 								<form:errors path="pmemNm" element="span" cssClass="error" /></td> --%>
						</tr>
						<tr>
							<th>생년월일</th>
							<td><form:input path="pmemBir" class="input--style-6" type="date"/>
<%-- 							<form:errors path="pmemBir" element="span" cssClass="error" /></td> --%>
						</tr>
						<tr>
							<th>성별</th>
							<td><div class="row" style="padding-left: 4%">
									<form:select class="custom-select col-6" path="pmemSex" >
										<option value>성별</option>
										<c:forEach items="${genList }" var="gen">
											<option value="${gen['commCd'] }">${gen.commNm }</option>
										</c:forEach>
									</form:select>
								</div>	
<%-- 								<form:errors path="pmemSex" element="span" cssClass="error" /> --%>
							</td>
						</tr>
						<tr>
							<th>휴대폰번호</th>
							<td><form:input path="pmemTel" class="input--style-6" placeholder="연락처를 '-' 없이 입력"/>
<%-- 								<form:errors path="pmemTel" element="span" cssClass="error" /></td> --%>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><form:input path="pmemZip" id="add1" class="input--style-6" placeholder="주소찾기 클릭" readonly="true"/>
<%-- 								<form:errors path="pmemZip" element="span" cssClass="error" /></td> --%>
							<td><input class="btn btn-success btn-sm" type="button" onclick="post()" style="padding-left: 10px; margin-left: -28%;"value="주소찾기"></td>							
						</tr>
						<tr>
							<th>주소</th>
							<td><form:input path="pmemBadd" id="add2" class="input--style-6" readonly="true"/>
<%-- 								<form:errors path="pmemBadd" element="span" cssClass="error" /></td> --%>
						</tr>
						<tr>
							<th>상세주소</th>
							<td><form:input path="pmemDadd" id="add3" class="input--style-6" />
<%-- 								<form:errors path="pmemDadd" element="span" cssClass="error" /></td> --%>
						</tr>
						<tr>
							<th>이메일</th>
							<td><form:input path="pmemEmail" class="input--style-6" placeholder="이메일 형식으로 입력"/>
<%-- 								<form:errors path="pmemEmail" element="span" cssClass="error" /></td> --%>
						</tr>
						<tr>
							<th>재직상태</th>
							<td>
								<div class="row" style="padding-left: 4%">
									<form:select class="custom-select col-6" id="selectBox" name="selectBox" path="empStateCd" >
										<option value>재직분류</option>
										<c:forEach items="${tenList }" var="tenList">
											<option value="${tenList['commCd'] }">${tenList.commNm }</option>
										</c:forEach>
									</form:select>
								</div>	
								<div id="carForm">
								</div>
<%-- 								<form:errors path="empStateCd" element="span" cssClass="error" /></td> --%>
						</tr>
						<tr>
							<th>기술스택</th>
							<td><div class="col-12 form-group">
								<form:input type="hidden" path="pmemSkill" id="rdTag"/>
								</div>
								<div class="row" style="padding-left: 4%">
									<select class="custom-select col-6" name="staDiv" >
										<option value>기술분류</option>
										<c:forEach items="${staDivList }" var="staDiv">
											<option value="${staDiv['commCd'] }">${staDiv.commNm }</option>
										</c:forEach>
									</select>
									<select class="custom-select col-6" multiple name="stack" >	
										<option value>스택명</option>
										<c:forEach items="${stackList }" var="stack">
											<option class="${stack['commCd'] }" value="${stack['comsCd'] }">${stack.comsNm }</option>
										</c:forEach>
									</select>
								</div>	
<%-- 								<form:errors path="pmemSkill" element="span" cssClass="error" /> --%>
							</td>
							<td>
								<ul id="tag-list" class="col-12"></ul>
							</td>
						</tr>
						<tr>
							<th>프로필사진</th>
							<td>
								<input class="label--file" type="file" name="pmemImg" id="pmemImg"/>
<%-- 								<span class="error">${errors["pmemPic"] }</span> --%>
							</td>
						</tr>
					</table>	
					</div>
					<div id="buttons">
							
							<input type="submit" class="site-btn btn" name="fn_save();" value="저장" />
<!-- 							<button type="button" class="site-btn btn" onclick="fn_save();">저장</button> -->
							
							<input type="reset" class="btn btn-danger" value="취소" onclick="location.href='${cPath}';"/>
					</div>
				</div>
			</div>
		</div>
	</div>				
</form:form>

<script type="text/javascript">

function fn_save(){
	var frm = $("#pmemberForm");
	
	alert("가입성공");
	frm.submit();
}


// function fn_save(){

// 	var frm = $("#pmemberForm");
	
// 	var alrtMessage = "";
// 	var id = $("#pmemId").val();
// 	var pass = $("#pmemPass").val();
// 	var nm = $("#pmemNm").val();
// 	var bir = $("#pmemBir").val();
// 	var sex = $("#pmemSex").val();
// 	var tel = $("#pmemTel").val();
// 	var zip = $("#pmemZip").val();
// 	var dadd = $("#pmemDadd").val();
	
// 	console.log(id)
	
// 	if(id == null || id == "" || id == undefiend)
// 		alrtMessage += "아이디\n";
// 	if(pass == null || pass == "" || pass == undefiend)
// 		alrtMessage += "비밀번호\n";
// 	if(nm == null || nm == "" || nm == undefiend)
// 		alrtMessage += "이름\n";
// 	if(bir == null || bir == "" || bir == undefiend)
// 		alrtMessage += "생년월일\n";
// 	if(sex == null || sex == "" || sex == undefiend)
// 		alrtMessage += "성별\n";
// 	if(tel == null || tel == "" || tel == undefiend)
// 		alrtMessage += "핸드폰번호\n";
// 	if(zip == null || zip == "" || zip == undefiend)
// 		alrtMessage += "우편번호\n";
// 	if(dadd == null || dadd == "" || dadd == undefiend)
// 		alrtMessage += "상세주소\n";
	
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
		pmemberForm.data("idValid", false);
		userInputId.data("idValid", false);
		$("#pmemId").val("");
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
			$("#pmemId").val(inputId);
			pmemberForm.data("idValid", true);
		}
 		exampleModal.modal("hide");
	});
	
	let pmemberForm = $("#pmemberForm").on("submit", function(){
		debugger;		
		let idValid = pmemberForm.data("idValid");
		return idValid;
	}).data("idValid", false);
	
</c:if>

//공통코드
let stack = $("[name=stack]");
let staDiv = $("[name=staDiv]").on("change", function() {
		let comm_cd = $(this).val();
		console.log(comm_cd);
		stack.find("option").hide();
		stack.find("option:first").show();
		stack.find("."+comm_cd).show();
	});


    var tag = {};
    var counter = 0;

    // 입력한 값을 태그로 생성한다.
    function addTag (value) {
        tag[counter] = value;
        counter++; // del-btn 의 고유 id 가 된다.
    }

    // tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
    function marginTag () {
        return Object.values(tag).filter(function (word) {
            return word !== "";
        });
    }

    // 서버에 제공
    $("#pmemberForm").on("submit", function (e) {
        var value = marginTag(); // return array
        $("#rdTag").val(value); 
    });
    
    stack.on("change", function(e) {
    	var self = $(this);
    	var tagValue = $("[value="+self.val()+"]").text();	// value 값 가져오기
    	
    	
    	// 해시태그 값 없으면 실행 X
    	if (tagValue !== "") {
    		// 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
            var result = Object.values(tag).filter(function (word) {
                return word === tagValue;
            });
         // 해시태그가 중복되었는지 확인
            if (result.length == 0) { 
                $("#tag-list").append("<li class='tag-item'>"+tagValue+"<span class='del-btn' idx='"+counter+"'> <i class='fa fa-times-circle'></i> </span></li>");
                addTag(tagValue);
                self.val("");
            } else {
                alert("태그값이 중복됩니다.");
            }
    	}
    });

    // 삭제 버튼 
    // 인덱스 검사 후 삭제
    $(document).on("click", ".del-btn", function (e) {
        var index = $(this).attr("idx");
        tag[index] = "";
        $(this).parent().remove();
    });
    
  //-----재직중 클릭시 이벤트----------------------
    $('#selectBox').change(function() {
    	var state = jQuery('#selectBox option:selected').val();
    	if ( state == 'TEN02' ) {
    		jQuery('.layer').show();
    	}
    });
    
	//-----------------------------------------재직-----------------------------------------
	
	// 재직
	$('#selectBox').change(function() {
    	var state = $('#selectBox option:selected').val();
    	
		
		let divTags = $("<div>");
		
		divTags.append($("<br>"));
		
		let div1 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-3").html("회사아이디 "))
			.append($("<input>").attr({type:"text", name:"ememId"}).addClass("form-control col-3"))
			.append($("<label>").addClass("col-2").html("연봉"))
			.append($("<input>").attr({type:"number", name:"empSalary"}).addClass("form-control col-3"));
		divTags.append(div1);
		
		let div2 = $("<div>").addClass("col-12 row")
		.append($("<label>").addClass("col-3").html("직급"))
		.append($("<select>").addClass("custom-select col-3").attr("name", "empPosiCd")
				.append($("<option>").html("직급"))
				<c:forEach items="${posiList }" var="posi">
					.append($("<option>").attr("value","${posi.commCd}").html("${posi.commNm}"))
				</c:forEach>
				)
		.append($("<label>").addClass("col-2").html("직무"))
		.append($("<select>").addClass("custom-select col-3").attr("name", "empDeptCd")
				.append($("<option>").html("직무"))
				<c:forEach items="${deptList }" var="dept">
					.append($("<option>").attr("value","${dept.commCd}").html("${dept.commNm}"))
				</c:forEach>
				);
		divTags.append(div2);
		
		let div3 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-3").html("입사일"))
			.append($("<input>").attr({type:"date", name:"empStartd"}).addClass("form-control col-6"));
		divTags.append(div3);

		
		divTags.append($("<hr>"));
		
    	if ( state == 'TEN02' ) {
    		$("#carForm").append(divTags);
    	}else {
    		$('#carForm').empty();
    	}
		
	});
	
	
})

</script>
