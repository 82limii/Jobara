<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    
<div class="container">

		<table class="col-md-12 table">
			<thead class="thead-dark">
				<tr>
					<th colspan="3">회원가입 선택</th>
				</tr>
			</thead>
		</table>
			<div class="col-3 row center">
				<input type="button" class="btn btn-outline-dark btn-lg" style="margin: 20px; padding: 10%;" onclick="ememPage()" value="기업회원가입"/>
				<input type="button" class="btn btn-outline-dark btn-lg" style="margin: 20px; padding: 10%;" onclick="pmemPage()" value="개인회원가입"/>
			</div>	
</div>

	

<script>
function ememPage(){
	window.location.href = '${cPath}/member/ememberForm.do'	
}

function pmemPage(){
	window.location.href = '${cPath}/member/pmemberForm.do'		
}

</script>
	