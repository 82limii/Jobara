package kr.co.jobara.faq.service;

import java.util.List;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.faq.vo.FaqVO;

public interface FaqService {
	
	public ServiceResult createFaq(FaqVO faq);
	
	public List<FaqVO> retrieveFaqList(PagingVO<FaqVO> pagingVO);
	
	public FaqVO retrieveFaq(int faqSn);
	
	public ServiceResult modifyFaq(FaqVO faq);
	
	public ServiceResult removeFaq(int faqSn);
}
