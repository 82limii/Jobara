package kr.co.jobara.pms.project.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pms.project.vo.ProNoticeVO;

/**
 * 프로젝트 보고서 관리 로직
 * @author PC20
 */
public interface ProNoticeService {
	public ServiceResult createNotice(ProNoticeVO notice);
	public ServiceResult modifyNotice(ProNoticeVO notice);
	public ServiceResult removeNotice(ProNoticeVO notice);
	public ProNoticeVO retrieveNotice(int notiSn);
	public List<ProNoticeVO> retrieveNoticeList(PagingVO<ProNoticeVO> pagingVO);
	
}
