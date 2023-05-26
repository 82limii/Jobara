<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	<div class="col-lg-12">
		<table class="col-md-12 table">
			<thead class="thead-dark">
				<tr>
					<th colspan="6">비밀번호 찾기</th>
				</tr>
			</thead>
					<tr>
						<td colspan="4">
							<input type="button" class="btn btn-outline-dark" onclick="eShow()" value="기업회원" />
							<input type="button" class="btn btn-outline-dark" onclick="pShow()" value="개인회원" />
						</td>
					</tr>	
				</table>
					
		<form id="ememPw" action="${cPath}/find/ememPw" method="POST">
			<div class="col-12" style="display: flex; justify-content: center;">			
				<table class="col-9 table" id="epw">	
					<tr>
						<th colspan="2">기업회원 비밀번호 찾기</th>
					</tr>
					<tr>
						<th class="col-6">아이디</th> 
						<td class="col-6">
							<input type="text" name="ememId" id="ememId" class="input--style-6" placeholder="등록한 아이디">
						</td>
					</tr>
					<tr>
						<th>사업자등록번호</th>
						<td>
							<input type="number" name="ememNum" id="ememNum" class ="input--style-6" placeholder = "사업자등록번호를 '-'없이 입력">
						</td>
					</tr>
					<tr>
						<th>대표자명</th> 
						<td>
							<input type="text" name="ememCeo" id="ememCeo" class="input--style-6" placeholder="등록한 대표자명 ">
						</td>
					</tr>
					<tr>
						<th>담당자 연락처</th>
						<td>
							<input type="number" name="ememMantel" id="memTel" class="input--style-6" placeholder="휴대폰번호를 '-'없이 입력">
						</td>
						<td>
							<input type="button" id="tel" class="btn btn-outline-info" value="인증번호 발송">
						</td>	
					</tr>
					<tr>
						<th>인증번호 확인</th> 
						<td>
							<input type="number" name="checkTel" id="checkTel" class="input--style-6" placeholder="인증번호 입력">
						</td>
						<td>	
							<input type="button" id="check" class="btn btn-outline-info" value="확인">
						</td>	
					<tr>
					<tr>
						<td colspan="2">
							<input class="btn btn-outline-info" type="submit" value="찾기" >
						</td>
					</tr>
				</table>
			</div>
		</form>
				
		<form id="pmemPw" action="${cPath}/find/pmemPw" method="POST">
			<div class="col-12" style="display: flex; justify-content: center;">
				<table class="col-9 table" id="ppw">	
					<tr>
						<th colspan="2">개인회원 비밀번호 찾기</th>
					</tr>
					<tr>
						<th class="col-6">아이디</th> 
						<td class="col-6">
							<input type="text" name="pmemId" id="pmemId" class="input--style-6" placeholder="등록한 아이디">
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="pmemNm" id="pmemNm" class="input--style-6" placeholder = "등록한 이름명">
						</td>
					</tr>
					<tr>
						<th>휴대폰번호</th> 
						<td>
							<input type="number" name="pmemTel" id="memTel" class="input--style-6" placeholder="휴대폰번호를 '-'없이 입력">
						</td>
						<td>
							<input type="button" id="tel" class="btn btn-outline-info" value="인증번호 발송">
						</td>	
					</tr>
					<tr>
						<th>인증번호 확인</th> 
						<td>
							<input type="number" name="checkTel" id="checkTel" class="input--style-6" placeholder="인증번호 입력">
						</td>
						<td>
							<input type="button" id="check" class="btn btn-outline-info" value="확인">
						</td>	
					<tr>
						<td colspan="2">
							<input class="btn btn-outline-info" type="submit" value="변경">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>		
</div>	
	
		
<script>
$('#tel').click(function(){
    let memTel = $('#memTel').val();
    alert('인증번호 발송 완료!')


    $.ajax({
        type: "GET",
        url: "pCall",
        data: {
            "memTel" : memTel,
        },
        success: function(res){
            $('#check').click(function(){
                if($.trim(res) ==$('#checkTel').val()){
                    alert(
                        '인증성공!'
                    )
                }else{
                    alert('잘못된 인증번호 입니다.')
                }
            })
        }
    })
});

window.addEventListener("load", function() {
	$('#epw').hide();
})
window.addEventListener("load", function() {
	$('#ppw').hide();
})
function eShow()  {
	$('#epw').show();
	$('#ppw').hide();
}

function pShow()  {
	$('#ppw').show();
	$('#epw').hide();
}

var msg = "${msg}";
	if (msg != ""){
		alert(msg);
	}
</script>	
	
