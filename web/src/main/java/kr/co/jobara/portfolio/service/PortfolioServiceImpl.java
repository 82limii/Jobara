package kr.co.jobara.portfolio.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.portfolio.dao.PortfolioDAO;
import kr.co.jobara.portfolio.vo.PortfolioVO;

/**
 * 작성일 2022.03.11
 * @author 김승현
 * 포트폴리오(CRD)용 ServiceImpl
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {
	
	@Inject
	private PortfolioDAO portfolioDAO;



	@Override
	public ServiceResult createPortfolio(PortfolioVO portfolio) {
		int rowcnt = portfolioDAO.insertPortfolio(portfolio);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<PortfolioVO> retrievePortfolioList(PagingVO<PortfolioVO> pagingVO) {
		List<PortfolioVO> portfolio = portfolioDAO.selectPortfolioList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = portfolioDAO.selectTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(portfolio);
		}
		return portfolio;
	}

	@Override
	public ServiceResult removePortfolio(int portSn) {
		int rowcnt = portfolioDAO.deletePortfolio(portSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	

}
