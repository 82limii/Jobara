<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
.form-group label, .form-group input {
	display: inline-block;
}
label {
	text-align: center;
	display: inline-block;
}
.card-body i {
	color: #FF6666;
}
#info {
	text-align: right;
}
.btnSize {
	width: 85px;
	height: 45px;
	margin: 5px;
}
</style>
<div class="container">
	<div class="row">
        <div class="col-lg-12 card-body content-wrapper">
<form:form method="post" id="jobBoardForm" class="form-group" modelAttribute="jobBoard" enctype="multipart/form-data">
	<div class="card-body">
		<div>${message }</div>
		<form:input type="text" path="jobTitle" class="form-control-lg col-12" placeholder="공고 제목을 입력하세요." />
		<form:errors path="jobTitle" element="span" cssClass="error"/>
	</div>
	<div id="info">
		<i class="fa fa-asterisk fa-xs"></i> 필수 입력 정보입니다.
		<form:input path="jobSn" type="hidden"/>
		<form:input path="ememId" type="hidden"/>
		<button type="button" class="btn btn-outline-secondary" id="valBtn">CLICK</button>
	</div>
	<div class="card-body">
		<h4>지원자격</h4>
		<div class="col-12 row">
			<label for="jobEduCd" class="col-1">학력 <i class="fa fa-asterisk fa-xs"></i></label>
			<select class="custom-select col-2" name="jobEduCd">
				<option value>학력</option>
				<c:forEach items="${eduList }" var="edu">
					<option value="${edu['commCd'] }">${edu.commNm }</option>
				</c:forEach>
			</select>
			<form:errors path="jobEduCd" element="span" cssClass="error"/>
			<label for="jobCarCd" class="col-1">경력 <i class="fa fa-asterisk fa-xs"></i></label>
			<select class="custom-select col-2" name="jobCarCd">
				<option value>경력</option>
				<c:forEach items="${carList }" var="car">
					<option value="${car['commCd'] }">${car.commNm }</option>
				</c:forEach>
			</select>
			<form:errors path="jobCarCd" element="span" cssClass="error"/>
			<div class="col-6 row">
				<label for="jobPref" class="col-3">우대사항</label>
				<form:input type="text" path="jobPref" class="form-control col-9" placeholder="우대사항" />
			<form:errors path="jobPref" element="span" cssClass="error"/>
			</div>
		</div>
	</div>
	
	<div class="card-body">
		<h4>근무조건</h4>
		<div class="col-12 row">
			<label for="jobEmpCd" class="col-2">고용형태 <i class="fa fa-asterisk fa-xs"></i></label>
			<select class="custom-select col-2" name="jobEmpCd">
				<option value>고용형태</option>
				<c:forEach items="${empList }" var="emp">
					<option value="${emp['commCd'] }">${emp.commNm }</option>
				</c:forEach>
			</select>
			<form:errors path="jobEmpCd" element="span" cssClass="error"/>
			<label for="jobPay" class="col-1">급여 <i class="fa fa-asterisk fa-xs"></i></label>
			<form:input type="text" path="jobPay" class="form-control col-4" placeholder="ex) 4,000만원 / 회사내규에 따름" />
			<form:errors path="jobPay" element="span" cssClass="error"/>
		</div>
		<div class="col-12 row">
			<label for="jobLocCd" class="col-2">지역 <i class="fa fa-asterisk fa-xs"></i></label>
			<select class="custom-select col-2" id="city">
 				<option value>지역</option> 
 				<c:forEach items="${cityList }" var="city"> 
 					<option value="${city['locCd'] }">${city.locCityNm }</option> 
 				</c:forEach> 
 			</select> 
 			<select class="custom-select col-2" id="location" name="jobLocCd" required>
 				<option value>시구군</option>
 				<c:forEach items="${locList }" var="detail"> 
 					<option value="${detail['locCd'] }" class="${detail.locCd }">${detail.locNm }</option> 
 				</c:forEach> 
 			</select>
			<form:errors path="jobLocCd" element="span" cssClass="error"/>
		</div>
		<div class="col-12 row">
			<label for="jobTime" class="col-2">근무시간</label>
			<form:input type="text" path="jobTime" class="form-control col-3" placeholder="ex) 주 5일(월~금) 09:00 ~ 18:00" />
			<form:errors path="jobTime" element="span" cssClass="error"/>
			<label for="jobPosiCd" class="col-1">직급</label>
			<select class="custom-select col-2" name="jobPosiCd">
				<option value>직급</option>
				<c:forEach items="${posiList }" var="posi">
					<option value="${posi['commCd'] }">${posi.commNm }</option>
				</c:forEach>
			</select>
			<form:errors path="jobPosiCd" element="span" cssClass="error"/>
		<label for="jobDeptCd" class="col-1">직무<i class="fa fa-asterisk fa-xs"></i></label>
		<form:select class="custom-select col-2" path="jobDeptCd">
			<option value>직무</option>
				<c:forEach items="${deptList }" var="dept">
					<option value="${dept['commCd'] }">${dept.commNm }</option>
				</c:forEach>
		</form:select>
			<form:errors path="jobDeptCd" element="span" cssClass="error"/>
		</div>
	</div>
	<div class="card-body">
		<div class="col-12 row">
			<h4>상세요강 <i class="fa fa-asterisk fa-xs"></i></h4>
			<div class="col-12">
			<form:textarea path="jobDetail" id="jobDetail" cssClass="form-control"/> 
			<form:errors path="jobDetail" element="span" cssClass="error"/>
			</div>
		</div>
	</div>
	<div class="card-body">
		<div class="col-12 row">
			<h4>기술스택 <i class="fa fa-asterisk fa-xs"></i></h4>
			<div class="col-12 form-group">
				<form:input type="hidden" path="jobSkill" id="hiddenSkill"/>
			<form:errors path="jobSkill" element="span" cssClass="error"/>
			</div>
			<ul id="tag-list"></ul>
			<div class="row">
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
		</div>
	</div>
	<div class="card-body">
		<h4>접수기간/방법</h4>
		<div class="col-12 row">
			<label for="jobStartd" class="col-2">접수시작일 <i class="fa fa-asterisk fa-xs"></i></label>
			<form:input type="date" path="jobStartd" class="form-control col-2" />
			<form:errors path="jobStartd" element="span" cssClass="error"/>
			<label for="jobEndd" class="col-2">접수마감일 <i class="fa fa-asterisk fa-xs"></i></label>
			<form:input type="date" path="jobEndd" class="form-control col-2" />
			<form:errors path="jobEndd" element="span" cssClass="error"/>
			<label for="jobWay" class="col-2">지원방법  <i class="fa fa-asterisk fa-xs"></i></label>
			<div id="jobWay" class="col-2">
				<ul>
					<c:set var="jobWay" value="${jobBoard.jobWay }" />
					<c:forEach items="${wayList }" var="way">
						<c:set var="wayNm" value="${way.commNm }" />
						<c:choose>
							<c:when test="${fn:contains(jobWay, wayNm) }">
								<li><label><form:checkbox path="jobWay" value="${way.commNm }" checked="true"/>${way.commNm }</label> </li>
							</c:when>
							<c:otherwise>
								<li><label><form:checkbox path="jobWay" value="${way.commNm }"/>${way.commNm }</label> </li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			<form:errors path="jobWay" element="span" cssClass="error"/>
			</div>
		</div>
		<div class="col-12 row">
			<label for="jobPage" class="col-2">홈페이지 지원링크</label>
			<form:input type="url" path="jobPage" class="form-control col-4" />
			<form:errors path="jobPage" element="span" cssClass="error"/>
		</div>
		<div class="col-12 row attatch">
			<label for="attatchId" class="col-2">첨부파일</label>
			<div class="col-10" id="attDiv">
				<input type="file" name="jobFiles" class="form-control col-4" />
			</div>
			<form:errors path="jobFiles" element="span" cssClass="error"/>
			<button type="button" class="btn btnSize left" id="attatchBtn"><i class="fa fa-plus-square-o fa-lg"></i></button>
		</div>
	</div>
