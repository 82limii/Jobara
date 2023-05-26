package kr.co.jobara.review.cote.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.cote.dao.CoteDAO;
import kr.co.jobara.review.cote.vo.CoteVO;

/**
 * 작성일 2022.02.25
 * @author 김승현
 * 코딩테스트(CRUD)용 ServiceImpl
 */
@Service
public class CoteServiceImpl implements CoteService {
	
	@Inject
	private CoteDAO coteDAO;


	@Override
	public ServiceResult createCote(CoteVO cote) {
		int rowcnt = coteDAO.insertCote(cote);
        return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public CoteVO retrieveCote(int coteSn) {
		CoteVO cote = coteDAO.selectCote(coteSn);
        if(cote == null)
           throw new PKNotFoundException(coteSn+"에 해당하는 인증이 없음.");
		return cote;
	}

	@Override
	public List<CoteVO> retrieveCoteList(PagingVO<CoteVO> pagingVO) {
		int totalRecord = coteDAO.selectTotalRecord(pagingVO);
	    pagingVO.setTotalRecord(totalRecord);
	    List<CoteVO> coteList = coteDAO.selectCoteList(pagingVO);
	    pagingVO.setDataList(coteList);
		return coteList;
	}


}
