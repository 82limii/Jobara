<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#delBtn, #ban, #listBtn, #updateBtn {
	float: right;
}
#reBtn {
	width: 300pt;
}
.preBtn {
	margin: 5px;
	margin-top: 0;
}
</style>
<div class="container">
	<div class="row">
		<div class="col-lg-3">
	            <div class="hero__categories">
	                <div class="hero__categories__all">
	                    <i class="fa fa-bars"></i>
	                    <span>개발자 톡톡</span>
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
	        <div class="col-lg-9 grid-margin stretch-card">
             <div class="card-body">
			  <table class="table table-bordered">
			  	<tbody>
			  		<tr>
			  			<th>부모글</th>
			  			<td colspan="2">${shareIt.rebSn2 }</td>
			  		</tr>
			  		<tr>
			  			<th>글번호</th>
			  			<td colspan="2">${shareIt.rebSn }</td>
			  		</tr>
			  		<tr>
				  		<th colspan="3">${shareIt.rebTitle }</th>
			  		</tr>
			  		<tr>
				  		<td>${shareIt.pmemNm }</td>
				  		<td>${shareIt.rebDate }</td>
				  		<td>
		  					<button class="btn btn-outline-danger">
								 <i class="fa fa-bell"></i>
									신고
							</button>
				  		</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3">
			  				${shareIt.rebContents }
			  			</td>
			  		</tr>
			  	</tbody>
			  </table>
			  
			    <div class="form-group">
				<input type="button" id="listBtn" class="btn btn-outline-success linkBtn" value="목록"
					data-url="${cPath }/board/shareItList.do"
				/>
			 	 <c:choose>
  					<c:when test="${authMember.pmemId eq shareIt.pmemId }">
		  				<input id="delBtn" type="button" class="btn btn-outline-danger preBtn" value="삭제" 
		  				data-toggle="modal" data-target="#deleteModal"
		  				/>
		  				<c:url value="/board/shareItUpdate.do" var="updateURL">
							<c:param name="what" value="${shareIt.rebSn }"/>
						</c:url>
						<input type="button" id="updateBtn" class="btn btn-outline-secondary btn-md linkBtn btnControl preBtn" value="수정"
							data-url="${updateURL }"
						/>
  					</c:when>
  				</c:choose>
  				<c:url value="/board/shareItInsert.do" var="insertURL">
					<c:param name="rebSn2" value="${shareIt.rebSn }" />
				</c:url>
  				<input type="button" value="답글 등록" class="btn btn-outline-primary linkBtn"
  					data-url="${insertURL }" 
  				/>
			  </div>
			  
			</div>
	</div>
</div>
</div>
