<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<style>
.form-group label, .form-group input {
	display: inline-block;
}
label {
	text-align: center;
	display: inline-block;
}
i {
	color: #FF6666;
}
#info {
	text-align: right;
}
.center {
	text-align: center;
}
.btnSize {
	width: 85px;
	height: 45px;
	margin: 5px;
}
.left {
	float: left;
}
b {
	font-size: large;
}
.card-body * {
	margin-bottom: 5px;
}
li {
	float: left;
	margin-right: 10px;
}
.modal-body .table th, .table td {
	text-align: center;
}
</style>
<div class="container">
	<div class="row">
        <div class="col-lg-12 card-body content-wrapper">
			<form method="post" id="resumeForm" enctype="multipart/form-data">
			<div class="card-body">
				<input type="text" name="reTitle" class="form-control-lg col-12" placeholder="기업의 이목을 끌 제목을 적어주세요." required="required" />
				<span class="error">${errors["reTitle"] }</span>
			</div>
			<div id="info">
				<i class="fa fa-asterisk fa-xs"></i> 필수 입력 정보입니다.
			</div>
			<div class="card-body">
				<h4>인적사항</h4>
				<div class="col-12 row">
					<table>
						<tr>
							<td rowspan="4" class="col-2 center">
								<img src="<spring:url value='/image/${member.pmemPic }'/>" style="width:auto; height:140px;"/>
							</td>
							<td colspan="2"><b>${member.pmemNm }</b> ${member.pmemSexNm }, ${member.pmemBir }</td>
						</tr>
						<tr>	
							<th class="col-2">이메일</th>
							<td class="col-8">${member.pmemEmail }</td>
						</tr>
						<tr>
							<th class="col-2">연락처</th>
							<td class="col-8">${member.pmemTel }</td>
						</tr>
						<tr>
							<th class="col-2">주소</th>
							<td class="col-8">${member.pmemZip }, ${member.pmemBadd }</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="card-body" id="eduForm">
				<h4>학력</h4>
				<button type="button" class="btn btn-info" id="eduBtn" >학력추가</button>
			</div>
			
			<div class="card-body" id="carForm">
				<h4>경력</h4>
				<button type="button" class="btn btn-info" id="carBtn">경력추가</button>
			</div>
			
			<div class="card-body" id="actForm">
				<h4>대외활동</h4>
				<button type="button" class="btn btn-info" id="actBtn">활동추가</button>
			</div>
			
			<div class="card-body" id="schForm">
				<h4>교육이수</h4>
				<button type="button" class="btn btn-info" id="schBtn">교육추가</button>
			</div>
			
			<div class="card-body" id="langForm">
				<h4>어학</h4>
				<button type="button" class="btn btn-info" id="langBtn">어학추가</button>
			</div>
			
			<div class="card-body" id="licForm">
				<h4>자격증</h4>
				<button type="button" class="btn btn-info" id="licBtn">자격증추가</button>
			</div>
			
			<div class="card-body">
				<h4>포트폴리오</h4>
				<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal" id="modalOpen">포트폴리오 불러오기</button>
				<input type="file" class="form-control col-3" name="portfolio.portfolioFiles" />
				<input type="text" name="portSn" hidden="true"/>
			</div>
			
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">내 포트폴리오</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <table class="table">
			        	<tr>
			        		<th class="col-1"></th>
			        		<th class="col-8" style="text-align:left;">파일명</th>
			        		<th class="col-3">등록일</th>
			        	</tr>
			        	<c:set value="${pagingVO.dataList }" var="portfolioList" />
			        	<c:forEach items="${portfolioList}" var="portfolio">
				        	<tr>
				        		<td><input type="radio" class="portSn" value="${portfolio.portSn }" /></td>
				        		<td style="text-align:left;">${portfolio.attNm }</td>
				        		<td>${portfolio.attDate }</td>
				        	</tr>
			        	</c:forEach>
			        </table>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			        <button type="button" class="btn btn-primary"  data-dismiss="modal" id="modalBtn">선택</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			<div class="card-body">
				<h4><i class="fa fa-asterisk fa-xs"></i> 자기소개서</h4>
				<textarea name="reInt" id="reInt"></textarea>
			</div>
			
			<div class="card-body">
				<h4>희망근무조건</h4>
				<div>
					<label for="condiShape" class="col-2"><i class="fa fa-asterisk fa-xs"></i> 근무형태</label>
					<select class="custom-select col-2" name="condi.condiShape" required>
						<option value>근무형태</option>
						<c:forEach items="${empList }" var="emp">
							<option value="${emp['commCd'] }">${emp.commNm }</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="condiPlace" class="col-2"><i class="fa fa-asterisk fa-xs"></i> 희망근무지</label>
					<select class="custom-select col-2" id="city">
		 				<option value>지역</option> 
		 				<c:forEach items="${cityList }" var="city"> 
		 					<option value="${city['locCd'] }">${city.locCityNm }</option> 
		 				</c:forEach> 
		 			</select> 
		 			<select class="custom-select col-2" id="location" name="condi.condiPlace" required>
		 				<option value>시구군</option>
		 				<c:forEach items="${locList }" var="detail"> 
		 					<option value="${detail['locCd'] }" class="${detail.locCd }">${detail.locNm }</option> 
		 				</c:forEach> 
		 			</select>					
				</div>
				<div>
		 			<label for="condiDuty" class="col-2"><i class="fa fa-asterisk fa-xs"></i> 희망직무</label>
		 			<select class="custom-select col-2" name="condi.condiDuty" required>
						<option>직무</option>
							<c:forEach items="${deptList }" var="dept">
								<option value="${dept['commCd'] }">${dept.commNm }</option>
							</c:forEach>
					</select>
				</div>
				<div>
					<h4>기술스택</h4>
					<div class="col-12 form-group">
						<input type="hidden" name="reStack" id="hiddenStack" />
					</div>
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
					<div class="col-12">
						<ul id="tag-list"></ul>
					</div>
				</div>
			</div>
			
			<div class="center">
				<button type="button" class="btn btn-outline-primary left linkBtn btnSize"
				onclick="location.href='/myPage/resumeList.do'" >목록</button>
				<button type="submit" class="btn btn-success btnSize">저장</button>
				<button type="reset" class="btn btn-danger btnSize">취소</button>
			</div>
			</form>
		</div>
	</div>	
