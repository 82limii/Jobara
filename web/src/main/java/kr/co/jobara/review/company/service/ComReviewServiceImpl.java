package kr.co.jobara.review.company.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.review.company.dao.ComReviewDAO;
import kr.co.jobara.review.company.vo.ComReviewVO;

/**
 * 작성일 2022.02.23
 * @author 김승현
 * 기업리뷰(CRUD)용 ServiceImpl
 */
@Service
public class ComReviewServiceImpl implements ComReviewService {
	
	@Inject
	private ComReviewDAO comReviewDAO;

	@Override
	public ServiceResult createComReview(ComReviewVO comReview) {
		 int rowcnt = comReviewDAO.insertComReview(comReview);
         return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ComReviewVO retrieveComReview(int reviewSn) {
		ComReviewVO comReview = comReviewDAO.selectComReview(reviewSn);
        if(comReview == null)
           throw new PKNotFoundException(reviewSn+"에 해당하는 리뷰가 없음.");
		return comReview;
	}

	@Override
	public List<ComReviewVO> retrieveComReviewList(PagingVO<ComReviewVO> pagingVO) {
	    List<ComReviewVO> comReviewList = comReviewDAO.selectComReviewList(pagingVO);
	    if(pagingVO!=null) {
	    	int totalRecord = comReviewDAO.selectTotalRecord(pagingVO);
		    pagingVO.setTotalRecord(totalRecord);
		    pagingVO.setDataList(comReviewList);
		}
		return comReviewList;
	}

	@Override
	public ServiceResult modifyComReview(ComReviewVO comReview) {
		retrieveComReview(comReview.getReviewSn());
        int rowcnt = comReviewDAO.updateComReview(comReview);

        return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

//	@Override
//	public ServiceResult removeComReview(ComReviewVO comReview) {
//		
//		return result;
//	}

}
