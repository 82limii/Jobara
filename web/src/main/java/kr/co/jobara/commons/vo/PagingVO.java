package kr.co.jobara.commons.vo;

import java.util.List;

import kr.co.jobara.searchboard.vo.SearchVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이징 처리와 관련된 모든 프로퍼티를 가진 VO
 *
 */
@Getter
@NoArgsConstructor
public class PagingVO<T> {
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	private String pmemId;
	private int totalRecord; // 조회값
	private int screenSize=10; // 설정값
	private int blockSize=5; // 설정값
	private int totalPage; // 연산값
	private int currentPage; // 파라미터
	// 연산값===============
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	
	private SearchVO simpleSearch; // 단순 키워드 검색
	
	private T detailSearch;
	
	public void setDetailSearch(T detailSearch) {
		this.detailSearch = detailSearch;
	}
	
	public void setSimpleSearch(SearchVO simpleSearch) {
		this.simpleSearch = simpleSearch;
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		endPage = ((currentPage + (blockSize - 1)) / blockSize) * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	private static final String ATAGPATTERN = "<a data-page='%d'>%s</a>\n";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		endPage = endPage > totalPage ? totalPage : endPage;
		if(startPage>1){
			html.append(
				String.format(ATAGPATTERN, (startPage-1), "[이전]")
			);
		}
		for(int count=startPage; count<=endPage; count++){
			if(count==currentPage){
				html.append(count + "\n");
			}else{
				html.append(
					String.format(ATAGPATTERN, count, count)	
				);
			}
		}
		if(endPage < totalPage){
			html.append(
				String.format(ATAGPATTERN, (endPage+1), "[다음]")	
			);
		}
		return html.toString();
	}
	
	
	/**
	 * 페이징 HTML 구문에 BootStrap 의 Pagination UI 적용하기!
	 * @return
	 */
	public String getPagingHTMLBS() {
		StringBuffer html = new StringBuffer();
		endPage = endPage > totalPage ? totalPage : endPage;
		
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");
		String disabled = startPage > 1 ? "" : "disabled";
		html.append( String.format("<li class='page-item %s'>", disabled) );
		if(startPage>1) {
			html.append( 
				String.format(
					"<a class='page-link' data-page='%d'>Previous</a>"
					, (startPage-1)
				)
			);
			
		}else {
			html.append( "<span class='page-link'>Previous</span>");
		}
		html.append( "</li>");
		for(int count=startPage; count<=endPage; count++){
			if(count==currentPage){
				html.append("<li class='page-item active' aria-current='page'>");
			    html.append(String.format("<span class='page-link'>%d</span>", count));
			    html.append("</li>");
			}else{
				html.append(
					String.format(
					"<li class='page-item'><a class='page-link' data-page='%1$d' href='#'>%1$d</a></li>"
					, count)
				);
			}
		}
		   
		disabled = endPage < totalPage ? "" : "disabled";
		html.append( String.format("<li class='page-item %s'>", disabled));
		if(endPage < totalPage) {
			html.append(
					String.format(
					"<a class='page-link' href='#' data-page='%d'>Next</a>"
					, (endPage+1)
					)
			);
			
		}else {
			html.append( "<span class='page-link'>Next</span>");
		}
		html.append("</li>");
		html.append("</ul>");
		html.append("</nav>");
		return html.toString();
	}

	public void setPmemId(String pmemId) {
		this.pmemId = pmemId;
	}

	
}