<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
#saveBtn, #cancelBtn{
	float: right;
}
.memberBtn {
	margin-right: 5px;
}
.phBtn {
	margin-right: 5px;
}
#projListTr:hover{
	background-color: lightgray;
}
.form-control {
	line-height: 40px;
}
#d1 {
	float: left;
}
#d2 {
	float: right;
}
.trVal:hover {
	background-color: lightgray;
}
.trVal2:hover {
	background-color: lightgray;
}
.tag-item {
	color: black;
}
#add1 {
	float : left;
}
#add2 {
	float: left;
	border: 1px solid #ddd;
	height: expression( this.scrollHeight > 348 ? "348px" : "auto" );
	min-height: 348px;
	max-height: 348px;
	padding-top: 9px;
	overflow-y: auto;
}
ul,li {
	margin:0; 
	padding:0;
	list-style:none;
}

/*tab css*/
.tabnav{font-size:0; border:1px solid #ddd;}
.tabnav li{display: inline-block;  height:46px; text-align:center; border-right:1px solid #ddd;}
.tabnav li a:before{content:""; position:absolute; left:0; top:0px; width:100%; height:3px; }
.tabnav li a.active:before{background:#7ea21e;}
.tabnav li a.active{border-bottom:1px solid #fff;}
.tabnav li a{ position:relative; display:block; background: #f8f8f8; color: #000; padding:0 30px; line-height:46px; text-decoration:none; font-size:16px;}
.tabnav li a:hover,
.tabnav li a.active{background:#fff; color:#7ea21e; }
.tabcontent{padding: 20px; height: auto; min-height:300px; border:1px solid #ddd; border-top:none;}

</style>
   <div class="col-12 grid-margin stretch-card">
     <div class="card">
       <div class="card-body">
         <h4 class="card-title">프로젝트 생성</h4>
         <form:form class="forms-sample" modelAttribute="project" method="post" >
           <div class="form-group">
             <label for="projectName">프로젝트명</label>
             <form:input path="proName" class="form-control" />
           </div>
           <div class="form-group">
	           <div class="form-group col-6" id="d1">
	             <label for="inputStartDate">시작일</label>
	             <form:input path="proStartd" type="date" class="form-control" id="inputStartDate" />
	           </div>
	           <div class="form-group col-6" id="d2">
	             <label for="inputEndDate">종료일</label>
	             <form:input path="proEndd" type="date" class="form-control" id="inputEndDate" />
	           </div>
           </div>
<!--            <div class="form-group"> -->
<!--              <label for="proManager">담당자</label> -->
<%--              <form:input path="ememId" type="text" class="form-control col-lg-6" id="proManager" /> --%>
<!--            </div> -->
			<div class="form-group">
			  <div class="tab col-6" id="add1">
<!-- 			  <br><br><br><br><br> -->
			    <ul class="tabnav">
			      <li><a href="#tab01">지원자</a></li>
			      <li><a href="#tab02">재직자</a></li>
			    </ul>
			    <div class="tabcontent">
				<!-- 지원자 조회 -->
			      <div id="tab01">
					<select class="custom-select col-lg-10" name="applyMember">
						<option value>프로젝트 목록</option>
						<c:forEach items="${proBoardList }" var="proBoard">
							<option value="${proBoard.probSn }">${proBoard.probTitle }</option>
						</c:forEach>
					</select>
					<input type="button" id="applyMemBtn" class="btn btn-outline-success" value="조회" />
					<table class="table table-bordered" id="applyMemTb">
						<thead>
						<tr>
							<th class="col-2">아이디</th>
							<th class="col-2">이름</th>
							<th class="col-4">Email</th>
						</tr>
						</thead>
						<tbody id="applyMemBody">
<%-- 							<c:forEach items="${applyMemList }" var="applyMem"> --%>
<!-- 							<tr id="trTag"> -->
<%-- 								<td>${applyMem.pmemId }</td> --%>
<%-- 								<td>${applyMem.pmemNm }</td> --%>
<%-- 								<td>${applyMem.pmemEmail }</td> --%>
<!-- 							</tr> -->
<%-- 							</c:forEach> --%>
						</tbody>
					</table>
			      </div>
			      
				<!-- 재직자 조회 -->
			      <div id="tab02">
			      	<table class="table table-bordered">
			      	<thead>
			      		<tr style="text-align: center;">
			      			<th colspan="4">재직자 목록</th>
			      		</tr>
			      	</thead>
			      	<tbody>
						<c:forEach items="${paging.dataList }" var="incumbent">
				      		<tr class="trVal2">
								<td>${incumbent.pmemId }</td>
								<td>${incumbent.pmemNm }</td>
								<td>${incumbent.pmemEmail }</td>
				      		</tr>
						</c:forEach>
			      	</tbody>
			      	</table>
			      </div>
			    </div>
			    </div>
<!-- 			     <br><br><br><br><br> -->
				<div class="form-group col-6" id="add2">
					<label for="memList">구성원 추가 목록</label>
					<table class="table table-bordered">
						<tbody id="tag-Mem">
						</tbody>
					</table>
				</div>
			  </div><!--tab-->
			
           <button id="cancelBtn" class="btn btn-light">취소</button>
           <button id="saveBtn" type="submit" class="btn btn-primary mr-2">저장</button>
         </form:form>
       </div>
     </div>
   </div>
<script type="text/javascript">
$(function(){
	  $('.tabcontent > div').hide();
	  $('.tabnav a').click(function () {
	    $('.tabcontent > div').hide().filter(this.hash).fadeIn();
	    $('.tabnav a').removeClass('active');
	    $(this).addClass('active');
	    return false;
	  }).filter(':eq(0)').click();
	  });


var counter = 0;
$("#applyMemBtn").click(function(){
	let proBoard = $("select[name=applyMember]").val();
	console.log(proBoard);
	if(proBoard != null) {
		$.ajax({
			url : "${cPath}/project/com/proBoardApplyMem.do?what="+proBoard,
			method : "get",
			dataType : "json",
			success : function(resp) {
				let result = resp.applyMemList;
				let trTag = "";
				$.each(result, function(i, applyMem){
					trTag += "<tr class='trVal'>";
					trTag += "<td>" + applyMem.pmemId + "</td>";
					trTag += "<td>" + applyMem.pmemNm + "</td>";
					trTag += "<td>" + applyMem.pmemEmail + "</td>";
					trTag += "</tr>"
				});
				$("#applyMemBody").empty();
				$("#applyMemBody").append(trTag);
				
				$("#applyMemBody tr").click(function(){
					
					for (var i = 0; i < $(".tag-item").length; i++) {
// 						console.log($(".tag-item").children("td")[4*i].innerText);
// 						console.log($(this).children(":first")[0].innerText);
						if ($(this).children(":first")[0].innerText == $(".tag-item").children("td")[4*i].innerText) {
							alert("이미 등록되어 있습니다.");
							return;
						}
					}
					
					var str = "";
					var tdArr = new Array();
					
					var tr = $(this);
					var td = tr.children();
					
					td.each(function(i){
						tdArr.push(td.eq(i).text());
					});
					var pmemId = td.eq(0).text();
					var pmemNm = td.eq(1).text();
					var pmemEmail = td.eq(2).text();
					var pmemTel = td.eq(3).text();
					let applyMemTag = "<tr class='tag-item'><td>"+pmemId+"</td><td>"+pmemNm+"</td><td>"+pmemEmail+"</td>";
						applyMemTag += "<td><span class='del-btn' idx='"+counter+"'><i class='fa fa-times-circle'></i></span></td></tr>";
						applyMemTag += "<input type='hidden' name='projectMemberList["+counter+"].pmemId' id='pmemId"+counter+"' value='"+pmemId+"' />"
						applyMemTag += "<input type='hidden' name='projectMemberList["+counter+"].pmemNm' id='pmemNm"+counter+"' value='"+pmemNm+"' />"
						applyMemTag += "<input type='hidden' name='projectMemberList["+counter+"].memEmail' id='pmemEmail"+counter+"' value='"+pmemEmail+"' />"
						applyMemTag += "<input type='hidden' name='projectMemberList["+counter+"].memTel' id='pmemTel"+counter+"' value='"+pmemTel+"' />"
						$("#pmemId"+counter).val(pmemId);
						$("#pmemNm"+counter).val(pmemNm);
						$("#pmemEmail"+counter).val(pmemEmail);
						$("#pmemTel"+counter).val(pmemTel);
						$("#tag-Mem").append(applyMemTag);
					console.log("counter : " + counter);
					counter++;
				})
				
			},
			error : function(xhr, status, error) {
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		});
	
	} 
})

$(".trVal2").click(function(){
					
					for (var i = 0; i < $(".tag-item").length; i++) {
//							console.log($(".tag-item").children("td")[4*i].innerText);
//							console.log($(this).children(":first")[0].innerText);
						if ($(this).children(":first")[0].innerText == $(".tag-item").children("td")[4*i].innerText) {
							alert("이미 등록되어 있습니다.");
							return;
						}
					}
					
					var str2 = "";
					var tdArr2 = new Array();
					var tr = $(this);
					var td = tr.children();
					td.each(function(i){
						tdArr2.push(td.eq(i).text());
					});
					var pmemId = td.eq(0).text();
					var pmemNm = td.eq(1).text();
					var pmemEmail = td.eq(2).text();
					var pmemTel = td.eq(3).text();
					let incumbentTag = "<tr class='tag-item'><td>"+pmemId+"</td><td>"+pmemNm+"</td><td>"+pmemEmail+"</td>";
						incumbentTag += "<td><span class='del-btn' idx='"+counter+"'><i class='fa fa-times-circle'></i></span></td></tr>";
						incumbentTag += "<input type='hidden' name='projectMemberList["+counter+"].pmemId' id='ememId"+counter+"' value='"+pmemId+"'>"
						incumbentTag += "<input type='hidden' name='projectMemberList["+counter+"].pmemNm' id='ememNm"+counter+"' value='"+pmemNm+"'>"
						incumbentTag += "<input type='hidden' name='projectMemberList["+counter+"].memEmail' id='ememEmail"+counter+"' value='"+pmemEmail+"'>"
						incumbentTag += "<input type='hidden' name='projectMemberList["+counter+"].memTel' id='ememTel"+counter+"' value='"+pmemTel+"'>"
					$("#ememId"+counter).val(pmemId);
					$("#ememNm"+counter).val(pmemNm);
					$("#ememEmail"+counter).val(pmemEmail);
					$("#ememTel"+counter).val(pmemTel);
					$("#tag-Mem").append(incumbentTag);
					console.log("counter : " + counter);
					counter++;

				});

$(document).on("click", ".del-btn", function() {
	let tag = $(this).closest(".tag-item");
	tag.remove();
	counter--;
});

</script>







