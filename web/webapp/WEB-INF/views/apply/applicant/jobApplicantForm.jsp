<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   

<form:form modelAttribute="incumbent" method="post" enctype="multipart/form-data" >
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
        	<div class="row">
			    <div class="col-md-12">
			    	<br>
			        <h2 class="text-center">재직자 등록</h2>
		            <div class="table table-responsive">
		                <table class="table table-striped">
				            <tr>
				                <td><label for="posiList">직급코드</label></td>
				                <td colspan="3">
				                	<form:select path="empPosiCd" class="custom-select" id="posiList">
						               <c:forEach items="${posiList }" var="pos">
						               		<option value="${pos.commCd }" <c:if test="${pos.commNm eq incumbent.empPosiCd }">selected</c:if> >${pos.commNm }</options>
						               		<form:options items="${empPosiCd }" />
						               </c:forEach>
						            </form:select>
				                </td>
				            </tr>
				            <tr>
				                <td><label for="deptList">직무코드</label></td>
				                <td colspan="3">
				                	<form:select path="empDeptCd" class="custom-select" id="deptList">
						               <c:forEach items="${deptList }" var="dep">
						               		<option value="${dep.commCd }" <c:if test="${dep.commNm eq incumbent.empDeptCd }">selected</c:if> >${dep.commNm }</options>
						               		<form:options items="${empDeptCd }" />
						               </c:forEach>
						            </form:select>
				                </td>
				            </tr>
				            <tr>
				                <td>연봉</td>
				                <td colspan="3">
				                	<form:input path="empSalary" cssClass="form-control" required="required" value="${incumbent.empSalary }"/>
				                	<form:errors path="empSalary" element="span" cssClass="error" />
				                </td>
					            </tr>
				            <tr>  
				                <td colspan="4"  class="text-center">
				                    <input type="submit" value="등록" class="btn btn-success right" />
				                    <input type="button"  class="btn btn-primary linkBtn right" data-url="${cPath }/incumbent/com/incumbentList.do" value="목록" />
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