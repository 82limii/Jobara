<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
        <table class="table table-bordered">
        	<thead>
        		<tr>
        			<th class="col-12">기업 검색</th>
        		</tr>
        	</thead>
        	<tbody>
        		<tr>
        			<td>
        			<div class="row col-12">
		       			<c:forEach items="${searchResult }" var="emember">
		       			<div class="card col-4">
		       				<c:set value="${emember.info }" var="info" />
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">${emember.ememNm }</a></h5>
		       					<p>${info.comBussiness }</p>
		       					<p>${info.comTake }</p>
		       					<p>${info.comHeadcnt }</p>
		       					<p>${info.comFounddate }</p>
		       					<a class="card-link" href="${cPath }/info/info.do?what=${emember.ememId}">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			</c:forEach>
        			</div> 
        			</td>
        		</tr>
        	</tbody>
        </table>
		</div>
    </div>
</div>
