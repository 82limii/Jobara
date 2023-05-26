<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="row">
		<div class="col-lg-3">
			<div class="hero__categories">
				<div class="hero__categories__all">
					<i class="fa fa-bars"></i> <span>마이페이지</span>
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
		<div class="card-body">
			<nav>
				<div class="nav nav-tabs" id="nav-tab" role="tablist">
					<a class="nav-link active" id="nav-home-tab" data-toggle="tab" href="#jobApply" role="tab" aria-controls="nav-home" aria-selected="true">
						입사지원 현황
					</a>
					 <a class="nav-link" id="nav-profile-tab" data-toggle="tab" href="#proApply" role="tab" aria-controls="nav-profile" aria-selected="false">
					 	프로젝트 지원 현황
					 </a>
				</div>
			</nav>
			<div class="tab-content" id="nav-tabContent">
				<div class="tab-pane fade show active" id="jobApply" role="tabpanel" aria-labelledby="nav-home-tab">
					<table class="table table-bordered col-12">
						<thead>
							<tr>
								<th class="col-1">순번</th>
								<th class="col-5">채용 공고명</th>
								<th class="col-4">지원이력서</th>
								<th class="col-2">지원날짜</th>
							</tr>
						</thead>
						<tbody  id="listBody">
							<c:set var="jobList" value="${pagingVO2.dataList }" />
							<c:choose>
								<c:when test="${empty jobList }">
									<tr>
										<td colspan="7">지원한 입사지원이 없음.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${jobList }" var="jobList">
										<c:url value="/jobboard/jobBoardView.do" var="viewURL">
											<c:param name="what" value="${jobList.jobSn }" />
										</c:url>
										<c:url value="/myPage/resumeView.do" var="viewRURL">
											<c:param name="what" value="${jobList.reSn }" />
										</c:url>
										<tr data-toggle="collapse" data-target="#collapseOne"
											aria-expanded="false" aria-controls="collapseOne">
											<th scope="row">${jobList.appSn}</th>
											<td><a href="${viewURL }">${jobList.jobTitle}</a></td>
											<td>
												<a href="${viewRURL }">
													${jobList.reTitle}
												</a>
											</td>
											<td>${jobList.appDate}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>	
					</table>
				</div>
				<div class="tab-pane fade" id="proApply" role="tabpanel" aria-labelledby="nav-profile-tab">
					<table class="table table-bordered col-12">
						<thead>
							<tr>
								<th class="col-1">순번</th>
								<th class="col-5">프로젝트 공고명</th>
								<th class="col-4">지원이력서</th>
								<th class="col-2">지원날짜</th>
							</tr>
						</thead>
						<tbody  id="listBody">
							<c:set var="proList" value="${pagingVO1.dataList }" />
							<c:choose>
								<c:when test="${empty proList }">
									<tr>
										<td colspan="7">지원한 프로젝트가 없음.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${proList }" var="proList">
										<c:url value="/proboard/proBoardView.do" var="viewURL">
											<c:param name="what" value="${proList.probSn }" />
										</c:url>
										<c:url value="/myPage/resumeView.do" var="viewRURL">
											<c:param name="what" value="${proList.reSn }" />
										</c:url>
										<tr data-toggle="collapse" data-target="#collapseOne"
											aria-expanded="false" aria-controls="collapseOne">
											<th scope="row">${proList.probSn}</th>
											<td><a href="${viewURL }">${proList.probTitle}</a></td>
											<td>
												<a href="${viewRURL }">
													${proList.reTitle}
												</a>
											</td>
											<td>${proList.appDate}</td>
										</tr>
									</c:forEach>
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