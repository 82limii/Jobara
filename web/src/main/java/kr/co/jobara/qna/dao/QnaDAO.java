package kr.co.jobara.qna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.qna.vo.QnaVO;

@Mapper
public interface QnaDAO {
	
	public QnaVO selectQna(int qnaSn);
	
	public List<QnaVO> selectQnaList(PagingVO<QnaVO> pagingVO);
	
	public int selectTotalRecord(PagingVO<QnaVO> pagingVO);
	
	public int insertQna(QnaVO qna);
	
	public int updateQna(QnaVO qna);
	
	public int deleteQna(int qnaSn);
	
}
