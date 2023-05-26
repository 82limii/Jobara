//====================댓글 CRUD==========================
	$(document).ajaxError(function(event, xhr, settings, error){
		console.log(error);
	});
	function commonSuccess(resp){
		if(resp.result == "OK"){
			replyInsertForm.get(0).reset();
			replyModal.modal("hide");
			replyChildModal.modal("hide");
			searchForm.submit();
		}else if(resp.message){
			alert(resp.message);
		}
	}
	// 대댓글
	function childReply(event){
		let reply = $(this).parents("tr:first").data("reply");
		let repParent = reply.repNo;
		replyChildForm.get(0).repParent.value = repParent;
		replyChildModal.modal("show");
	}
	// 수정
	function updateReply(geta){
		$("input[name='commSn']").attr("value",geta);
		let reply = $(this).parents("tr:first").data("reply");
		for(let prop in reply){
			$(replyUpdateForm).find("[name='"+prop+"']").val(reply[prop]);
		}
		replyModal.modal("show");
	}
	// 삭제
	function deleteReply(geta){
		$("input[name='commSn']").attr("value",geta);
		let reply = $(this).parents("tr:first").data("reply");
		for(let prop in reply){
			$(replyUpdateForm).find("[name='"+prop+"']").val(reply[prop]);
		}
		replyDeleteModal.modal("show");
	}
	
	let listTable = $("#replyTable").on("click", ".updateBtn", updateReply)
									.find("#listBody");
	
	let replyModal = $("#replyModal").on("hidden.bs.modal", function(){
		$(this).find("form").get(0).reset();
	}).on("shown.bs.modal", function(){
		$(this).find(":text:first").focus().select();
	});
	let replyChildModal = $("#replyChildModal").on("hidden.bs.modal", function(){
		$(this).find("form").get(0).reset();
	}).on("shown.bs.modal", function(){
		$(this).find(":text:first").focus().select();
	});
	let replyDeleteModal = $("#replyDeleteModal").on("hidden.bs.modal", function(){
		$(this).find("form").get(0).reset();
	}).on("shown.bs.modal", function(){
		$(this).find(":password").focus();
	});
	
	let options ={
		dataType : "json",
		success :commonSuccess,
		beforeSubmit:function(){
			replyDeleteModal.modal("hide");
		}
	}
	
	let replyInsertForm = $("#replyInsertForm").ajaxForm(options);
	let replyUpdateForm = replyModal.find("form").ajaxForm(options);
	let replyChildForm = replyChildModal.find("form").ajaxForm(options);
	let replyDeleteForm = replyDeleteModal.find("form")
							.ajaxForm(options);
//========================================================	
	
//====================덧글 페이징=======================
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a" ,function(){
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		searchForm.find("[name='page']").val(1);
		return false;
	}).on("click", ".scrollTop", function(event){
		$(this).parents("[class*='overflow']:first").animate({scrollTop:0}, 1000);
	});;
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			pagingArea.empty();
			let replyList = resp.dataList;
			let trTags = [];
			if(replyList){
				$(replyList).each(function(idx, reply){
					let tr = $("<tr>");
					tr.append(
							$("<td>").html(reply.pmemId)
							, $("<td>").html(reply.commContents
											+"<c:choose><c:when test='${"+authMember.pmemId+" eq "+reply.pmemId+"}'>"
											+"<small><a href='#' onclick='updateReply("+reply.commSn+");'><i class='fa fa-pencil' aria-hidden='true' style='margin-left:5px;'></i></a></small>"
											+"<small><a href='#' onclick='deleteReply("+reply.commSn+");'><i class='fa fa-times-circle' style='margin-left:5px;'></i></a></small>"
											+"</c:when></c:choose>"	
							)
							, $("<td>").html(reply.commDate)
					).data("reply", reply);
					trTags.push(tr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").text("댓글이 없음.")									
					)
				);
			}
			if(resp.currentPage==1)
				listTable.html(trTags);
			else
				listTable.append(trTags);
			if(replyList.length>0)
				pagingArea.html(resp.pagingHTMLReplyType);
			let scrollElement = listTable.parents("[class*='overflow']:first");
			scrollElement.animate({scrollTop:scrollElement.get(0).scrollHeight}, 3000);
		},
		error : function(errResp) {
			console.log(errResp);
		}
	}).submit(); // 페이지 로드 후 1페이지의 댓글 요청.
//========================================================