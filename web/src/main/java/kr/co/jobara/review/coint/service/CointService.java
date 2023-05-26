package kr.co.jobara.review.coint.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.coint.vo.CointVO;

/**
 * 작성일 2022.02.28
 * @author 김승현
 * 리뷰리스트(R)용 Service
 */
public interface CointService {
	
	
	/**
	 * 코딩테스트 리뷰 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<CointVO> retrieveCointList(PagingVO<CointVO> pagingVO);

}
