package kr.co.jobara.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.notice.vo.NoticeVO;

@Mapper
public interface NoticeDAO {
	
	public NoticeVO selectNotice(int noticeSn);
	
	public List<NoticeVO> selectNoticeList(PagingVO<NoticeVO> pagingVO);
	
	public int selectTotalRecord(PagingVO<NoticeVO> pagingVO);
	
	public int insertNotice(NoticeVO notice);
	
	public int updateNotice(NoticeVO notice);
	
	public int deleteNotice(int noticeSn);
}
