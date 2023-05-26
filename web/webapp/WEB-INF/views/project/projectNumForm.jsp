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
        <div class="col-lg-12">
        	<div class="card border-dark col-12">
        		<div class="card-header">
        			<h4>연락처 등록</h4>
       			</div>
       			<div class="card-body text-dark">
       				<form enctype="multipart/form-data" id="fileForm" action="http://192.168.141.15:8088/recog_name" method="post">
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
       						<label for="proSn" class="col-3">프로젝트 일련번호</label>
       						<form:input path="proSn" type="text" class="form-control col-9" />
       						<form:errors path="proSn" element="span" cssClass="error"/>
       						<p></p>
       					</div>
       					<p></p>
       					<div class="col-12 center">
       						<button type="submit" class="btn btn-success">저장</button>
       						<button type="reset" class="btn btn-danger">취소</button>
       					</div>
       				</form:form>
       			</div>
        	</div>
        </div>
	</div>
</div>
<script>

	<c:if test="${not empty param.contacBuyern }">
	let contacBuyernVal = $("[name=contacBuyern]").val("${param.contacBuyern }");
	</c:if>
	<c:if test="${not empty param.contacDept }">
	let contacNameVal = $("[name=contacName]").val("${param.contacDept }");
	</c:if>
	<c:if test="${not empty param.contacPosition }">
	let contacPosiCdVal = $("[name=contacPosiCd]").val("${param.contacPosition }");
	</c:if>
	<c:if test="${not empty param.contacPern }">
	let contacPersVal = $("[name=contacPers]").val("${param.contacPern }");
	</c:if>
	<c:if test="${not empty param.contacEmail }">
	let contacEmailVal = $("[name=contacEmail]").val("${param.contacEmail }");
	</c:if>
	<c:if test="${not empty param.contacTel }">
	let contacTelVal = $("[name=contacTel]").val("${param.contacTel }");
	</c:if>
	<c:if test="${not empty param.contacBuyera }">
	let contacBuyeraVal = $("[name=contacBuyera]").val("${param.contacBuyera }");
	</c:if>
	<c:if test="${not empty param.contacBuyert }">
	let contacBuyertVal = $("[name=contacBuyert]").val("${param.contacBuyert }");
	</c:if>
	<c:if test="${not empty param.contacBuyerp }">
	let contacBuyerpVal = $("[name=contacBuyerp]").val("${param.contacBuyerp }");
	</c:if>
	<c:if test="${not empty param.proSn }">
	let proSnVal = $("[name=proSn]").val("${param.proSn }");
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