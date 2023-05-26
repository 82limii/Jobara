package kr.co.jobara.board.shareit.service;

import java.util.List;

import kr.co.jobara.board.shareit.vo.ShareItVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

/**
 * @author 최인수
 * Share It 게시판 로직
 */
public interface ShareItService {
	
	public ServiceResult createShareItBoard(ShareItVO shareItBoard);
	public ServiceResult modifyShareItBoard(ShareItVO shareItBoard);
	public ServiceResult removeShareItBoard(ShareItVO shareItBoard);
	public List<ShareItVO> retrieveShareItBoardList(PagingVO<ShareItVO> pagingVO);
	public ShareItVO retrieveShareItBoard(int reBoardSn);
	
}
