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
		<table class="table">
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
									<c:choose>
										<c:when test="${jobBoard.jobCity eq jobBoard.jobLoc}">
											<td>${jobBoard.jobLoc }</td>
										</c:when>
										<c:otherwise>
											<td>${jobBoard.jobCity } ${jobBoard.jobLoc }</td>
										</c:otherwise>
									</c:choose>
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
									<td class="col-7" colspan="2">${jobBoard.comBussiness }</td>
								</tr>
								<tr>
									<td>직원수</td>
									<td colspan="2">${jobBoard.comHeadcnt }</td>
								</tr>
								<tr>
									<td>
										<c:url value="${cPath }/info/info.do" var="retrieveURL">
											<c:param name="what" value="${jobBoard.ememId }" />
										</c:url>
										<input type="button" class="btn btn-outline-secondary linkBtn" data-url="${retrieveURL }" value="기업정보" />
									</td>
									<td><button class="btn btn-outline-secondary linkBtn" data-url="${cPath }/jobboard/myJobBoard.do?what=${jobBoard.ememId}">기업공고</button>
									</td>
									<td>
										<button class="btn btn-outline-danger">
										 <i class="fa fa-bell"></i>
											신고
										</button>
									</td>
								</tr>
								<!-- 기업회원 가능 -->
								<tr>
									<td colspan="3">
										<c:choose>
											<c:when test="${authMember.userType eq 'Emember'}">
												<c:if test="${authMember.ememId eq jobBoard.ememId }">
													<button class="btn btn-primary linkBtn" data-url="${cPath }/jobboard/com/jobBoardUpdate.do?what=${jobBoard.jobSn}">수정</button>
												</c:if>
												<button class="linkBtn btn btn-outline-success" data-url="${cPath }/jobboard/com/jobBoardList.do">목록</button>
											</c:when>
											<c:otherwise>
												<c:url value="/scrap/ScrapInsert.do" var="viewSURL">
													<c:param name="what" value="${jobBoard.jobSn }" />
												</c:url>
												<button class="linkBtn btn btn-outline-warning" data-url="${viewSURL }">스크랩</button>
												<button class="linkBtn btn btn-outline-success" data-url="${cPath }/jobboard/jobBoardList.do">목록</button>
											</c:otherwise>
										</c:choose>
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
							등록일  ${jobBoard.jobDate }
							<hr>
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
								<c:set value="${jobBoard.jobWay }" var="way" />
								<tr>
									<td colspan="2">접수기간</td>
									<td colspan="2">
										<c:if test="${authMember.userType ne 'Emember' }">
											<c:if test="${fn:contains(way, '잡아라') }">
												<button class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" >즉시지원</button>
											</c:if>
											<c:if test="${fn:contains(way, '홈페이지') }">
												<button class="btn btn-outline-primary linkBtn" onclick="location.href='${jobBoard.jobPage}'">홈페이지 지원</button>
											</c:if>
											<c:if test="${fn:contains(way, '이메일') }">
												<button class="btn btn-outline-primary" disabled="disabled">이메일 지원</button>
											</c:if>
										</c:if>
									</td>
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
										<c:if test="${fn:contains(way, '잡아라') }">
											잡아라 지원양식&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
										<c:if test="${not empty attatchList}">
											<c:forEach items="${attatchList }" var="attatch">
												<a href="${cPath }/download.do?what=${attatch.attSn}&num=${attatch.attNum}">
													<i class="fa fa-paperclip" aria-hidden="true"></i>&nbsp;${attatch.attNm }
												</a>&nbsp;&nbsp;&nbsp;&nbsp;
											</c:forEach>
										</c:if>
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
						<td style="text-align: center;">${appCntVO.appCnt }명</td>
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
<c:if test="${authMember.userType eq 'Pmember' }">
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">이력서 목록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${cPath }/apply/jobApply.do" method="post" id="applyForm">
      <div class="modal-body">
        <table class="table">
        	<tr>
        		<th class="col-1"></th>
        		<th class="col-2">순번</th>
        		<th class="col-6" style="text-align:left;">이력서 제목</th>
        		<th class="col-3">등록일</th>
        	</tr>
        	<c:set value="${paging.dataList }" var="resumeList" />
        	<c:forEach items="${resumeList}" var="resume">
	        	<tr>
	        		<td><input type="radio" name="reSn" value="${resume.reSn }" /></td>
	        		<td>${resume.rnum }</td>
	        		<td style="text-align:left;">${resume.reTitle }</td>
	        		<td>${resume.reDate }</td>
	        	</tr>
        	</c:forEach>
        </table>
      </div>
      <div class="modal-footer">
<!-- 	        <input type="text" name="reSn" /> -->
	        <input type="text" name="jobSn" value="${jobBoard.jobSn }" hidden="true"/>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-primary"  data-dismiss="modal" id="modalBtn">선택</button>
      </div>
      </form>
    </div>
  </div>
</div>
</c:if>


<script>

	// 입사지원
	let modalBtn = $("#modalBtn").on("click", function() {
// 		let reSn = $(".reSn").val();
// 		$("[name=reSn]").val(reSn);
		
		$("#applyForm").submit();
		
		alert("지원에 성공하셨습니다.");
	});

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