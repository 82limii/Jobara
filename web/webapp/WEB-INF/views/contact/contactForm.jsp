<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
.center {
	text-align: center;
}
.left {
	float: left;
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
        	<div class="card border-dark col-12">
        		<div class="card-header">
        			<h4>연락처 등록</h4>
       			</div>
       			<div class="card-body text-dark">
       				<form enctype="multipart/form-data" id="fileForm" action="https://192.168.141.15:5443/recog_name" method="post">
       					<div class="col-5 center">
       						<input type="file" name="nameCard" accept="image/*" onchange="setThumbnail(event);" class="form-control col-12"/>
 							<div id="imgContainer"></div>
 							<br>
 							<button type="submit" id="flaskBtn" class="btn btn-info" >명함 인식</button>
       					</div>
       				</form>
       				<p></p>
       				<form:form class="row col-12" modelAttribute="contact" method="post">
       					<div class="col-12 row">
       						<label for="contacBuyern" class="col-3">거래처명</label>
       						<form:input path="contacBuyern" type="text" class="form-control col-9" />
       						<form:errors path="contacBuyern" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacName" class="col-3">부서명</label>
       						<form:input path="contacName" type="text" class="form-control col-9" />
       						<form:errors path="contacName" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacPosiCd" class="col-3">직책</label>
       						<form:input path="contacPosiCd" type="text" class="form-control col-9" />
       						<form:errors path="contacPosiCd" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacPers" class="col-3">이름</label>
       						<form:input path="contacPers" type="text" class="form-control col-9" />
       						<form:errors path="contacPers" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacEmail" class="col-3">이메일</label>
       						<form:input path="contacEmail" type="text" class="form-control col-9" />
       						<form:errors path="contacEmail" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacTel" class="col-3">핸드폰번호</label>
       						<form:input path="contacTel" type="text" class="form-control col-9" />
       						<form:errors path="contacTel" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacBuyera" class="col-3">거래처 주소</label>
       						<form:input path="contacBuyera" type="text" class="form-control col-9" />
       						<form:errors path="contacBuyera" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacBuyert" class="col-3">거래처 전화번호</label>
       						<form:input path="contacBuyert" type="text" class="form-control col-9" />
       						<form:errors path="contacBuyert" element="span" cssClass="error"/>
       						<p></p>
       						<label for="contacBuyerp" class="col-3">거래처 홈페이지</label>
       						<form:input path="contacBuyerp" type="text" class="form-control col-9" />
       						<form:errors path="contacBuyerp" element="span" cssClass="error"/>
       						<p></p>
       					</div>
       					<p></p>
       					<div class="col-12 center">
       						<button type="submit" class="btn btn-success">저장</button>
       						<button type="reset" class="btn btn-danger">취소</button>
       					</div>
       					<form:input path="ememId" type="hidden" value="${authMember.ememId }"/>
       					<form:input path="contacSn" type="hidden" value="${contacSn }"/>
       				</form:form>
       			</div>
        	</div>
        </div>
	</div>
</div>
<script>

	<c:if test="${not empty param.buyern_str }">
	let contacBuyernVal = $("[name=contacBuyern]").val("${param.buyern_str }");
	</c:if>
	<c:if test="${not empty param.dept_str_ori }">
	let contacNameVal = $("[name=contacName]").val("${param.dept_str_ori }");
	</c:if>
	<c:if test="${not empty param.posi_str }">
	let contacPosiCdVal = $("[name=contacPosiCd]").val("${param.posi_str }");
	</c:if>
	<c:if test="${not empty param.pern_str }">
	let contacPersVal = $("[name=contacPers]").val("${param.pern_str }");
	</c:if>
	<c:if test="${not empty param.mail_str }">
	let contacEmailVal = $("[name=contacEmail]").val("${param.mail_str }");
	</c:if>
	<c:if test="${not empty param.phone_str }">
	let contacTelVal = $("[name=contacTel]").val("${param.phone_str }");
	</c:if>
	<c:if test="${not empty param.buyera_str }">
	let contacBuyeraVal = $("[name=contacBuyera]").val("${param.buyera_str }");
	</c:if>
	<c:if test="${not empty param.tel_str }">
	let contacBuyertVal = $("[name=contacBuyert]").val("${param.tel_str }");
	</c:if>
	<c:if test="${not empty param.page_str }">
	let contacBuyerpVal = $("[name=contacBuyerp]").val("${param.page_str }");
	</c:if>
	
	$("[name=nameCard]").on("click", function() {
		$("#imgContainer").empty();
	});
	
	function setThumbnail(changeEvent) {
		var reader = new FileReader();
		
		reader.onload = function(loadEvent) {
			
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			document.querySelector("div#imgContainer").appendChild(img);
			$(changeEvent.target).data('nameBase', event.target.result);
			console.log(loadEvent);
		};
		
		reader.readAsDataURL(event.target.files[0]);
	};
	
	window.addEventListener("load", function() {
		
		$("#flaskBtn").on("submit", function(event) {
			event.preventDefault();
			let url = $(this).action;
			let method = $(this).method;
			let formData = new FormData($("#fileForm").get(0));
			
			var xhr = new XMLHttpRequest();
            xhr.open(method, url);
            xhr.setRequestHeader('Content-Type', 'multipart/form-data');
            
            xhr.addEventListener('readystatechange', function(event) {
            	const target = event; 
            	if (target.readyState === XMLHttpRequest.DONE) { 
            		const status = target; 
            		if (status === 0 || (status >= 200 && status < 400)) { 
            			xhr.send(formData);
            			// 요청이 정상적으로 처리 된 경우 
            			let result = target.responseText;
            			console.log("성공");
            		} else {
            			// 에러가 발생한 경우 
            			console.log("에러발생");
            		} 
            	}

            });
            
			return false;
		});
	});
	
</script>