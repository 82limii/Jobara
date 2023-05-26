package kr.co.jobara.review.inter.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.inter.vo.InterVO;

/**
 * 작성일 2022.02.28
 * @author 김승현
 * 면접리뷰(CR)용  Service
 */
public interface InterService {
	/**
	 * 면접리뷰 인증 작성
	 * @param inter
	 * @return
	 */
	public ServiceResult createInter(InterVO inter);
	
	/**
	 * 면접리뷰 인증 상세조회
	 * @param interSn
	 * @return
	 */
	public InterVO retrieveInter(int interSn);
	/**
	 * 면접리뷰 인증 목록조회
	 * @param paging
	 * @return
	 */
	public List<InterVO> retrieveInterList(PagingVO<InterVO> paging);

}
