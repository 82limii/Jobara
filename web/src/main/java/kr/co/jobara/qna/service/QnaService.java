package kr.co.jobara.qna.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.qna.vo.QnaVO;

public interface QnaService {

	public ServiceResult createQna(QnaVO qna);
	
	public List<QnaVO> retrieveQnaList(PagingVO<QnaVO> pagingVO);
	
	public QnaVO retrieveQna(int qnaSn);
	
	public ServiceResult modifyQna(QnaVO qna);
	
	public ServiceResult removeQna(int qnaSn);
	
}
