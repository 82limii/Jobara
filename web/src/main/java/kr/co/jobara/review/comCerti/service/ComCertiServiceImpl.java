package kr.co.jobara.review.comCerti.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.comCerti.dao.ComCertiDAO;
import kr.co.jobara.review.comCerti.vo.ComCertiVO;

/**
 * 작성일 2022.02.24
 * @author 김승현
 * 기업리뷰(CRUD)용 ServiceImpl
 */
@Service
public class ComCertiServiceImpl implements ComCertiService {
	
	@Inject
	private ComCertiDAO comCertiDAO;

	@Override
	public ServiceResult createComCerti(ComCertiVO comCerti) {
		int rowcnt = comCertiDAO.insertComCerti(comCerti);
        return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ComCertiVO retrieveComCerti(int coceSn) {
		ComCertiVO comCerti = comCertiDAO.selectComCerti(coceSn);
        if(comCerti == null)
           throw new PKNotFoundException(coceSn+"에 해당하는 인증이 없음.");
		return comCerti;
	}
	
	@Override
	public List<ComCertiVO> retrieveComCertiList(PagingVO<ComCertiVO> pagingVO) {
		int totalRecord = comCertiDAO.selectTotalRecord(pagingVO);
	    pagingVO.setTotalRecord(totalRecord);
	    List<ComCertiVO> comCertiList = comCertiDAO.selectComCertiList(pagingVO);
	    pagingVO.setDataList(comCertiList);
		return comCertiList;
	}


}
