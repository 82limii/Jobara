package kr.co.jobara.board.shareit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.board.shareit.vo.ShareItVO;
import kr.co.jobara.commons.vo.PagingVO;

/**
 * @author 최인수
 * shareit 게시판
 * 답글 기능 구현
 */
@Mapper
public interface ShareItDAO {
	public List<ShareItVO> selectShareItBoardList(PagingVO<ShareItVO> pagingVO);
	public int selectTotalRecord(PagingVO<ShareItVO> pagingVO); 
	
	public ShareItVO selectShareItBoard(int reBoardSn);
	public int insertShareItBoard(ShareItVO shareIt);
	public int updateShareItBoard(ShareItVO shareIt);
	public int deleteShareItBoard(ShareItVO shareIt);
	
	
}
