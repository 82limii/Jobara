<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<div class="col-lg-11">
		<table class="col-md-12 table">
			<thead class="thead-dark">
				<tr>
					<th colspan="3">기업회원 블랙리스트 등록</th>
				</tr>
			</thead>
				<tr>
					<th>기업회원 ID</th>
					<td>
						<input path="eMemId" style="width:600px;" required="required" /> 
					</td>
				</tr>
				<tr>
					<th>기업명</th>
					<td>
						<input path="eMemName" style="width:600px;" required="required" /> 
					</td>
				</tr>
				<tr>
					<th>신고내용</th>
					<td>
						<textarea rows="15" cols="80" path="helpsContents" ></textarea> 
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="button" value="신고내역 불러오기" class="btn btn-outline-success" /> 
					<input type="submit" value="저장" class="btn btn-success" /> 
					<input type="reset" value="취소" class="btn btn-danger" /> 
					</td>
				</tr>
		</table>
	</div>
</div>