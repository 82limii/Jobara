
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<form:form modelAttribute="helps" method="post" enctype="multipart/form-data" >
<div class="container">
	<div class="row">
		<div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>고객센터</span>
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
			<div class="row">
			    <div class="col-md-12">
			    	<br>
			        <h2 class="text-center">1:1 문의</h2>
			            <div class="table table-responsive">
		                    <table class="table table-striped">
					            <tr>
					                <td>제목</td>
					                <td colspan="3">
					                	<form:input path="helpsTitle" cssClass="form-control" required="required" />
							            <form:errors path="helpsTitle" element="span" cssClass="error" />
					                </td>
					            </tr>
					            	<c:choose>
										<c:when test="${authMember.userType eq 'Emember'}">
							                <form:hidden path="helpsId" cssClass="form-control" required="required" value="${authMember.ememId }" />
					                		<form:errors path="helpsId" element="span" cssClass="error" />
										</c:when>
										<c:otherwise>
							                <form:hidden path="helpsId" cssClass="form-control" required="required" value="${authMember.pmemId }" />
					                		<form:errors path="helpsId" element="span" cssClass="error" />
										</c:otherwise>
									</c:choose>
					            <tr>
					                <td><label for="kindList">문의종류코드</label></td>
					                <td colspan="3">
					                	<form:select path="helpsKindCd" class="custom-select" id="kindList">
							               <c:forEach items="${helList }" var="hel">
							               		<option value="${hel.commCd }" <c:if test="${hel.commNm eq helps.helpsKindCd }">selected</c:if> >${hel.commNm }</options>
							               		<form:options items="${helpsKindCd }" />
							               </c:forEach>
							            </form:select>
					                </td>
					            </tr>
					            <tr>
					                <td>파일첨부</td>
					                <td colspan="3">
					                	<form:input type="file" path="helpsFiles" cssClass="form-control" />
							            <form:errors path="helpsFiles" element="span" cssClass="error" />
					                </td>
					            </tr>
					            <tr>
					                <td>내용</td>
					                <td colspan="3">
							             <label for="helpsContent"></label> <form:errors path="helpsContents" cssclass="error" element="span" />
						            	<form:textarea path="helpsContents" class="form-control" id="helpsContent" rows="4"></form:textarea>
					                </td>
					            </tr>
					             
					            <tr>  
					                <td colspan="4"  class="text-center">
					                    <input type="submit" value="등록" class="btn btn-warning right">
						            	<c:choose>
											<c:when test="${authMember.userType eq 'Emember'}">
							                    <input type="button"  class="btn btn-primary linkBtn right" data-url="${cPath }/helps/com/helpsList.do" value="목록">
											</c:when>
											<c:otherwise >
							                    <input type="button"  class="btn btn-primary linkBtn right" data-url="${cPath }/helps/helpsList.do" value="목록">
											</c:otherwise>
										</c:choose>
					                </td>
					            </tr>
			          		</table>
			            </div>
			    </div>
			</div>
        </div>
	</div>
</div>
</form:form>
