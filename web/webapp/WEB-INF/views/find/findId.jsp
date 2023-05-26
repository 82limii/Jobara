<%@page import="kr.co.jobara.member.dao.PmemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	    
    
    
<div class="container">
	<div class="col-lg-11" style="text-align: center; margin: 10%;">

			<h2>아이디는 [ ${id} ] 입니다.</h2><br><br>
			

		<input class="btn btn-primary" type="button" value="로그인 페이지" onclick="location.href='${cPath }/login/loginForm.do'"/>
		<input class="btn btn-primary" type="button" value="메인 페이지" onclick="location.href='${cPath }/'">
	</div>
</div>
