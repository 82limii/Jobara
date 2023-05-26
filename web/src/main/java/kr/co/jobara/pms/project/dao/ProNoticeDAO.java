package kr.co.jobara.pms.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pms.project.vo.ProNoticeVO;

@Mapper
public interface ProNoticeDAO {
	
	public int insertNotice(ProNoticeVO notice);
	public int updateNotice(ProNoticeVO notice);
	public int deleteNotice(ProNoticeVO notice);
	public ProNoticeVO selectNotice(int notiSn);
	public List<ProNoticeVO> selectNoticeList(PagingVO<ProNoticeVO> pagingVO);
	public int selectTotalRecord(PagingVO<ProNoticeVO> pagingVO);
}
