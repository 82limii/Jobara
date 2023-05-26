package kr.co.jobara.specDivi.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.specDivi.dao.SpecDiviDAO;
import kr.co.jobara.specDivi.vo.SpecDiviVO;
/**
 * 작성일 2022.03.14
 * @author 김승현
 * 합격스펙(CR)용 ServiceImpl
 */
@Service
public class SpecDiviServiceImpl implements SpecDiviService {
	
	@Inject
	private SpecDiviDAO specDiviDAO;

	

	@Override
	public ServiceResult createSpecDivi(SpecDiviVO specDivi) {
		int rowcnt = specDiviDAO.insertSpecDivi(specDivi);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public SpecDiviVO retrieveSpecDivi(int sdiSn) {
		SpecDiviVO specDivi = specDiviDAO.selectSpecDivi(sdiSn);
		if(specDivi == null)
			throw new PKNotFoundException(sdiSn+"에 해당하는 게시글이 없음.");
		return specDivi;
	}

	@Override
	public List<SpecDiviVO> retrieveSpecDiviList(PagingVO<SpecDiviVO> pagingVO) {
		int totalRecord = specDiviDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<SpecDiviVO> specDiviList = specDiviDAO.selectSpecDiviList(pagingVO);
		pagingVO.setDataList(specDiviList);
		return specDiviList;
	}

}
