<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<style>
.table td, .table th, .table thead th {
   text-align: center;
   vertical-align: middle;
}   
.input--style-6 {
	width : 300px;
}   

</style>

<!-- 장인슬 -->
<div class="container">
	<div class="col-lg-11">
		<table class="col-md-12 table">
			<thead class="thead-dark">
				<tr>
					<th colspan="6">아이디 찾기</th>
				</tr>
			</thead>
					<tr>
						<td colspan="4">
							<input type="button" class="btn btn-outline-dark" onclick="eShow()" value="기업회원" />
							<input type="button" class="btn btn-outline-dark" onclick="pShow()" value="개인회원" />
						</td>
					</tr>
				</table>
				
		<form id="ememId" action="${cPath}/find/ememId" method = "POST">
			<div class="col-12" style="display: flex; justify-content: center;">			
				<table class="col-6 table" id="eid">		
					<tr>
						<th colspan="2">기업회원 아이디 찾기</th>
					</tr>
					<tr>
						<th>대표자명</th> 
						<td>
							<input type="text" name="ememCeo" id="ememCeo" class="btn-man" placeholder = "등록한  대표자명">
						</td>
					</tr>
					<tr>
						<th>사업자등록번호</th>
						<td>
							<input type="number" name="ememNum" id="ememNum" class="btn-num" placeholder = "사업자등록번호를 '-'없이 입력">
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="email" name="ememEmail" id="ememEmail" class="btn-email" placeholder = "이메일 형식에 맞춰 작성">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="btn btn-outline-info" type="submit" value="찾기">
						</td>
					</tr>
				</table>
				</div>	
			</form>		
			
		<form name="pmemId" action="${cPath}/find/pmemId" method = "POST">
			<div class="col-12" style="display: flex; justify-content: center;">	
				<table class="col-6 table" id="pid">		
					<tr>
						<th colspan="2">개인회원 아이디 찾기</th>
					</tr>
					<tr>
						<th>이름</th> 
						<td>
							<input type="text" name="pmemNm" id="pmemNm" class="btn-name" placeholder = "등록한 이름명">
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="email" name="pmemEmail" id="pmemEmail" class ="btn-email" placeholder = "이메일 형식에 맞춰 작성">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="btn btn-outline-info" type="submit" value="찾기">
						</td>
					</tr>
				</table>
			</div>	
		</form>	
		</div>		
	</div>	

<script>

window.addEventListener("load", function() {
	$('#eid').hide();
})
window.addEventListener("load", function() {
	$('#pid').hide();
})
function eShow()  {
	$('#eid').show();
	$('#pid').hide();
}

function pShow()  {
	$('#pid').show();
	$('#eid').hide();
}

var msg = "${msg}";
	if (msg != ""){
		alert(msg);
	}

</script>
	
