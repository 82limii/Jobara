<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.center {
	text-align: center;
}
</style>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="hero__categories">
                <div class="hero__categories__all">
                    <i class="fa fa-bars"></i>
                    <span>마이페이지</span>
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
        <div class="col-lg-9 row">
        	<div class="card-body">
	        	<div class="card border-dark col-12">
	        		<div class="card-header">FIT-ME</div>
	        		<div class="card-body text-dark">
	        			<h5 class="card-title">AI 기업 추천</h5>
	        			<div class="card-text">
	        				<table id="boardTb" class="table table-bordered col-12">
	        					<thead>
	        						<tr>
										<td colspan="3">
											설문조사를 진행하신 다음날 결과가 반영됩니다.
											<c:if test="${not empty recommend.surDate }">
												<br>
												반영된 설문조사 날짜 : ${recommend.surDate }
											</c:if>
										</td>
	        						</tr>
	        						<tr>
	        							<th class="col-2 ceneter">추천 순위</th>
	        							<th class="col-8 ceneter">기업명</th>
	        							<th class="col-2 ceneter">기업페이지</th>
	        						</tr>
	        					</thead>
	        					<tbody>
	        					<c:choose>
	        						<c:when test="${not empty recommend.recMsg }">
	        							<tr>
	        								<td colspan="3">
	        									<h5>설문조사를 먼저 진행해주세요.</h5>
	        								</td>
	        							</tr>
	        						</c:when>
	        						<c:otherwise>
	        						<tr>
	        							<td>1</td>
	        							<td>${recommend.ememNm1 }</td>
	        							<td><button type="button" class="btn btn-secondary linkBtn" data-url="${cPath }/info/info.do?what=${recommend.ememId1}">이동</button>
	        						</tr>
	        						<tr>
	        							<td>2</td>
	        							<td>${recommend.ememNm2 }</td>
	        							<td><button type="button" class="btn btn-secondary linkBtn" data-url="${cPath }/info/info.do?what=${recommend.ememId2}">이동</button>
	        						</tr>
	        						<tr>
	        							<td>3</td>
	        							<td>${recommend.ememNm3 }</td>
	        							<td><button type="button" class="btn btn-secondary linkBtn" data-url="${cPath }/info/info.do?what=${recommend.ememId3}">이동</button>
	        						</tr>
	        						<tr>
	        							<td>4</td>
	        							<td>${recommend.ememNm4 }</td>
	        							<td><button type="button" class="btn btn-secondary linkBtn" data-url="${cPath }/info/info.do?what=${recommend.ememId4}">이동</button>
	        						</tr>
	        						<tr>
	        							<td>5</td>
	        							<td>${recommend.ememNm5 }</td>
	        							<td><button type="button" class="btn btn-secondary linkBtn" data-url="${cPath }/info/info.do?what=${recommend.ememId5}">이동</button>
	        						</tr>
	        						</c:otherwise>
	        					</c:choose>
	        					</tbody>
	        				</table>
	        			</div>
	        		</div>
        		</div>
	        </div>
        </div>
	</div>
</div>