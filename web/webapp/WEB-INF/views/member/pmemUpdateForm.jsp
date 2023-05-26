<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import=" kr.co.jobara.enumpkg.ServiceResult"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
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
.input--style-6 {
	width : 300px;
}

</style>


<form:form commandName="pmember" id="pmemberForm" method="post" enctype="multipart/form-data" action="${cPath }/member/pmemberUpdate.do">	
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
			<table class="col-9 table" style="text-align: center; display: inline-block;">
				<thead class="thead-dark">
					<tr>
						<th colspan="3" style="text-align: center;">내 정보 수정</th>
					</tr>
				</thead>
						<tr>
							<th class="col-3">회원아이디</th> 
							<td class="col-6">
								<form:input path="pmemId" required="required" class="input--style-6" readonly="true" style="background-color: lightgray;"/></td>						
						</tr>
						<tr>
							<th>이름</th>
							<td><form:input path="pmemNm" class="input--style-6" readonly="true" style="background-color: lightgray;"/></td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td><form:input path="pmemBir" class="input--style-6" readonly="true" style="background-color: lightgray;"/></td>
						</tr>
						<tr>
							<th>휴대폰번호</th>
							<td><form:input path="pmemTel" class="input--style-6" placeholder="연락처를 '-' 없이 입력"/>
								<form:errors path="pmemTel" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><form:input path="pmemZip" id="add1" class="input--style-6" placeholder="주소찾기 클릭" readonly="true"/>
								<form:errors path="pmemZip" element="span" cssClass="error" />
								<input class="btn btn-success btn-sm" type="button" onclick="post()" value="주소찾기"></td>							
						</tr>
						<tr>
							<th>주소</th>
							<td><form:input path="pmemBadd" id="add2" class="input--style-6" readonly="true"/>
								<form:errors path="pmemBadd" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td><form:input path="pmemDadd" id="add3" class="input--style-6" />
								<form:errors path="pmemDadd" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><form:input path="pmemEmail" class="input--style-6" placeholder="이메일 형식으로 입력"/>
								<form:errors path="pmemEmail" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th>재직상태</th>
							<td>
								<div class="row" style="padding-left: 3%;">
									<form:select class="custom-select col-6" id="selectBox" name="selectBox" path="empStateCd">
										<option value>재직분류</option>
										<c:forEach items="${tenList }" var="tenList">
											<option value="${tenList['commCd'] }">${tenList.commNm }</option>
										</c:forEach>
									</form:select>
								</div>								
								<div class="col-12" id="carForm" >
								</div>	
								<form:errors path="empStateCd" element="span" cssClass="error" /></td>
						</tr>
						<tr>
							<th>기술스택</th>
							<td><div class="col-12 form-group">
								<form:input type="hidden" path="pmemSkill" id="rdTag" />
								</div>
								<ul id="tag-list"></ul>
								<div class="row" style="padding-left: 3%;">
									<select class="custom-select col-6" name="staDiv">
										<option value>기술분류</option>
										<c:forEach items="${staDivList }" var="staDiv">
											<option value="${staDiv['commCd'] }">${staDiv.commNm }</option>
										</c:forEach>
									</select>
									<select class="custom-select col-6" multiple name="stack">	
										<option value>스택명</option>
										<c:forEach items="${stackList }" var="stack">
											<option class="${stack['commCd'] }" value="${stack['comsCd'] }">${stack.comsNm }</option>
										</c:forEach>
									</select>
								</div>	
								<form:errors path="pmemSkill" element="span" cssClass="error" /></td>								
						</tr>
						<tr>
							<th>프로필사진</th>
							<td>
								<input class="label--file" type="file" name="pmemImg" id="pmemImg"/>
							</td>
						</tr>
					</table>	
					<div style="display: inline-block; text-align: center;">
							<input type="submit" class="site-btn btn" value="저장" />
							<input type="reset" class="btn btn-danger" onclick="location.href='${cPath }/member/pmyPage.do';" value="취소" />
					</div>
				</div>
			</div>				
</form:form>

<script type="text/javascript">

// 재직상태 값 불러오기
let empStateCd = $("[name=empStateCd]").val("${pmember.empStateCd}");

//주소 검색 api
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

let stack = $("[name=stack]");
let staDiv = $("[name=staDiv]").on("change", function() {
		let comm_cd = $(this).val();
		console.log(comm_cd);
		stack.find("option").hide();
		stack.find("option:first").show();
		stack.find("."+comm_cd).show();
	});

$(document).ready(function () {
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

        $(this).submit();
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
})
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
			.append($("<input>").attr({type:"text", name:"empSalary"}).addClass("form-control col-3"));
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
	
	
</script>
