package kr.co.jobara.apply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.apply.dao.ApplyDAO;
import kr.co.jobara.apply.vo.ApplyVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

@Service
public class ApplyServiceImpl implements ApplyService {
	@Inject
	private ApplyDAO applyDao;

	@Override
	public ServiceResult applyBoard(ApplyVO apply) {
		ServiceResult result = null;
		
		
		int rowcnt = applyDao.insertApply(apply);
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		
		return result;
	}

	@Override
	public List<ApplyVO> retrieveJobList(PagingVO<ApplyVO> pagingVO) {

		int totalRecord = applyDao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		//list로 정보를 조회해서 가져온다.
		List<ApplyVO> List = applyDao.selectJobList(pagingVO);
		
		//조회한list정보를 pagingvo에 넣어준다.
		pagingVO.setDataList(List);
		
		return List;
	}

	@Override
	public List<ApplyVO> retrieveProList(PagingVO<ApplyVO> pagingVO) {
		int totalRecord = applyDao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		//list로 정보를 조회해서 가져온다.
		List<ApplyVO> List = applyDao.selectProList(pagingVO);
		
		//조회한list정보를 pagingvo에 넣어준다.
		pagingVO.setDataList(List);
		
		return List;
	}



}
