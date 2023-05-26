package kr.co.jobara.proboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.proboard.dao.ProBoardDAO;
import kr.co.jobara.proboard.vo.ProBoardVO;

/**
 * @author 이상림
 * 최초작성 2022.03.04
 *
 */
@Service
public class ProBoardServiceImpl implements ProBoardService {
	
	@Inject
	private ProBoardDAO proBoardDao;

	@Override
	public List<ProBoardVO> retrieveProBoardList(PagingVO<ProBoardVO> pagingVO) {
		List<ProBoardVO> proBoardList = proBoardDao.selectProBoardList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = proBoardDao.selectTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(proBoardList);
		}
		return proBoardList;
	}

	@Override
	public ProBoardVO retrieveProBoard(int probSn) {
		ProBoardVO proBoard = proBoardDao.selectProBoard(probSn);
		if(proBoard == null) {
			throw new PKNotFoundException(probSn + "에 해당하는 공고가 없음.");
		}
		return proBoard;
	}

	@Override
	public List<ProBoardVO> retrieveMyProBoard(PagingVO<ProBoardVO> pagingVO) {
		List<ProBoardVO> proBoardList = proBoardDao.selectMyProBoard(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = proBoardDao.selectMyTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(proBoardList);
		}
		return proBoardList;
	}

	@Transactional
	@Override
	public ServiceResult createProBoard(ProBoardVO proBoard) {
		ServiceResult result = null;
		int rowcnt = proBoardDao.insertProBoard(proBoard);
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

	@Transactional
	@Override
	public ServiceResult modifyProBoard(ProBoardVO proBoard) {
		ServiceResult result = null;
		retrieveProBoard(proBoard.getProbSn());
		int rowcnt = proBoardDao.updateProBoard(proBoard);
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

}
