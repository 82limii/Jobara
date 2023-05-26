package kr.co.jobara.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.notice.dao.NoticeDAO;
import kr.co.jobara.notice.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Inject
	private NoticeDAO noticeDAO;
	
	@Override
	public ServiceResult createNotice(NoticeVO notice) {
		int rowcnt = noticeDAO.insertNotice(notice);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<NoticeVO> retrieveNoticeList(PagingVO<NoticeVO> pagingVO) {
		int totalRecord = noticeDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> noticeList = noticeDAO.selectNoticeList(pagingVO);
		pagingVO.setDataList(noticeList);
		return noticeList;
	}

	@Override
	public NoticeVO retrieveNotice(int noticeSn) {
		NoticeVO notice = noticeDAO.selectNotice(noticeSn);
		if(notice == null) {
			throw new PKNotFoundException(noticeSn+"에 해당하는 게시글이 없음.");
		}
		return notice;
	}

	@Override
	public ServiceResult modifyNotice(NoticeVO notice) {
		retrieveNotice(notice.getNoticeSn());
		int rowcnt = noticeDAO.updateNotice(notice);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeNotice(int noticeSn) {
		int rowcnt = noticeDAO.deleteNotice(noticeSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
