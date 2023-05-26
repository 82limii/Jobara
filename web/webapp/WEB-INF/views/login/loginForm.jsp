<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>       

<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/util.css">
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/bootstrap/css/bootstrap.min.css">
<%-- <link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/animate/animate.css"> --%>
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/css-hamburgers/hamburgers.min.css">
<%-- <link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/select2/select2.min.css"> --%>
<%-- <link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/util.css"> --%>
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/main.css">

<!-- 장인슬 -->

<script>

window.addEventListener("load", function() {
	$('#ememlogin').hide();
})

function setDisplay(){
	
	
    if($('input:radio[id=pmem]').is(':checked')){
        $('#ememlogin').hide();
        $('#pmemlogin').show();
    }else{
    	$('#pmemlogin').hide();
        $('#ememlogin').show();
    }
}
</script>

	
	
<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
					<span class="login100-form-title">
						<b>Login</b>
					</span>
				
<!-- 					<div class="form-check"> -->
<!-- 					  <input class="form-check-input" type="radio" name="flexRadioDefault" id="pmem" onchange="setDisplay()" checked> -->
<!-- 					  <label class="form-check-label" for="pmem">개인회원</label> -->
<!-- 					</div> -->
<!-- 					<div class="form-check"> -->
<!-- 					  <input class="form-check-input" type="radio" name="flexRadioDefault" id="emem" onchange="setDisplay()">					   -->
<!-- 					  <label class="form-check-label" for="emem"> 기업회원</label> -->
<!-- 					</div> -->
				<div class="container-login100-form-btn">
					<b style="color: gray; ">기업회원 또는 개인회원 아이디를 입력하세요.</b>
				</div>

				<form class="login100-form validate-form col-12" action="${cPath}/login/loginProcess.do" id="pmemlogin" method="post">
					<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="memId" placeholder="아이디">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="wrap-input100 validate-input">
						<input class="input100" type="password" name="memPw" placeholder="비밀번호">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
						<input class="login100-form-btn" type="submit" value="로그인"/>
					</div>
				</form>
				
				
				<form class="login100-form validate-form col-12" action="${cPath}/login/loginProcess.do" id="ememlogin" method="post">
					<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="memId" placeholder="아이디">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="wrap-input100 validate-input">
						<input class="input100" type="password" name="memPw" placeholder="비밀번호">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
						<input class="login100-form-btn" type="submit" value="로그인"/>
					</div>
				
				</form>
				
					<c:if test="${message ne ''  or message eq null}">
					   <div class="error" style="color:red;">${message } </div>
					</c:if>
					<c:if test="${errors ne null  and errors eq ''}">
					   <div class="error">${errors } </div>
					</c:if>				
					
					<div class="text-center p-t-12 center"><br>
						<a href="${cPath }/find/memberfindpw.do">비밀번호 찾기</a>&nbsp;
						<a href="${cPath }/find/memberfindid.do">아이디 찾기</a>&nbsp;	
						<a href="${cPath }/member/memberForm.do">회원가입</a>&nbsp;
					</div>
			</div>
		</div>
	</div>
	











