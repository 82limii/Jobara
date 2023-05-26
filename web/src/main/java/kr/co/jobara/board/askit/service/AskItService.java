package kr.co.jobara.board.askit.service;

import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

public interface AskItService {

	public ServiceResult createAskItBoard(AskItVO askItBoard);
	public ServiceResult modifyAskItBoard(AskItVO askItBoard);
	public ServiceResult removeAskItBoard(int boardSn);
	public AskItVO retrieveAskItBoard(int boardSn);
	public void retrieveAskItBoardList(PagingVO<AskItVO> paging);

}
