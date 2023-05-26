<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<!-- 장인슬 -->
<style>
#thum {
	width: auto;
	height: 100px;
}
.table td, .table th, .table thead th {
   text-align: center;
   vertical-align: middle;
}

</style>

<div class="container">
	<div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>기업페이지</span>
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
        <div class="col-lg-9">
		<table class="col-md-12 table">
				<thead class="thead-dark">
					<tr>
						<th colspan="3">핸드폰 본인 인증</th>
					</tr>
				</thead>
					<tr>
						<th>휴대폰번호</th> 
						<td>
							<input type="number" name="ememMantel" id="memTel" class="btn-tel" placeholder="휴대폰번호를 '-'없이 입력">
						</td>
						<td>
							<input type="button" id="tel" class="btn btn-outline-info" value="인증번호 발송">
						</td>	
					</tr>
					<tr>
						<th>인증번호 확인</th> 
						<td>
							<input type="number" name="checkTel" id="checkTel" class="btn-tel" placeholder="인증번호 입력">
						</td>
						<td>
							<input type="button" id="check" class="btn btn-outline-info" value="확인">
						</td>	
					<tr>
				<tr>
					<td colspan="3">
						<input class="btn btn-outline-info" type="button" value="조회" disabled="disabled" onclick="location.href='${cPath }/member/com/emyPage.do';">
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>

<script>

$(document).ready(function () {
	$('#checkTel').on('input change', function(){
		if($(this).val()==''){
			$('#join').prop('disabled',true);
		}else{
			$('#join').prop('disabled',false);
		}
	});
});

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

</script>
