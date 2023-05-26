<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
	<div class="row">
		<div class="col-lg-3">
			<div class="hero__categories">
				<div class="hero__categories__all">
					<i class="fa fa-bars"></i> <span>기업페이지</span>
				</div>
				<ul>
					<li><a href="#">기업정보</a></li>
					<li><a href="#">재직자관리</a></li>
					<li><a href="#">연락처관리</a></li>
					<li><a href="#">지원자관리</a></li>
					<li><a href="#">Q&A</a></li>
					<li><a href="#">리뷰</a></li>
					<li><a href="#">합격자소서</a></li>
					<li><a href="#">회원정보관리</a></li>
					<li><a href="#">스크랩</a></li>
					<li onclick="location.href='/myCompany/coteReviewList.do'">코테/면접
						리뷰</li>
				</ul>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="card-body">
				<div class="table-wrap">
					<table class="table myaccordion table-hover" id="accordion">
						<tr>
							<th class="col-md-3">학력</th>
							<td>대졸</td>
						</tr>
						<tr>
							<th>학점</th>
							<td>3.5</td>
						</tr>
						<tr>
							<th>경력</th>
							<td>5년차</td>
						</tr>
						<tr>
							<th>기술 스택</th>
							<td>무슨무슨 기술</td>
						</tr>
						<tr>
							<th>어학 성적</th>
							<td>한국어 능력 1급</td>
						</tr>
						<tr>
							<th>자격증</th>
							<td>무슨무슨기사, 무슨무슨기사, 무슨무슨자격증</td>
						</tr>
						<tr>
							<th>대외 활동</th>
							<td>활동</td>
						</tr>
						<tr>
							<th>교육수료</th>
							<td>어디어디 교육 1기 수료</td>
						</tr>
						
					</table>

					<input id="coteM" class="btn-outline-primary btn" type="button"
						value="목록으로"
						onclick="location.href='/myCompany/specDivisionList.do'" />
				</div>
			</div>
		</div>
	</div>
</div>