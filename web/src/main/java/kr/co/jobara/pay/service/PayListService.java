package kr.co.jobara.pay.service;

import java.util.List;

import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.pay.vo.PayListVO;

/**
 * 결제내역용 Business Logic Layer
 */
public interface PayListService {

	/**
	 * 결제 내역 상세 조회
	 * @param impUid
	 * @return 존재하지 않으면, {@link PKNotFoundException} 발생
	 */
	public PayListVO retrievePay(String impUid);
	
	public List<PayListVO> retrievePayList(PagingVO<PayListVO> pagingVO);
	
	/**
	 * 신규 결제 내역 등록
	 * @param payList
	 * @return OK, FAIL
	 */
	public ServiceResult createPayList(PayListVO pl);

}

