<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
#coteM {
	width: 80px;
	height: 45px;
	float: left;
}

#coteS {
	float: right;
	width: 80px;
	height: 45px;
	margin: 5px;
	margin-top: 0;
}

#coteR {
	float: right;
	width: 80px;
	height: 45px;
	margin: 5px;
	margin-top: 0;
}

#spec {
	height: 100px;
}

#specC {
}
</style>

<form:form modelAttribute="comCerti" method="post" enctype="multipart/form-data">
<div class="container">
	<div class="row">
		<div class="col-lg-3">
			<div class="hero__categories">
				<div class="hero__categories__all">
					<i class="fa fa-bars"></i> <span>기업페이지</span>
				</div>
				<ul>
					<c:forEach items="${menuList }" var="menu">
						<c:choose>
							<c:when test="${not empty menu.menuUpper }">
								<br>
								<h5>${menu.menuUpper }</h5>
							</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty menu.menuFlag }">
										<c:if test="${menu.menuFlag eq 'ememId' }">
											<c:url value="${menu.menuUrl }" var="viewURL">
												<c:param name="what" value="${comCerti.ememId }" />
											</c:url>
											<li><a href="${cPath }/${viewURL }">${menu.menuName }</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<li><a href="${cPath }/${menu.menuUrl }">${menu.menuName }</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="col-lg-9">
			<div class="card-body">
				<div>
					<h4 class="heading-section">인증이 필요한 페이지입니다.</h4>
				</div>


				<table class="table">
					<tr>
						<th class="col-2">재직상티</th>
						<td>
							<select class="custom-select col-8" name="coceState">
								<option value>재직상태</option>
								<option value="현직원">현직원</option>
								<option value="전직원">전직원</option>
							</select>
							<form:errors path="coceState" element="span" cssClass="error"/>
						</td>
					</tr> 
					<tr>
						<th class="col-2">직무</th>
						<td>
							<select class="custom-select col-8" name="coceDeptCd">
								<option value>직무구분</option>
								<c:forEach items="${deptList }" var="comCerti">
									<option value="${comCerti['commCd'] }">${comCerti.commNm }</option>
								</c:forEach>
							</select>
							<form:errors path="coceDeptCd" element="span" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<th class="col-2">고용형태</th>
						<td>
							<select class="custom-select col-8" name="coceEmptype">
								<option value>고용구분</option>
								<c:forEach items="${empList }" var="comCerti">
									<option value="${comCerti['commCd'] }">${comCerti.commNm }</option>
								</c:forEach>
							</select>
							<form:errors path="coceEmptype" element="span" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<th class="col-2">경력</th>
						<td>
							<select class="custom-select col-8" name="coceCareer">
								<option value>경력구분</option>
								<c:forEach items="${yrList }" var="comCerti">
									<option value="${comCerti['commCd'] }">${comCerti.commNm }</option>
								</c:forEach>
							</select>
							<form:errors path="coceCareer" element="span" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<th class="col-2">근무지역</th>
						<td>
							<select class="custom-select col-8" name="coceArea">
								<option>지역구분</option>
								<c:forEach items="${cityList }" var="comCerti">
									<option value="${comCerti['locCd'] }">${comCerti.locCityNm }</option>
								</c:forEach>
							</select>
							<form:errors path="coceArea" element="span" cssClass="error"/>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<button id="coteM" type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/review/reviewList.do?what=${comCerti.ememId }">목록</button>
							<button id="coteR" type="reset" class="btn btn-danger btnSize">취소</button>
							<button id="coteS"  type="submit" class="btn btn-success btnSize">저장</button>
						</td>
					</tr>
				</table>

			</div>
		</div>
	</div>
</div>
</form:form>
<script type="text/javascript">
	
let attatchBtn = $("#attatchBtn").on("click", function() {
	let inputTag = $("<input>",{type:"file", name:"certiFiles"}).addClass("form-control col-4");
	$("#attDiv").append(inputTag);
});

let coceState = $("[name=coceState]").val("${certi.coceState}");
let coceDeptCd = $("[name=coceDeptCd]").val("${certi.coceDeptCd}");
let coceEmptype = $("[name=coceEmptype]").val("${certi.coceEmptype}");
let coceCareer = $("[name=coceCareer]").val("${certi.coceCareer}");
let coceArea = $("[name=coceArea]").val("${certi.coceArea}");

$(document).ready(function () {
    var tag = {};	// 기술스택
    var counter = 0;	// 기술스택

    // 입력한 값을 태그로 생성한다. 기술스택
    function addTag (value) {
        tag[counter] = value;
        counter++; // del-btn 의 고유 id 가 된다.
    }
    
    // tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
    function marginTag () {
        return Object.values(tag).filter(function (word) {
            return word !== "";
        });
    }
    
    // 서버에 제공
    $("#companyCertiForm").on("submit", function (e) {
        var value = marginTag(); // return array
        $("#hiddenSkill").val(value); 
        

        $(this).submit();
    });
    
    stack.on("change", function(e) {
    	var self = $(this);
    	var tagValue = $("[value="+self.val()+"]").text();	// value 값 가져오기
    	
    	
    	// 해시태그 값 없으면 실행 X
    	if (tagValue != "") {
    		// 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
            var result = Object.values(tag).filter(function (word) {
                return word == tagValue;
            });
         // 해시태그가 중복되었는지 확인
            if (result.length == 0) { 
                $("#tag-list").append("<li class='tag-item'>"+tagValue+"<span class='del-btn' idx='"+counter+"'> <i class='fa fa-times-circle'></i> </span></li>");
                addTag(tagValue);
                self.val("");
            } else {
                alert("태그값이 중복됩니다.");
            }
    	}
    });
    
    // 삭제 버튼 
    // 인덱스 검사 후 삭제
    $(document).on("click", ".del-btn", function (e) {
        var index = $(this).attr("idx");
        if($(this).parent().hasClass("tag-item")) {
            tag[index] = "";
        }
        $(this).parent().remove();
    });
});
</script>