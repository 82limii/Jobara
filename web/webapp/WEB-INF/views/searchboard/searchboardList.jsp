<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.nav {
	display: inline-block;
}
#menu {
	vertical-align: middle;
}
.left {
	float: left;
}
</style>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
        <table class="table table-bordered">
        	<thead>
        		<tr>
        			<th colspan="4">구직글 상세검색</th>
        		</tr>
        		<tr>
        			<td class="col-3">
        				<select>
							<option>직무</option>
							<option>선택1</option>
							<option>선택2</option>
							<option>선택3</option>
						</select>
        			</td>
        			<td class="col-3">
        				<select>
							<option>경력</option>
							<option>선택1</option>
							<option>선택2</option>
							<option>선택3</option>
						</select>
        			</td>
        			<td class="col-3">
        				<select>
							<option>고용형태</option>
							<option>선택1</option>
							<option>선택2</option>
							<option>선택3</option>
						</select>
        			</td>
        			<td class="col-3">
        				<select>
							<option>직급</option>
							<option>선택1</option>
							<option>선택2</option>
							<option>선택3</option>
						</select>
        			</td>
        		</tr>
        		<tr>
        			<td class="col-3">
        				<select>
							<option>거주지역</option>
							<option>선택1</option>
							<option>선택2</option>
							<option>선택3</option>
						</select>
        			</td>
        			<td class="col-3">
        				<select>
							<option>희망근무지</option>
							<option>선택1</option>
							<option>선택2</option>
							<option>선택3</option>
						</select>
        			</td>
        			<td colspan="2" class="col-3">
        				<label for="reStack">기술스택</label>
        				<input type="text" class="form-control" id="reStack" />
        			</td>
        		</tr>
        		<tr>
        			<td colspan="4">
        				<button class="btn btn-outline-dark right">상세검색</button>
        			</td>
        		</tr>
        	</thead>
        	<tbody>
        		<tr>
        			<td colspan="4">
        			<div class="row col-12">
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
        			</div> 
        			<div class="row col-12">
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
        			</div> 
        			<div class="row col-12">
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
        			</div> 
        			<div class="row col-12">
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
		       			<div class="card col-4">
		       				<div class="card-body">
		       					<h5 class="card-title"><a href="${cPath }/searchboard/searchBoardView.do">제목</a></h5>
		       					<h6 class="card-subtitle text-muted">이OO | 남 | 1997년</h6>
		       					<p class="card-text">
		       						학력 | 전공 | 거주지
		       					</p>
		       					<p>등록일</p>
		       					<a class="card-link" href="${cPath }/searchboard/searchBoardView.do">
		       					<button class="btn btn-outline-info">상세보기</button>
		       					</a>
		       					<button class="btn btn-outline-warning card-link">스크랩</button>
		       				</div>
		       			</div>
        			</div> 
        			</td>
        		</tr>
        	</tbody>
        	<tfoot>
        		<tr>
        			<td colspan="4">
        			<button type="button" class="btn btn-success linkBtn left" data-url="${cPath }/searchboard/searchBoardInsert.do">구직글 등록</button>
						<ul class="pagination justify-content-end center">
							<li class="page-item before">
				                <a class="page-link" href="#" tabindex="-1">
				                  <span class="ion-ios-arrow-back"></span>
				                </a>
			              	</li>
							<li class="page-item">
							  <a class="page-link" href="#">1</a>
							</li>
							<li class="page-item">
							  <a class="page-link" href="#">2</a>
							</li>
							<li class="page-item">
							  <a class="page-link" href="#">3</a>
							</li>
							<li class="page-item">
							  <a class="page-link" href="#">4</a>
							</li>
							<li class="page-item">
							  <a class="page-link" href="#">5</a>
							</li>
							<li class="page-item next">
				                <a class="page-link" href="#">
				                  <span class="ion-ios-arrow-forward"></span>
				                </a>
				            </li>
						</ul>
        			</td>
        		</tr>
        	</tfoot>
        </table>
		</div>
    </div>
</div>
