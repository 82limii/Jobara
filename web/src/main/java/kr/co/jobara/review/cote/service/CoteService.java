package kr.co.jobara.review.cote.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.cote.vo.CoteVO;

/**
 * 작성일 2022.02.28
 * @author 김승현
 * 코딩테스트(CRUD)용 Service
 */
public interface CoteService {
	/**
	 * 코딩테스트 리뷰 작성
	 * @param cote
	 * @return
	 */
	public ServiceResult createCote(CoteVO cote);
	
	/**
	 * 코딩테스트 리뷰 상세조회
	 * @param coteSn
	 * @return
	 */
	public CoteVO retrieveCote(int coteSn);
	/**
	 * 코딩테스트 리뷰 목록조회
	 * @param paging
	 * @return
	 */
	public List<CoteVO> retrieveCoteList(PagingVO<CoteVO> paging);

}
