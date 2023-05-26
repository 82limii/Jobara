package kr.co.jobara.successint.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.successint.vo.SuccessIntVO;

/**
 * 작성일 2022.03.02
 * @author 김승현
 * 합격자소서(CRU)용 Service
 */
public interface SuccessIntService {
	
	/**
	 * 합격자소서 입력
	 * @param successInt
	 * @return
	 */
	public ServiceResult createSuccessInt(SuccessIntVO successInt);
	
	/**
	 * 합격자소서 상세조회
	 * @param sinSn
	 * @return
	 */
	public SuccessIntVO retrieveSuccessInt(int sinSn);
	
	/**
	 * 합격자소서 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<SuccessIntVO> retrieveSuccessIntList(PagingVO<SuccessIntVO> pagingVO);
	
	/**
	 * 합격자소서 수정
	 * @param successInt
	 * @return
	 */
	public ServiceResult removeSuccessInt(int sinSn);
	
}
