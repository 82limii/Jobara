package kr.co.jobara.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pay.dao.PayListDAO;
import kr.co.jobara.pay.vo.PayListVO;

public class PayListServiceImpl implements PayListService {
	
	private PayListDAO payListDAO;
	
	@Required
	public void setPayListDAO(PayListDAO plDAO) {
		this.payListDAO = plDAO;	
	}
	

	@Override
	public PayListVO retrievePay(String impUid) {
		PayListVO payList = payListDAO.selectPay(impUid);
		return payList;
	}

	@Override
	public List<PayListVO> retrievePayList(PagingVO<PayListVO> pagingVO) {
		List<PayListVO> pl = payListDAO.selectPayList(pagingVO);
		return pl;
	}

	@Override
	public ServiceResult createPayList(PayListVO pl) {
		int rowcnt = payListDAO.insertPayList(pl); 
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
