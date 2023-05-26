package kr.co.jobara.board.shareit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.board.shareit.dao.ShareItDAO;
import kr.co.jobara.board.shareit.vo.ShareItVO;
import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

@Service
public class ShareItServiceImpl implements ShareItService {

	@Inject
	private ShareItDAO shareItDAO;
	
	@Override
	public ServiceResult createShareItBoard(ShareItVO shareItBoard) {
		int rownum = shareItDAO.insertShareItBoard(shareItBoard);
		return rownum > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyShareItBoard(ShareItVO shareItBoard) {
		int rownum = shareItDAO.updateShareItBoard(shareItBoard);
		return rownum > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeShareItBoard(ShareItVO shareItBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShareItVO> retrieveShareItBoardList(PagingVO<ShareItVO> pagingVO) {
		List<ShareItVO> shareItBoardList = shareItDAO.selectShareItBoardList(pagingVO);
		if(pagingVO != null) {
			int totalRecord = shareItDAO.selectTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(shareItBoardList);
		}
		
		return shareItBoardList;
	}

	@Override
	public ShareItVO retrieveShareItBoard(int reBoardSn) {
		ShareItVO shareItBoard = shareItDAO.selectShareItBoard(reBoardSn);
		if(shareItBoard == null) {
			throw new PKNotFoundException(reBoardSn + "에 해당하는글 없음");
		}
		return shareItBoard;
	}

}
