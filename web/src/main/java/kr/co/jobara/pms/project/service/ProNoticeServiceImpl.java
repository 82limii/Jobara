package kr.co.jobara.pms.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.dao.ProNoticeDAO;
import kr.co.jobara.pms.project.vo.ProNoticeVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProNoticeServiceImpl implements ProNoticeService {

	@Inject
	private ProNoticeDAO proNoticeDAO;
	
	@Override
	public ServiceResult createNotice(ProNoticeVO notice) {
		int rownum = proNoticeDAO.insertNotice(notice);
		return rownum > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyNotice(ProNoticeVO notice) {
		retrieveNotice(notice.getNotiSn());
		int rownum = proNoticeDAO.updateNotice(notice);
		return rownum > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeNotice(ProNoticeVO notice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProNoticeVO retrieveNotice(int notiSn) {
		ProNoticeVO notice = proNoticeDAO.selectNotice(notiSn);
		if(notice == null)
			throw new PKNotFoundException(notiSn + "글 없음");
		return notice;
	}

	@Override
	public List<ProNoticeVO> retrieveNoticeList(PagingVO<ProNoticeVO> pagingVO) {
		int totalRecord = proNoticeDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ProNoticeVO> selectNoticeList = proNoticeDAO.selectNoticeList(pagingVO);
		pagingVO.setDataList(selectNoticeList);
		return selectNoticeList;
	}

}
