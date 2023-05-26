package kr.co.jobara.portfolio.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.portfolio.vo.PortfolioVO;

/**
 * 작성일 2022.03.11
 * @author 김승현
 * 포트폴리오(CRD)용 Service
 */
public interface PortfolioService {
	
	/**
	 * 포트폴리오 입력
	 * @param portfolio
	 * @return
	 */
	public ServiceResult createPortfolio(PortfolioVO portfolio);
	
	
	
	/**
	 * 포트폴리오 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<PortfolioVO> retrievePortfolioList(PagingVO<PortfolioVO> pagingVO);
	
	/**
	 * 포트폴리오 삭제
	 * @param portfolio
	 * @return
	 */
	public ServiceResult removePortfolio(int portSn);
	
}
