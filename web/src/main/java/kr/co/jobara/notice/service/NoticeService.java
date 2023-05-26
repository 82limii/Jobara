package kr.co.jobara.notice.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.notice.vo.NoticeVO;

public interface NoticeService {
	
	public ServiceResult createNotice(NoticeVO notice);
	
	public List<NoticeVO> retrieveNoticeList(PagingVO<NoticeVO> pagingVO);
	
	public NoticeVO retrieveNotice(int noticeSn);
	
	public ServiceResult modifyNotice(NoticeVO notice);
	
	public ServiceResult removeNotice(int noticeSn);
}