<div class="center">
	<button type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/jobboard/com/jobBoardList.do">목록</button>
	<button type="submit" class="btn btn-success btnSize">저장</button>
	<button type="reset" class="btn btn-danger btnSize">취소</button>
</div>
</form:form>
		</div>
	</div>
</div>	



<script type="text/javascript">
CKEDITOR.replace("jobDetail", {
	filebrowserImageUploadUrl : CONTEXTPATH+'/board/uploadImage.do?type=Images'
});

	let valBtn = $("#valBtn").on("click", function() {
		$("[name=jobTitle]").val("서버 개발자 Python(Django) Developer");
		$("[name=jobEduCd]").val("EDU03");
		$("[name=jobCarCd]").val("CAR01");
		$("[name=jobEmpCd]").val("EMP01");
		$("[name=jobPay]").val("3,200만원");
		$("#city").val("11");
		$("#location").val("11210");
		$("[name=jobDeptCd]").val("DEP01");
		$("[name=jobStartd]").val("2022-04-01");
		$("[name=jobEndd]").val("2022-04-13");
		$("#jobWay1").attr("checked", true);
	});

//지역
let detail = $("#location");
let city = $("#city").on("change", function() {
	let cityCd = $(this).val();
	detail.find("option").hide();
	detail.find("option.first").show();
	detail.find("option").each(function(index, item) {
		let optCd = $(this).val().substring(0,2);
		if(cityCd == optCd) {
			$(this).show();
		}
	});
});

	let attatchBtn = $("#attatchBtn").on("click", function() {
		let inputTag = $("<input>",{type:"file", name:"jobFiles"}).addClass("form-control col-4");
		$("#attDiv").append(inputTag);
	});
	
	// 기술스택 동적
	let stack = $("[name=stack]");
	let staDiv = $("[name=staDiv]").on("change", function() {
			let comm_cd = $(this).val();
			console.log(comm_cd);
			stack.find("option").hide();
			stack.find("option:first").show();
			stack.find("."+comm_cd).show();
		});
	
	let jobEduCd = $("[name=jobEduCd]").val("${jobBoard.jobEduCd}");
	let jobCarCd = $("[name=jobCarCd]").val("${jobBoard.jobCarCd}");
	let jobEmpCd = $("[name=jobEmpCd]").val("${jobBoard.jobEmpCd}");
	let jobLocCd = $("[name=jobLocCd]").val("${jobBoard.jobLocCd}");
	let jobPosiCd = $("[name=jobPosiCd]").val("${jobBoard.jobPosiCd}");
	let jobDeptCd = $("[name=jobDeptCd]").val("${jobBoard.jobDeptCd}");

	$(document).ready(function () {
        var tag = {};	// 기술스택
        var counter = 0;	// 기술스택

        // 입력한 값을 태그로 생성한다. 기술스택
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
        $("#jobBoardForm").on("submit", function (e) {
            var value = marginTag(); // return array
            $("#hiddenSkill").val(value); 
            

            $(this).submit();
        });
        
        stack.on("change", function(e) {
        	var self = $(this);
        	var tagValue = $("[value="+self.val()+"]").text();	// value 값 가져오기
        	
        	
        	// 해시태그 값 없으면 실행 X
        	if (tagValue != "") {
        		// 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
                var result = Object.values(tag).filter(function (word) {
                    return word == tagValue;
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
            if($(this).parent().hasClass("tag-item")) {
	            tag[index] = "";
            }
            $(this).parent().remove();
        });
	});
    
</script>