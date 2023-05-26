package kr.co.jobara.board.askit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.board.askit.vo.CommentsVO;
import kr.co.jobara.commons.vo.PagingVO;

@Mapper
public interface CommentsDAO {
	public int insertReply(CommentsVO reply);
	public int updateReply(CommentsVO reply);
	public int deleteReply(int commSn);
	public List<CommentsVO> selectReplyList(PagingVO<CommentsVO> pagingVO);
	public int selectTotalRecord(PagingVO<CommentsVO> pagingVO);
}
