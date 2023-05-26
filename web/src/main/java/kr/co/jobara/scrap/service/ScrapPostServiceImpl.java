package kr.co.jobara.scrap.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.scrap.dao.ScrapPostDAO;
import kr.co.jobara.scrap.vo.ScrapPostVO;
/**
 * 작성일 2022.03.03
 * @author 김승현
 * 공고스크랩(CRD)용 ServiceImpl
 */
@Service
public class ScrapPostServiceImpl implements ScrapPostService {

	@Inject
	private ScrapPostDAO scrapPostDAO;

	@Override
	public List<ScrapPostVO> retrieveScrapPostList(PagingVO<ScrapPostVO> pagingVO) {
		int totalRecord = scrapPostDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ScrapPostVO> scrapPostList = scrapPostDAO.selectScrapPostList(pagingVO);
		pagingVO.setDataList(scrapPostList);
		return scrapPostList;
	}

	@Transactional
	@Override
	public ServiceResult removeScrapPost(int scrapSn) {
//		ScrapPostVO scrapPost = scrapPostDAO.selectScrapPost(scrapSn);
//		if(scrapPost == null)
//			throw new PKNotFoundException(scrapSn+"에 해당하는 스크랩이 없음.");
		int rowcnt = scrapPostDAO.deleteScrapPost(scrapSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

//	@Override
//	public ScrapPostVO retrieveScrapPost(int scrapSn) {
//		ScrapPostVO scrapPost = scrapPostDAO.selectScrapPost(scrapSn);
//		if(scrapPost == null)
//			throw new PKNotFoundException(scrapSn+"에 해당하는 스크랩이 없음.");
//		return scrapPost;
//	}

	@Transactional
	@Override
	public ServiceResult createScrapPost(ScrapPostVO scrapPost) {
		int rowcnt = scrapPostDAO.insertScrapPost(scrapPost);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}


	@Override
	public List<ScrapPostVO> retrieveScrapCompanyList(PagingVO<ScrapPostVO> pagingVO) {
		int totalRecord = scrapPostDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ScrapPostVO> scrapCompanyList = scrapPostDAO.selectScrapCompanyList(pagingVO);
		pagingVO.setDataList(scrapCompanyList);
		return scrapCompanyList;
	}
}