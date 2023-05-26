<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#insertAsk {
	float: right;
}
#boardTb {
	font-size: 10pt;
}
#searchText {
	width: 300pt;
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
		        <h3>SHARE IT</h3><br>
				 <table id="boardTb" class="table table-bordered">
					<thead>
						<tr>
							<th class="col-md-1" style="text-align: center;">글번호</th>
							<th class="col-md-6" style="text-align: center;">제목</th>
							<th class="col-md-3" style="text-align: center;">등록일</th>
							<th class="col-md-2" style="text-align: center;">작성자</th>
						</tr>
					</thead>
					<tbody id="listBody">
					<c:set value="${pagingVO.dataList }" var="shareItList" />
						<c:choose>
							<c:when test="${empty shareItList }">
								<tr>
									<td colspan="4">게시글이  없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${shareItList }" var="shareItBoard">
									<c:url value="/board/shareItView.do" var="viewURL">
										<c:param name="what" value="${shareItBoard.rebSn }"/>
									</c:url>
									<tr>
										<td>${shareItBoard.rnum }</td>									
										<td><a href="${viewURL }">${shareItBoard.rebTitle }</a></td>
										<td>${shareItBoard.rebDate }</td>
										<td>${shareItBoard.pmemId }</td>
									</tr>
								</c:forEach>							
							</c:otherwise>
						</c:choose>
					</tbody>
					<tfoot>
						<tr>	
							<td colspan="4">
								<input id="insertAsk" type="button" class="btn btn-outline-success linkBtn" value="등록"
								data-url="${cPath }/board/shareItInsert.do"
								/><br>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>

<script>
	

</script>