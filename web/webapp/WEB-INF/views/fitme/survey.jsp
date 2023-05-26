<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
	        		<div class="card-header">
	        			FIT-ME
	        			<c:if test="${not empty survey.surMsg }">
		        			<button type="button" id="valBtn" class="btn btn-outline-secondary" style="float: right;">click</button>
	        			</c:if>
	        		</div>
	        		<div class="card-body text-dark">
	        		<c:choose>
	        			<c:when test="${not empty survey.surMsg }">
			        		<form:form class="form-check" action="${cPath }/fitme/surveyInsert.do" method="post" modelAttribute="survey">
			        			<h5 class="card-title">설문조사</h5>
			        			<h6>${survey.surMsg }</h6>
			        			<p class="card-text">
			        				1. 안정적인 기업을 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur1" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur1" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				2. 자기분야에서 최고가 되고싶다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur2" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur2" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				3. 자율적으로 업무를 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur3" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur3" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				4. 정해진 업무를 반복적으로 수행하는 것을 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur4" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur4" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				5. 새로운 기술을 배우는 것을 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur5" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur5" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				6. 안정적인 수입을 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur6" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur6" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				7. 일처리 방식을 스스로 결정하는 업무를 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur7" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur7" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				8. 금전적 보상을 중요시 한다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur8" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur8" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				9. 일로 인해 건강을 해치는 일을 싫어한다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur9" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur9" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				10. 마음에 맞는 사람과 함께 일하는 것이 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur10" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur10" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				11. 새로운 방법으로 업무를 처리하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur11" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur11" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				12. 조직이나 단체의 나아갈 방향을 제시하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur12" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur12" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				13. 일이 조금 힘들어도 보수가 많은 것을 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur13" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur13" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				14. 독창적인 아이디어와 접근방법을 찾는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur14" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur14" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				15. 목표를 스스로 세우고 그 목표를 달성하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur15" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur15" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				16. 주어진 일을 수행하는 것을 선호하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur16" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur16" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				17. 어려운 문제에 도전하는 것을 주저하지 않는다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur17" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur17" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				18. 충분한 휴식을 중요하게 생각하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur18" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur18" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				19. 미래를 예상하고 이를 위해 노력하는 것보다 오늘 하루를 충실하게 살아내는
			        				<br>것이 중요하다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur19" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur19" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
		        				</p>
		        				<p>
			        				20. 회사의 구성원을 하나로 뭉치게 하는 것은 성과와 인정보단 공감과 격려라고
			        				<br>생각하는 편이다.
			        				<span class="right">
			        					<span><form:radiobutton path="sur20" value="0"/></span> 
			        					<span>예</span>
			        					<span><form:radiobutton path="sur20" value="1"/></span> 
			        					<span>아니오</span>
			        				</span>
			        			</p>
			        			<p class="center">
			        				<span class="center">
				        				<button type="submit" class="btn btn-outline-success">제출</button>
				        				<button type="reset" class="btn btn-outline-danger">초기화</button>
			        				</span>
			        			</p>
		        			</form:form>
	        			</c:when>
	        			<c:otherwise>
	        				<h5 class="card-title">설문조사</h5>
	        				<p>
	        					<h4>오늘의 설문조사를 진행하셨습니다.</h4>
	        					<br>
	        					<h5>다음날 AI 기업 추천 결과를 확인해 주세요.</h5>
	        				</p>
	        			</c:otherwise>
	        		</c:choose>
	        		</div>
	        	</div>
	        </div>
        </div>
	</div>
</div>

<script>
	let valBtn = $("#valBtn").on("click", function() {
		$("[name=sur1][value=0]").attr("checked", true);
		$("[name=sur2][value=1]").attr("checked", true);
		$("[name=sur3][value=1]").attr("checked", true);
		$("[name=sur4][value=0]").attr("checked", true);
		$("[name=sur5][value=1]").attr("checked", true);
		$("[name=sur6][value=0]").attr("checked", true);
		$("[name=sur7][value=1]").attr("checked", true);
		$("[name=sur8][value=0]").attr("checked", true);
		$("[name=sur9][value=0]").attr("checked", true);
		$("[name=sur10][value=1]").attr("checked", true);
		$("[name=sur11][value=1]").attr("checked", true);
		$("[name=sur12][value=0]").attr("checked", true);
		$("[name=sur13][value=1]").attr("checked", true);
		$("[name=sur14][value=0]").attr("checked", true);
		$("[name=sur15][value=1]").attr("checked", true);
		$("[name=sur16][value=0]").attr("checked", true);
		$("[name=sur17][value=1]").attr("checked", true);
		$("[name=sur18][value=1]").attr("checked", true);
		$("[name=sur19][value=0]").attr("checked", true);
		$("[name=sur20][value=0]").attr("checked", true);
	});
</script>