package kr.co.jobara.jobboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jobara.commons.dao.AttatchDAO;
import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.jobboard.dao.JobBoardDAO;
import kr.co.jobara.jobboard.vo.JobBoardVO;

/**
 * @author 이상림
 *  최초작성2022.02.23
 */
@Service
public class JobBoardServiceImpl implements JobBoardService {
	
	@Inject
	private JobBoardDAO jobBoardDao;
	
	@Transactional
	@Override
	public ServiceResult createJobBoard(JobBoardVO jobBoard) {
		ServiceResult result = null;
		int rowcnt = jobBoardDao.insertJobBoard(jobBoard);
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

	@Override
	public List<JobBoardVO> retrieveJobBoardList(PagingVO<JobBoardVO> pagingVO) {
		List<JobBoardVO> jobBoardList = jobBoardDao.selectJobBoardList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = jobBoardDao.selectTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(jobBoardList);
		}
		return jobBoardList;
	}

	@Override
	public JobBoardVO retrieveJobBoard(int jobSn) {
		JobBoardVO jobBoard = jobBoardDao.selectJobBoard(jobSn);
		if(jobBoard == null) {
			throw new PKNotFoundException(jobSn + "에 해당하는 공고가 없음.");
		}
		return jobBoard;
	}

	@Override
	public List<JobBoardVO> retreiveMyJobBoard(PagingVO<JobBoardVO> pagingVO) {
		List<JobBoardVO> jobBoardList = jobBoardDao.selectMyJobBoard(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = jobBoardDao.selectMyTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(jobBoardList);
		}
		return jobBoardList;
	}

	@Transactional
	@Override
	public ServiceResult modifyJobBoard(JobBoardVO jobBoard) {
		ServiceResult result = null;
		retrieveJobBoard(jobBoard.getJobSn());
		int rowcnt = jobBoardDao.updateJobBoard(jobBoard);
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

}
