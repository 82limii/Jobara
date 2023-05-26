package kr.co.jobara.faq.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.faq.dao.FaqDAO;
import kr.co.jobara.faq.vo.FaqVO;

@Service
public class FaqServiceImpl implements FaqService {

	@Inject
	private FaqDAO faqDAO;
	
	@Override
	public ServiceResult createFaq(FaqVO faq) {
		int rowcnt = faqDAO.insertFaq(faq);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<FaqVO> retrieveFaqList(PagingVO<FaqVO> pagingVO) {
		int totalRecord = faqDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<FaqVO> faqList = faqDAO.selectFaqList(pagingVO);
		pagingVO.setDataList(faqList);
		return faqList;
	}

	@Override
	public ServiceResult modifyFaq(FaqVO faq) {
		retrieveFaq(faq.getFaqSn());
		int rowcnt = faqDAO.updateFaq(faq);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeFaq(int faqSn) {
		int rowcnt = faqDAO.deleteFaq(faqSn);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public FaqVO retrieveFaq(int faqSn) {
		FaqVO faq = faqDAO.selectFaq(faqSn);
		if(faq == null)
			throw new PKNotFoundException(faqSn+"에 해당하는 게시글이 없음");
		return faq;
	}

}
