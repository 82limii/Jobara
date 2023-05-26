<%@page import="kr.co.jobara.member.vo.EmemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>        
    
<style>
#payMain {
   float:none; 
   margin:0 auto;
}
</style>      
    
<div class="container">
    <div class="row">
        <div id="payMain" class="col-lg-9" >
			<form id="payForm">
			<table class="table" style="text-align: center;">
				<thead class="thead-dark">
					<tr>
						<th colspan="3">후원 결제 시스템</th>
					</tr>
				</thead>
					<tr>
						<th>후원 안내</th>
						<td>사이트 운영에 도움이 됩니다.</td>
					</tr>
					<tr>	
						<th>후원 비용</th>
						<td>10000원</td>
					</tr>			
					<tr>
						<th>이름</th>
						<td>
							<input type="text" id="name" placeholder = "등록한 이름명" required>
						</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>
							<input type="text" id="tel" placeholder = "연락처를 '-'없이 입력" required>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="email" id="mail" placeholder = "이메일 형식으로 입력" required>
						</td>
					</tr>
				</table>	
					<br>
					개인정보 제공에 동의합니다.<input type="checkbox" id="check" required><br>
					이용약관 및 유의사항에 동의합니다.<input type="checkbox" id="check" required>
					<br><br>
				<div style="text-align: center;">
					<input id="pay" class="btn btn-outline-success btn-lg" type="button" value="후원하기" >
				</div><br><br><br>			
				</form>
		</div>
	</div>
</div>	

<script>

//클라이언트
$("#pay").on("click", function(){	
	
	var name = document.getElementById('name').value;
	var tel = document.getElementById('tel').value;
	var mail = document.getElementById('mail').value;
	
	
	IMP.init('imp88723848');
	
    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
        pg: "html5_inicis",
        pay_method: "card",
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: "JOBARA 후원",
        amount: 10000,
        buyer_email : mail,
		buyer_name : name,
		buyer_tel : tel
	}, function(rsp) {
		if ( rsp.success ) {
	        var msg = '결제가 완료되었습니다. ';
	        msg += '[결제 금액 :' + rsp.paid_amount + '원 ';
	        msg += '카드 승인번호 :' + rsp.apply_num + ']';
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	    }

	    alert(msg);   
	});//pay
	


}); //결제 클릭 이벤트

</script>

