package kr.co.jobara.successint.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.successint.dao.SuccessIntDAO;
import kr.co.jobara.successint.vo.SuccessIntVO;

/**
 * 작성일 2022.03.02
 * @author 김승현
 * 합격자소서(CRU)용 ServiceImpl
 */
@Service
public class SuccessIntServiceImpl implements SuccessIntService {
	
	@Inject
	private SuccessIntDAO successIntDAO;

	@Override
	public ServiceResult createSuccessInt(SuccessIntVO successInt) {
		int rowcnt = successIntDAO.insertSuccessInt(successInt);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public SuccessIntVO retrieveSuccessInt(int sinSn) {
		SuccessIntVO successInt = successIntDAO.selectSuccessInt(sinSn);
		if(successInt == null)
			throw new PKNotFoundException(sinSn+"에 해당하는 게시글이 없음.");
		return successInt;
	}

	@Override
	public List<SuccessIntVO> retrieveSuccessIntList(PagingVO<SuccessIntVO> pagingVO) {
		List<SuccessIntVO> successIntList = successIntDAO.selectSuccessIntList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = successIntDAO.selectTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(successIntList);
		}
		return successIntList;
	}

	@Override
	public ServiceResult removeSuccessInt(int sinSn) {
		SuccessIntVO successInt = retrieveSuccessInt(sinSn);
		if(successInt == null)
			throw new PKNotFoundException(sinSn+"에 해당하는 게시글이 없음.");
		int rowcnt = successIntDAO.deleteSuccessInt(sinSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
