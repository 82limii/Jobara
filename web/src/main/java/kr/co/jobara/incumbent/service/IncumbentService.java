package kr.co.jobara.incumbent.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.incumbent.vo.IncumbentVO;

public interface IncumbentService {
	
	/**
	 * 재직자 상세조회
	 * @param empSn
	 * @return
	 */
	public IncumbentVO retrieveIncumbent(int empSn);
	
	/**
	 * 재직자 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<IncumbentVO> retrieveIncumbentList(PagingVO<IncumbentVO> pagingVO);
	
	/**
	 * 재직자 정보수정
	 * @param incumbent
	 * @return
	 */
	public ServiceResult modifyIncumbent(IncumbentVO incumbent);
	
	/**
	 * 재직자 삭제
	 * @param empSn
	 * @return
	 */
	public ServiceResult removeIncumbent(int empSn);
	
}
