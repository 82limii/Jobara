/**
 * 
 * $("#searchForm").paging({
 * 	  listBody:$("#listBody")
 *    , pagingArea : $(".pagingArea")
 *    , searchUI : $("#searchUI")
 *    , searchBtn : "#searchBtn"
 *    , success:function(resp){
 *    
 *    }
 * });
 */
$.fn.paging=function(obj){
	let success = obj.success;
	let listBody = obj.listBody;
	let pagingArea = obj.pagingArea;
	let searchUI = obj.searchUI;
	let searchBtn = obj.searchBtn;
	let searchForm = this;
	if(success){
		this.on("submit", function(event){
			event.preventDefault();
			let url = this.action;
			let method = this.method;
			let data = $(this).serialize(); 
// 		console.log(data);
			$.ajax({
				url : url,
				method : method,
				data : data,
				dataType : "json", // Accept, Content-Type
				success : success
			});
			return false;
		}).submit();
	}// if end
	
	searchUI.on("click", searchBtn, function(){
		let inputs = searchUI.find(":input[name]");
		$.each(inputs, function(index, input){
			let value = $(input).val();
			let name = $(input).attr("name");
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
//	
//	searchBtn.on("click", function() {
//		
//	});
	
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm[0].page.value=page;
		searchForm.submit();
		return false;
	});
	
	return this;
}