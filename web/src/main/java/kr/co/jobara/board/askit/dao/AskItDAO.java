package kr.co.jobara.board.askit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.board.askit.vo.AskItVO;
import kr.co.jobara.commons.vo.PagingVO;

@Mapper
public interface AskItDAO {
	public int insertAskItBoard(AskItVO askItBoard);
	public int updateAskItBoard(AskItVO askItBoard);
	public int deleteAskItBoard(int boardSn);
	public AskItVO selectAskItBoard(int boardSn);
	public List<AskItVO> selectAskItBoardList(PagingVO<AskItVO> askItBoard);
	public int selectTotalRecord(PagingVO<AskItVO> paging);
}
