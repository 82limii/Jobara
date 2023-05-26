package kr.co.jobara.scrap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.scrap.vo.ScrapPostVO;

/**
 * 작성일 2022.03.03
 * @author 김승현
 * 공고스크랩(CRD)용 dao
 */
@Mapper
public interface ScrapPostDAO {
	
	/**
	 * 공고스크랩 등록
	 * @param jobSn
	 * @return
	 */
	public int insertScrapPost(ScrapPostVO scrapPost);
	
	/**
	 * 조건에 맞는 공고스크랩 갯수
	 * @param ScrapPostVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ScrapPostVO> pagingVO);
	
	/**
	 * 공고스크랩 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ScrapPostVO> selectScrapPostList(PagingVO<ScrapPostVO> pagingVO);
	public List<ScrapPostVO> selectScrapCompanyList(PagingVO<ScrapPostVO> pagingVO);

	/**
	 * 공고스크랩 상세조회
	 * @param scrapSn
	 * @return
	 */
	public ScrapPostVO selectScrapPost(int scrapSn);
	
	/**
	 * 공고스크랩 삭제
	 * @param scrapSn
	 * @return  > 0 : 성공
	 */
	public int deleteScrapPost(int scrapSn);
	
}
