<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script src="${cPath }/resources/js/fancytree/jquery.fancytree-all-deps.min.js"></script>
<script src="${cPath }/resources/js/fancytree/jquery.fancytree-all.min.js"></script>
<link href="${cPath }/resources/js/fancytree/skin-win8/ui.fancytree.css" rel="stylesheet">
<!-- 장인슬 -->
<div class="container">
	<div class="col-6 center">
	</div>
	<div class="col-6 center">
		<div class="row">
			<h2>문서함</h2>
			<div id="fileTree" class="col">
				<ul id="ft-id-1"
					class="ui-fancytree fancytree-container fancytree-plain"
					tabindex="0" role="tree" aria-multiselectable="true"
					aria-activedescendant="ui-id-1">
					<li role="treeitem" aria-selected="false" class="fancytree-lastsib" id="ui-id-1">
					<span class="fancytree-node fancytree-active fancytree-lastsib fancytree-statusnode-error fancytree-exp-nl fancytree-ico-c">
					<span class="fancytree-expander"></span>
					<span role="presentation" class="fancytree-icon"></span>
					<span class="fancytree-title">Load error!</span></span>
					</li>
				</ul>
			</div>
		</div>
		<form action="${cPath }/ftp/uploadFile.do" id="uploadForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="parentPath" placeholder="전송폴더" readonly required /> 
			<input type="file" name="uploadFile" required /><br> 
			<input class="btn btn-secondary btn-lg" type="submit" value="업로드" style="margin-top: 2%;"/> 
			<input class="btn btn-secondary btn-lg" type="button" value="압축파일로 다운로드" id="zipBtn" style="margin-top: 2%;"/>
		</form>
		<form action="${cPath }/ftp/fileDownload.do" id="downloadForm"
			method="post"></form>
		<c:set var="parentPath" value="${parentPath }" />
	</div>
</div>
<script type="text/javascript">
  	$("#fileTree").fancytree({
  		checkbox : function(event, data){
  			return !data.node.isFolder();
  		}
  	 	, selectMode : 2
  		, source: {
  			 url:"${cPath }/ftp/fileBrowser.do",
  			 cache:false
  		}
  		, lazyLoad: function(event, data){
  	      var node = data.node;
  	      data.result = {
  	    	url:"${cPath }/ftp/fileBrowser.do",
  	        data: {path: node.getKeyPath()},
  	        cache: false
  	      };
  	 	}
  	 	, activate : function(event, data){
  	 		if(data.node.isFolder())
  	 			uploadForm.parentPath.value = data.node.getKeyPath();
  	 		else
  	 			uploadForm.parentPath.value = "";
  	 	}
  	 	, select : function(event, data){
  	 		console.log(data);
  	 	}
  	<c:if test="${not empty parentPath}">
  	 	, init:function(event, data){
		    data.tree.loadKeyPath("${parentPath}", function(data, status) {
		    	if(data.key){
			    	if(status=="ok")
			        	data.tree.expandAll(true);
		    	}
		    });
  	 	}
	</c:if>
  	}); 
  	const TREE = $.ui.fancytree.getTree("#fileTree");
	const DOWNLOADFORM = $("#downloadForm");
	$("#zipBtn").on("click", function(){
		let nodes = TREE.getSelectedNodes();
		
  		if(!nodes || nodes.length==0) return false;
  		
  		DOWNLOADFORM.empty();
  		$.each(nodes, function(index, node){
  			let file = node.getKeyPath();
  			DOWNLOADFORM.append(
  				$("<input>").prop({
  					name:"downFiles"
  					, type:"hidden"
  					, readonly:true
  					, value:file
  				})		
  			);
  		});
  		DOWNLOADFORM.submit();
	});
  </script>
