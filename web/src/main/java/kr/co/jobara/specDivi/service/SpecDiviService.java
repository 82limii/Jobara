package kr.co.jobara.specDivi.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.specDivi.vo.SpecDiviVO;

/**
 * 작성일 2022.03.14
 * @author 김승현
 * 합격스펙(CR)용 Service
 */
public interface SpecDiviService {
	
	/**
	 * 합격스펙 입력
	 * @param specDivi
	 * @return
	 */
	public ServiceResult createSpecDivi(SpecDiviVO specDivi);
	
	/**
	 * 합격스펙 상세조회
	 * @param sdiSn
	 * @return
	 */
	public SpecDiviVO retrieveSpecDivi(int sdiSn);
	
	/**
	 * 합격스펙 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<SpecDiviVO> retrieveSpecDiviList(PagingVO<SpecDiviVO> pagingVO);
	

	
}
