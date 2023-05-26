package kr.co.jobara.portfolio.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.portfolio.vo.PortfolioVO;

/**
 * 작성일 2022.03.11
 * @author 김승현
 * 포트폴리오(CRD)용 dao
 */
@Mapper
public interface PortfolioDAO {
	
	/**
	 * 포트폴리오 등록
	 * @param portfolio
	 * @return
	 */
	public int insertPortfolio(PortfolioVO portfolio);
	
	/**
	 * 조건에 맞는 포트폴리오 갯수
	 * @param portSn
	 * @return
	 */
	public int selectTotalRecord(PagingVO<PortfolioVO> pagingVO);
	
	/**
	 * 포트폴리오 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<PortfolioVO> selectPortfolioList(PagingVO<PortfolioVO> pagingVO);
	

	
	/**
	 * 포트폴리오 삭제
	 * @param portSn
	 * @return
	 */
	public int deletePortfolio(int portSn);
	
}
