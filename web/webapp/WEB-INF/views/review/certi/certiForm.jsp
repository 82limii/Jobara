<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
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
<form:form modelAttribute="certi" id="certiForm" method="post" enctype="multipart/form-data">

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
												<c:param name="what" value="${certi.ememId }" />
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
					<h5 class="heading-section">인증이 필요한 페이지입니다.</h5>
				</div>

			
				<table class="table">

					
					<tr>
						<th class="col-2">기업명</th>
						<th class="col-10">
							<form:input path="certiNm"  cssClass="form-control"  />
							<form:errors path="certiNm" element="span" cssClass="error"/>
						</th>
					</tr>
					<tr>	
						<th class="col-lg-6">재직 분류</th>
						<td>
							<select class="custom-select col-6" name="certiState">
								<option value>인증구분</option>
								<c:forEach items="${resList }" var="certi">
									<option value="${certi['commCd'] }">${certi.commNm }</option>
								</c:forEach>
							</select>
							<form:errors path="certiState" element="span" cssClass="error"/>
							
						</td>
						
					</tr>
					<tr>	
						<th class="col-lg-6">리뷰 분류</th>
						<td>
							<select class="custom-select col-6" name="certiDiviCd">
								<option value>인증구분</option>
								<c:forEach items="${revList }" var="certi">
									<option value="${certi['commCd'] }">${certi.commNm }</option>
								</c:forEach>
							</select>
							<form:errors path="certiDiviCd" element="span" cssClass="error"/>
							
						</td>
						
					</tr>
					<tr>
						<th>합격증빙자료</th>
						<th class="col-10">
							<div class="col-10" id="attDiv">
								<input type="file" path="certiFiles" name="certiFiles" class="form-control col" />
							</div>
							<form:errors path="certiFiles" element="span" cssClass="error"/>
							<button type="button" class="btn btnSize left" id="attatchBtn"></button>
						</th>
					</tr>
					
					<tr>
						<c:url value="/review/certiView.do" var="viewURL">
																<c:param name="what" value="${CertiVO.certiSn }" />
						</c:url>
						<td colspan="2">
							<button id="coteM" type="button" class="btn btn-outline-primary left linkBtn btnSize" data-url="${cPath }/review/cointReviewList.do?what=${certi.ememId }">목록</button>
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

let certiDiviCd = $("[name=certiDiviCd]").val("${certi.certiDiviCd}");
let certiState = $("[name=certiState]").val("${certi.certiState}");

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
    $("#certiForm").on("submit", function (e) {
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