</div>

<script type="text/javascript">

	CKEDITOR.replace("reInt");
	
	$("#resumeForm").validate();	// validate
	
	// 모달창에서 선택한 포트폴리오 번호 input태그로 넘기기
	let modal = $("#modalBtn").on("click", function() {
		let portSn = $(".portSn:checked").val();
		$("[name=portSn]").val(portSn);
	})
	
	// 모달버튼 클릭시 기존 선택 해제
	$("#modalOpen").on("click", function() {
		$("[name=portSn]").val(null);
		$(".portSn").prop("checked", false);
	})
	
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
	
	let stack = $("[name=stack]");
	let staDiv = $("[name=staDiv]").on("change", function() {
			let comm_cd = $(this).val();
			console.log(comm_cd);
			stack.find("option").hide();
			stack.find("option:first").show();
			stack.find("."+comm_cd).show();
	});
	
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
        $("#resumeForm").on("submit", function (e) {
            var value = marginTag(); // return array
            $("#hiddenStack").val(value); 
            

//             $(this).submit();
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
	
	
	//-----------------------------------------학력-----------------------------------------
	//학력 name 배열 idx
	let eduIdx = 0;
	
	// 학력추가
	let eduBtn = $("#eduBtn").on("click", function() {
		
		let divTags = $("<div>").addClass("eduClass");
		
		divTags.append($("<br>"));
		
		let div1 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("학교구분 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<select>").addClass("custom-select col-2").attr({name:"educationList["+eduIdx+"].ediSn", required:"required"}).val("")
					.append("<option value>학교구분</option>")
					<c:forEach items="${schList }" var="sch">
						.append($("<option>").attr("value","${sch.commCd}").html("${sch.commNm}"))
					</c:forEach>
					)
			.append($("<label>").addClass("col-2").html("학교명 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"educationList["+eduIdx+"].eduName", required:"required"}).addClass("form-control col-3"));
		divTags.append(div1);
		
		let div2 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("재학기간 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"month", name:"educationList["+eduIdx+"].eduSdate", required:"required"}).addClass("form-control col-2"))
			.append($("<select>").addClass("custom-select col-2").attr({name: "educationList["+eduIdx+"].eduEnter", required:"required"})
					<c:forEach items="${entList }" var="ent">
						.append($("<option>").attr("value","${ent.commCd}").html("${ent.commNm}"))
					</c:forEach>
					)
			.append("&nbsp;&nbsp;~&nbsp;&nbsp;")
			.append($("<input>").attr({type:"month", name:"educationList["+eduIdx+"].eduEdate", required:"required"}).addClass("form-control col-2"))
			.append($("<select>").addClass("custom-select col-2").attr({name:"educationList["+eduIdx+"].eduState", required:"required"})
					<c:forEach items="${graList }" var="gra">
						.append($("<option>").attr("value","${gra.commCd}").html("${gra.commNm}"))
					</c:forEach>
					);
		divTags.append(div2);
		
		let div3 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("전공"))
			.append($("<input>").attr({type:"text", name:"educationList["+eduIdx+"].eduMajor"}).addClass("form-control col-3"))
			.append($("<label>").addClass("col-1").html("학점"))
			.append($("<input>").attr({type:"text", name:"educationList["+eduIdx+"].eduScore", placeholder:"ex) 4.0/4.5"}).addClass("form-control col-2"));
		divTags.append(div3);
		
		let div4 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("졸업논문"))
			.append($("<input>").attr({type:"file", name:"educationList["+eduIdx+"].eduFiles"}).addClass("form-control col-3"));
		divTags.append(div4);
		
		let div5 = $("<div>").addClass("col-12 row")
			.append($("<span>").css("width","auto").addClass("col-12")
					.append($("<button>").attr({type:"button"}).addClass("btn btn-outline-danger eduDel").html("삭제 X").css("float","right"))
					);
		divTags.append(div5);
		
		divTags.append($("<hr>"));
		$("#eduForm").append(divTags);
		
		eduIdx = eduIdx + 1;
	});
	
	// 학력정보 삭제버튼
	$(document).on("click", ".eduDel", function() {
		let tag = $(this).closest(".eduClass");
		tag.empty(); 
	});
	
	
	//-----------------------------------------경력-----------------------------------------
	//경력 name 배열 idx
	let carIdx = 0;
	
	// 경력추가
	let carBtn = $("#carBtn").on("click", function() {
		
		let divTags = $("<div>").addClass("carClass");
		
		divTags.append($("<br>"));
		
		let div1 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("회사명 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"careerList["+carIdx+"].carEnter", required:"required"}).addClass("form-control col-3"))
			.append($("<label>").addClass("col-2").html("부서명 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"careerList["+carIdx+"].carDepartment", required:"required"}).addClass("form-control col-2"));
		divTags.append(div1);
		
		let div2 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("직급"))
			.append($("<select>").addClass("custom-select col-2").attr("name", "careerList["+carIdx+"].carPosiCd")
					.append("<option value>직급</option>")
					<c:forEach items="${posiList }" var="posi">
						.append($("<option>").attr("value","${posi.commCd}").html("${posi.commNm}"))
					</c:forEach>
					)
			.append($("<label>").addClass("col-2").html("직무")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<select>").addClass("custom-select col-3").attr({name:"careerList["+carIdx+"].carDeptCd", required:"required"})
					.append("<option value>직무</option>")
					<c:forEach items="${deptList }" var="dept">
						.append($("<option>").attr("value","${dept.commCd}").html("${dept.commNm}"))
					</c:forEach>
					);
		divTags.append(div2);
		
		let div3 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("재직기간 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"date", name:"careerList["+carIdx+"].carStartd", required:"required"}).addClass("form-control col-2"))
			.append("&nbsp;&nbsp;~&nbsp;&nbsp;")
			.append($("<input>").attr({type:"date", name:"careerList["+carIdx+"].carEndd"}).addClass("form-control col-2"))
			.append($("<label>").addClass("col-1").html("연봉"))
			.append($("<input>").attr({type:"text", name:"careerList["+carIdx+"].carPay"}).addClass("form-control col-2"));
		divTags.append(div3);
		
		let div4 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("담당업무")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"careerList["+carIdx+"].carTask", required:"required"}).addClass("form-control col-10"))
			.append($("<label>").addClass("col-2").html("경력기술서"))
			.append($("<textarea>").attr({rows:"5", name:"careerList["+carIdx+"].carDescription", wrap:"hard"}).addClass("form-control col-10"));
		divTags.append(div4);
		
		let div5 = $("<div>").addClass("col-12 row")
			.append($("<span>").css("width","auto").addClass("col-12")
					.append($("<button>").attr({type:"button"}).addClass("btn btn-outline-danger carDel").html("삭제 X").css("float","right"))
					);
		divTags.append(div5);
			
	
		divTags.append($("<hr>"));
		$("#carForm").append(divTags);
		
		carIdx = carIdx + 1;
	});
	
	// 경력정보 삭제버튼
	$(document).on("click", ".carDel", function() {
		let tag = $(this).closest(".carClass");
		tag.empty(); 
	});
	
	
	//-----------------------------------------대외활동-----------------------------------------
	//대외활동 name 배열 idx
	let actIdx = 0;
	
	// 대외활동추가
	let actBtn = $("#actBtn").on("click", function() {
		
		let divTags = $("<div>").addClass("actClass");
		
		divTags.append($("<br>"));
		
		let div1 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("활동구분 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<select>").attr({name:"activityList["+actIdx+"].actComb", required:"required"}).addClass("col-2 custom-select")
					.append("<option value>활동구분</option>")
					<c:forEach items="${actList }" var="act">
						.append($("<option>").attr("value","${act.commCd}").html("${act.commNm}"))
					</c:forEach>
					)
			.append($("<label>").addClass("col-2").html("기관/장소 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"activityList["+actIdx+"].actNm", required:"required"}).addClass("form-control col-3"));
		divTags.append(div1);
					
		let div2 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("활동기간 ")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"date", name:"activityList["+actIdx+"].actStartd", required:"required"}).addClass("form-control col-2"))
			.append("&nbsp;&nbsp;~&nbsp;&nbsp;")
			.append($("<input>").attr({type:"date", name:"activityList["+actIdx+"].actEndd", required:"required"}).addClass("form-control col-2"));
		divTags.append(div2);
		
		let div3 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("활동내용")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"activityList["+actIdx+"].actContents", required:"required"}).addClass("form-control col-10"));
		divTags.append(div3);
		
		let div4 = $("<div>").addClass("col-12 row")
			.append($("<span>").css("width","auto").addClass("col-12")
					.append($("<button>").attr({type:"button"}).addClass("btn btn-outline-danger actDel").html("삭제 X").css("float","right"))
					);
		divTags.append(div4);
		
		
		divTags.append($("<hr>"));
		$("#actForm").append(divTags);
		
		actIdx = actIdx + 1;
	});
	
	// 활동정보 삭제버튼
	$(document).on("click", ".actDel", function() {
		let tag = $(this).closest(".actClass");
		tag.empty(); 
	});
	
	
	//-----------------------------------------교육이수-----------------------------------------
	// 교육이수 name 배열 idx
	let schIdx = 0;
	
	// 교육이수 추가
	let schBtn = $("#schBtn").on("click", function() {
		
		let divTags = $("<div>").addClass("schClass");
		
		divTags.append($("<br>"));
		
		let div1 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("교육명")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"schoolList["+schIdx+"].schNm", required:"required"}).addClass("form-control col-3"))
			.append($("<label>").addClass("col-2").html("교육기관")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"schoolList["+schIdx+"].schInt", required:"required"}).addClass("form-control col-3"));
		divTags.append(div1);

		let div2 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("교육기간")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"date", name:"schoolList["+schIdx+"].schStartd", required:"required"}).addClass("form-control col-2"))
			.append("&nbsp;&nbsp;~&nbsp;&nbsp;")
			.append($("<input>").attr({type:"date", name:"schoolList["+schIdx+"].schEndd", required:"required"}).addClass("form-control col-2"))
		divTags.append(div2);
		
		let div3 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("교육내용")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"schoolList["+schIdx+"].schContents", required:"required"}).addClass("form-control col-10"))
		divTags.append(div3);
		
		let div4 = $("<div>").addClass("col-12 row")
			.append($("<span>").css("width","auto").addClass("col-12")
					.append($("<button>").attr({type:"button"}).addClass("btn btn-outline-danger schDel").html("삭제 X").css("float","right"))
					);
		divTags.append(div4);
		
		
		divTags.append($("<hr>"));
		$("#schForm").append(divTags);
		
		schIdx = schIdx + 1;
	});
	
	// 교육정보 삭제버튼
	$(document).on("click", ".schDel", function() {
		let tag = $(this).closest(".schClass");
		tag.empty(); 
	});

	
	//-----------------------------------------어학-----------------------------------------
	// 어학 name 배열 idx
	let langIdx = 0;
	
	// 어학 추가
	let langBtn = $("#langBtn").on("click", function() {
		
		let divTags = $("<div>").addClass("langClass");
		
		divTags.append($("<br>"));
		
		let div1 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("어학구분").attr("required","required")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<select>").attr({name:"languageList["+langIdx+"].langDiviCd", required:"required"}).addClass("custom-select col-2 divi-cd")
					.append("<option value>어학구분</option>")
					<c:forEach items="${langList }" var="lang">
						.append($("<option>").attr("value","${lang.commCd}").html("${lang.commNm}"))
					</c:forEach>
					)
			.append($("<label>").addClass("col-2").html("외국어명")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({name:"languageList["+langIdx+"].langNm", type:"text", required:"required"}).addClass("form-control col-2"))
			.append($("<label>").addClass("col-2").html("회화능력"))
			.append($("<select>").attr({name:"languageList["+langIdx+"].langConCd"}).addClass("custom-select col-2")
					.append("<option value>회화능력</option>")
					<c:forEach items="${conList }" var="con">
						.append($("<option>").attr("value","${con.commCd}").html("${con.commNm}"))
					</c:forEach>
					);
		divTags.append(div1);

		let div2 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("시험명"))
			.append($("<input>").attr({type:"text", name:"languageList["+langIdx+"].langTest"}).addClass("form-control col-2"))
			.append($("<label>").addClass("col-2").html("점수/급수"))
			.append($("<input>").attr({type:"text", name:"languageList["+langIdx+"].langScore"}).addClass("form-control col-2"))
			.append($("<label>").addClass("col-2").html("취득년월"))
			.append($("<input>").attr({type:"date", name:"languageList["+langIdx+"].langDate"}).addClass("form-control col-2"));
		divTags.append(div2);
		
		let div3 = $("<div>").addClass("col-12 row")
			.append($("<span>").css("width","auto").addClass("col-12")
					.append($("<button>").attr({type:"button"}).addClass("btn btn-outline-danger langDel").html("삭제 X").css("float","right"))
					);
		divTags.append(div3);
		
		divTags.append($("<hr>"));
		$("#langForm").append(divTags);
		
		langIdx = langIdx + 1;
	});
	
	// 어학정보 삭제버튼
	$(document).on("click", ".langDel", function() {
		let tag = $(this).closest(".langClass");
		tag.empty(); 
	});
	
	
	//-----------------------------------------자격증-----------------------------------------
	// 자격증 name 배열 idx
	let licIdx = 0;
	
	// 어학 추가
	let licBtn = $("#licBtn").on("click", function() {
		
		let divTags = $("<div>").addClass("licClass");
		
		divTags.append($("<br>"));
		
		let div1 = $("<div>").addClass("col-12 row")
			.append($("<label>").addClass("col-2").html("자격증명")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"licenseList["+licIdx+"].licNm", required:"required"}).addClass("form-control col-2"))
			.append($("<label>").addClass("col-2").html("발급기관")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"text", name:"licenseList["+licIdx+"].licInt", required:"required"}).addClass("form-control col-2"))
			.append($("<label>").addClass("col-2").html("취득년월")
					.append($("<i>").addClass("fa fa-asterisk fa-xs"))
					)
			.append($("<input>").attr({type:"date", name:"licenseList["+licIdx+"].licDate", required:"required"}).addClass("form-control col-2"))
			;
		divTags.append(div1);
		
		let div2 = $("<div>").addClass("col-12 row")
			.append($("<span>").css("width","auto").addClass("col-12")
					.append($("<button>").attr({type:"button"}).addClass("btn btn-outline-danger licDel").html("삭제 X").css("float","right"))
					);
		divTags.append(div2);
		
		divTags.append($("<hr>"));
		$("#licForm").append(divTags);
		
		licIdx = licIdx + 1;
	});
	
	// 어학정보 삭제버튼
	$(document).on("click", ".licDel", function() {
		let tag = $(this).closest(".licClass");
		tag.empty(); 
	});
</script>