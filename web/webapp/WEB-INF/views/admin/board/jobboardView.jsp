<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<style>
.table td, .table th {
	text-align: left;
}
#logo {
	text-align: center;
}
.table .condition {
	vertical-align: top;
}
#thumnail {
	width: auto;
	height: 70px;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
		<table class="table" style="background-color: #fff">
			<thead>
			<tr>
				<th>채용정보</th>
			</tr>
			<tr>
			<td>
				<table class="table">
					<tr>
						<th colspan="2">${jobBoard.ememNm }</th>
						<th id="logo" rowspan="2" colspan="1">
							<img id="thumnail" src="<spring:url value='/image/${jobBoard.ememPic }'/>"alt="companyPic" />
						</th>
					</tr>
					<tr>
						<th colspan="2">${jobBoard.jobTitle }</th>
					</tr>
					<tr>
						<th class="col-4">지원 자격</th>
						<th class="col-4">근무 조건</th>
						<th class="col-4">기업정보</th>
					</tr>
					<tr>
						<td class="condition">
							<table class="table">
								<tr>
									<td class="col-5">경력</td>
									<td class="col-7">${jobBoard.jobCar }</td>
								</tr>
								<tr>
									<td>학력</td>
									<td>${jobBoard.jobEdu }</td>
								</tr>
								<tr>
									<td>우대사항</td>
									<td>${jobBoard.jobPref }</td>
								</tr>								
							</table>
						</td>
						<td class="condition">
							<table class="table">
								<tr>
									<td class="col-5">고용형태</td>
									<td class="col-7">${jobBoard.jobEmp }</td>
								</tr>
								<tr>
									<td>급여</td>
									<td>${jobBoard.jobPay }</td>
								</tr>
								<tr>
									<td>지역</td>
									<td>${jobBoard.jobLoc }</td>
								</tr>								
								<tr>
									<td>시간</td>
									<td>${jobBoard.jobTime }</td>
								</tr>								
								<tr>
									<td>직급</td>
									<td>${jobBoard.jobPosi }</td>
								</tr>								
							</table>
						</td>
						<td class="condition">
							<table class="table">
								<tr>
									<td class="col-5">산업</td>
									<td class="col-7">${jobBoard.comBussiness }</td>
								</tr>
								<tr>
									<td>직원수</td>
									<td>${jobBoard.comHeadcnt }</td>
								</tr>
								<tr>
									<td>
										<c:url value="${cPath }/member/admin/ememberView.do" var="retrieveURL">
											<c:param name="what" value="${jobBoard.ememId }" />
										</c:url>
										<input type="button" class="btn btn-outline-secondary linkBtn" data-url="${retrieveURL }" value="기업정보" />
									</td>
									<td>
										<button class="btn btn-outline-secondary linkBtn" data-url="${cPath }/board/admin/jobBoardList.do?ememNm=${jobBoard.ememNm }">기업공고</button>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</td>
				</tr>
			</thead>
			<tbody>
			<tr>
			<td>
				<table class="table">
					<tr>
						<td colspan="2">
							<ul class="nav nav-tabs">
								<li class="nav-item"><a class="nav-link" href="#index1">상세요강</a></li>
								<li class="nav-item"><a class="nav-link" href="#index2">접수기간/방법</a></li>
								<li class="nav-item"><a class="nav-link" href="#index3">지원자 현황 통계</a></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td id="index1" colspan="2">
							${jobBoard.jobDetail }
						</td>
					</tr>
					<tr>
						<td colspan="2">기술스택&nbsp;&nbsp;&nbsp;&nbsp;${jobBoard.jobSkill }</td>
					</tr>
					<tr>
						<th id="index2" colspan="2">접수기간/방법</th>
					</tr>
					<tr>
						<td>
							<table class="table">
								<tr>
									<td colspan="4">접수기간</td>
								</tr>
								<tr>
									<td rowspan="2" class="col-2">
										모집시작일
										<br>모집마감일
									</td>
									<td rowspan="2" class="col-2">
										${jobBoard.jobStartd }
										<br>${jobBoard.jobEndd }
									</td>
									<td class="col-2">지원양식</td>
									<td class="col-6">
										${jobBoard.jobWay }
									</td>
								</tr>
								<tr>
									<td>모집분야</td>
									<td>${jobBoard.jobDept }</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</td>
				</tr>
			</tbody>
			<tfoot>
			<tr>
			<td>
				<table class="table">
					<tr>
						<th id="index3" colspan="4">지원자 현황 통계</th>
					</tr>
					<tr>               
						<td class="col-4" style="text-align: center;">실시간 지원자 수</td>
						<td class="col-4" style="text-align: center;">학력별</td>
						<td class="col-4" style="text-align: center;">경력별</td>
					</tr>
					<tr>
						<td  style="text-align: center;">${appCntVO.appCnt }명</td>
						<td>
							<div id="education">
								<c:if test="${not empty sta.msg }">
									${sta.msg }
								</c:if>
							</div>
						</td>
						<td>
							<div id="career">
								<c:if test="${not empty sta.msg }">
									${sta.msg }
								</c:if>
							</div>
						</td>
					</tr>
				</table>
			</td>
			</tr>
			</tfoot>
		</table>
		</div>
	</div>
</div>
<script>
	<c:if test="${empty sta.msg }">
		var chart1 = bb.generate({
			data: {
				columns: [
					<c:forEach items="${eduSta }" var="edu">
						["${edu.ediNm}", ${edu.ediCnt}],
					</c:forEach>
				],
				type: "pie",
			},
			bindto: "#education"
		});
		
		var chart2 = bb.generate({
			data: {
				columns: [
					<c:forEach items="${carSta }" var="car">
						["${car.yearDiv}", ${car.carCnt}],
					</c:forEach>
				],
				type: "bar",
			},
			bar: {
				width: {
					ratio: 1
				}
			},
			bindto: "#career"
		});
		
		chart1.resize({
			width: 200,
			height: 200
		});
		chart2.resize({
			width: 200,
			height: 200
		});
	</c:if>
</script>