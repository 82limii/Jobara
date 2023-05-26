package kr.co.jobara.info.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.info.dao.InfoDAO;
import kr.co.jobara.info.vo.InfoVO;
import kr.co.jobara.jobboard.vo.JobBoardVO;

@Service
public class InfoServiceImpl implements InfoService {
	
	@Inject
	private InfoDAO infoDAO;
	
	@Override
	public InfoVO retrieveInfo(String ememId) {
		InfoVO info = infoDAO.selectInfo(ememId);
		if(info == null)
			throw new PKNotFoundException(ememId+"에 해당하는 기업이 없음");
		return info;
	}

	@Override
	public List<JobBoardVO> retrieveInfoList(PagingVO<JobBoardVO> pagingVO) {
		List<JobBoardVO> infoList = infoDAO.selectInfoList(pagingVO);
		if(pagingVO!=null) {
			int totalRecord = infoDAO.selectTotalRecord(pagingVO);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(infoList);
		}
		return infoList;
	}
	
}
