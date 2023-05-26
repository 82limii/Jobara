package kr.co.jobara.review.certi.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.certi.dao.CertiDAO;
import kr.co.jobara.review.certi.vo.CertiVO;
@Service
public class CertiServiceImpl implements CertiService {
	
	@Inject
	private CertiDAO certiDAO;

	@Override
	public ServiceResult createCerti(CertiVO certi) {
		int rowcnt = certiDAO.insertCerti(certi);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public CertiVO retrieveCerti(int certiSn) {
		CertiVO certi = certiDAO.selectCerti(certiSn);
		if(certi == null)
			throw new PKNotFoundException(certiSn+"에 해당하는 인증이 없음.");
		return certi;
	}

	@Override
	public List<CertiVO> retrieveCertiList(PagingVO<CertiVO> pagingVO) {
		int totalRecord = certiDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<CertiVO> certiList = certiDAO.selectCertiList(pagingVO);
		pagingVO.setDataList(certiList);
		return certiList;
	}

}
