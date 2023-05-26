package kr.co.jobara.scrap.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.scrap.vo.ScrapPostVO;

/**
 * 작성일 2022.03.03
 * @author 김승현
 * 공고스크랩(CRD)용 Service
 */
public interface ScrapPostService {
	
	/**
	 * 공고스크랩 입력
	 * @param 
	 * @return
	 */
	public ServiceResult createScrapPost(ScrapPostVO scrapPost);
	
	/**
	 * 공고스크랩 상세조회
	 * @param sinSn
	 * @return
	 */
//	public ScrapPostVO retrieveScrapPost(int scrapSn);
	
	/**
	 * 공고스크랩 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ScrapPostVO> retrieveScrapPostList(PagingVO<ScrapPostVO> pagingVO);
	public List<ScrapPostVO> retrieveScrapCompanyList(PagingVO<ScrapPostVO> pagingVO);
	
	/**
	 * 공고스크랩 삭제
	 * @param scrapSn
	 * @return 
	 */
	public ServiceResult removeScrapPost(int scrapSn);
	
}
