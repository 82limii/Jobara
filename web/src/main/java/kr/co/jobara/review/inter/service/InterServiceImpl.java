package kr.co.jobara.review.inter.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.inter.dao.InterDAO;
import kr.co.jobara.review.inter.vo.InterVO;

/**
 * 작성일 2022.02.28
 * @author 김승현
 * 면접 리뷰(CRUD)용 ServiceImpl
 */
@Service
public class InterServiceImpl implements InterService {

	@Inject
	private InterDAO interDAO;
	
	@Override
	public ServiceResult createInter(InterVO inter) {
		int rowcnt = interDAO.insertInter(inter);
        return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public InterVO retrieveInter(int interSn) {
		InterVO inter = interDAO.selectInter(interSn);
        if(inter == null)
           throw new PKNotFoundException(interSn+"에 해당하는 인증이 없음.");
		return inter;
	}

	@Override
	public List<InterVO> retrieveInterList(PagingVO<InterVO> pagingVO) {
		int totalRecord = interDAO.selectTotalRecord(pagingVO);
	    pagingVO.setTotalRecord(totalRecord);
	    List<InterVO> interList = interDAO.selectInterList(pagingVO);
	    pagingVO.setDataList(interList);
		return interList;
	}
	
	


}
