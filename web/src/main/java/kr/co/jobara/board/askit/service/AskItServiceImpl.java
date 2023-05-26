package kr.co.jobara.board.askit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.board.askit.dao.AskItDAO;
import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

@Service
public class AskItServiceImpl implements AskItService {

	@Inject
	private AskItDAO askItDAO;
	
	@Override
	public ServiceResult createAskItBoard(AskItVO askItBoard) {
		int rownum = askItDAO.insertAskItBoard(askItBoard);
		return rownum > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyAskItBoard(AskItVO askItBoard) {
		int rowcnt = askItDAO.updateAskItBoard(askItBoard);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeAskItBoard(int boardSn) {
		int rowcnt = askItDAO.deleteAskItBoard(boardSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public AskItVO retrieveAskItBoard(int boardSn) {
		AskItVO askItBoard = askItDAO.selectAskItBoard(boardSn);
		if(askItBoard == null) {
			throw new PKNotFoundException(boardSn + "에 해당하는 글 없음");
		}
		return askItBoard;
	}

	@Override
	public void retrieveAskItBoardList(PagingVO<AskItVO> paging) {
		int totalRecord = askItDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<AskItVO> askItBoardList = askItDAO.selectAskItBoardList(paging);
		paging.setDataList(askItBoardList);
	}

}
