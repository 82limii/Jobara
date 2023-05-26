<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 장인슬   -->
<div class="container">
	<div class="col-lg-12">
		<form id="updatePwPmem" name="updatePwPmem" action="${cPath}/find/updatePwPmem" method="POST">
			<div class="col-12" style="display: flex; justify-content: center;">			
				<table class="col-6 table" id="ppw">	
					<tr>
						<th colspan="2">비밀번호 변경</th>
					</tr>
					<tr>
						<th>새 비밀번호</th> 
						<td>
							<input type="hidden" id="id" name="id" value="${id }">
							<input type="password" name="pmemPass" id="pmemPass" class="btn-id" >
						</td>
					</tr>
					<tr>
						<th>새 비밀번호 확인</th>
						<td>
							<input type="password" name="confirmPwd" id="confirmPwd" class ="btn-num" >
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="btn btn-outline-info" type="submit" value="저장" onclick="updatePass()">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>

<script>
function updatePass() {
	if(document.updatePwPmem.pmemPass.value != document.updatePwPmem.confirmPwd.value){
		alert("비밀번호가 일치하지 않습니다.");
	} else {
		alert("비밀번호가 변경되었습니다.")
	}
}

</script>