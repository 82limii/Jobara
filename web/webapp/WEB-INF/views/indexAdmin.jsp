<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/util.css">
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/util.css">
<link rel="stylesheet" type="text/css" href="${cPath }/resources/form/login/main.css">

<style>
.table td {
	text-align: center;
	vertical-align: middle;
}
</style>

<div class="row col-12">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card">
			<div class="card-body row">
				<h4>log</h4>
				<br>
				<br>
				<div id="listBody" class="col-12 row">
				</div>
				<div class="pagingArea"></div>
			</div>
		</div>
	</div>
</div>
<form id="searchForm">
	<input type="text" name="page" />
</form>

<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json"
		, success:function(resp) {
			listBody.empty();
			pagingArea.empty();
			$("[name=page]").val("");
			let logList = resp.dataList;
			let pagingHTML = resp.pagingHTMLBS;
			let pTags = [];
			if(logList && logList.length > 0) {
				$.each(logList, function(index, data) {
					let pTag = null;
					if(data.pmemId) {
						pTag = $("<p>").addClass("col-12 gird-margin").html(data.logTime+"&nbsp;"+data.pmemId+"&nbsp;"+data.logAdd);	
					}
					if(data.ememId) {
						pTag = $("<p>").addClass("col-12 gird-margin").html(data.logTime+"&nbsp;"+data.ememId+"&nbsp;"+data.logAdd);	
					}
					
					pTags.push(pTag);
				});
			} else {
				pTags.push(
					$("<p>").html("로그가 존재하지 않습니다.")	
				);
			}	//	if~else end
			listBody.append(pTags);
			pagingArea.html(pagingHTML);
		}
	}).submit();
	
	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		searchForm[0].page.value=page;
		searchForm.submit();
		return false;
	});

</script>