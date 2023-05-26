package kr.co.jobara.faq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.faq.vo.FaqVO;

@Mapper
public interface FaqDAO {
	
	public FaqVO selectFaq(int faqSn);
	
	public int insertFaq(FaqVO faq);
	
	public int selectTotalRecord(PagingVO<FaqVO> pagingVO);
	
	public List<FaqVO> selectFaqList(PagingVO<FaqVO> paging);
	
	public int updateFaq(FaqVO faq);
	
	public int deleteFaq(int faqSn);
}
