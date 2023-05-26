package kr.co.jobara.qna.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.qna.dao.QnaDAO;
import kr.co.jobara.qna.vo.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Inject
	private QnaDAO qnaDAO;

	@Override
	public ServiceResult createQna(QnaVO qna) {
		int rowcnt = qnaDAO.insertQna(qna);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<QnaVO> retrieveQnaList(PagingVO<QnaVO> pagingVO) {
		int totalRecord = qnaDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<QnaVO> qnaList = qnaDAO.selectQnaList(pagingVO);
		pagingVO.setDataList(qnaList);
		return qnaList;
	}

	@Override
	public QnaVO retrieveQna(int qnaSn) {
		QnaVO qna = qnaDAO.selectQna(qnaSn);
		if(qna == null)
			throw new PKNotFoundException(qnaSn+"에 해당하는 게시글이 없음.");
		return qna;
	}

	@Override
	public ServiceResult modifyQna(QnaVO qna) {
		retrieveQna(qna.getQnaSn());
		int rowcnt = qnaDAO.updateQna(qna);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeQna(int qnaSn) {
		int rowcnt = qnaDAO.deleteQna(qnaSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
