<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#delBtn, #ban, #listBtn {
	float: right;
}
#reBtn {
	width: 300pt;
}
</style>
<div class="container">
	        <div class="col-lg-12 grid-margin stretch-card">
             <div class="card-body" style="background-color: #fff">
			  <table class="table table-bordered">
			  	<tbody>
			  		<tr>
				  		<th colspan="3">글제목</th>
			  		</tr>
			  		<tr>
				  		<td>최인수</td>
				  		<td>2022.02.14</td>
				  		<td>
		  					<button class="btn btn-outline-danger">
								 <i class="fas fa-bell"></i>
									신고
							</button>
				  		</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3">
			  				안녕하세요.. 개발자가 되기위해 준비중입니다.
			  			</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3">
			  				<input id="delBtn" type="button" class="btn btn-outline-success" value="삭제" />
			  			</td>
			  		</tr>
			  		<tr>
			  			<td>첨부파일</td>
			  			<td colspan="2">1.jpg</td>
			  		</tr>
			  	</tbody>
			  	<tfoot>
			  	<tr>
			  		<td colspan="2">
						<input id="reBtn" type="text" placeholder="욕하지마라"/>
						<input type="button" class="btn btn-outline-dark" value="댓글등록"/><br>
			  		</td>
			  	</tr>
			  	</tfoot>
			  </table>
				<input type="button" id="listBtn" class="btn btn-outline-success linkBtn" value="목록으로"
					data-url="${cPath }/board/admin/itBoardList.do"
				/>
			</div>
	</div>
</div>
