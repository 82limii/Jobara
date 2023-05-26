package kr.co.jobara.review.coint.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.review.coint.dao.CointDAO;
import kr.co.jobara.review.coint.vo.CointVO;

/**
 * 작성일 2022.02.28
 * @author 김승현
 * 리뷰리스트(R)용 ServiceImpl
 */
@Service
public class CointServiceImpl implements CointService {
	
	@Inject
	private CointDAO cointDAO;


	@Override
	public List<CointVO> retrieveCointList(PagingVO<CointVO> pagingVO) {
		List<CointVO> cointList = cointDAO.selectCointList(pagingVO);
	    if(pagingVO!=null) {
	    	int totalRecord = cointDAO.selectTotalRecord(pagingVO);
		    pagingVO.setTotalRecord(totalRecord);
		    pagingVO.setDataList(cointList);
		}
		return cointList;
	}


}
