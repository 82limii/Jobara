package kr.co.jobara.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.pay.vo.PayListVO;

@Mapper
public interface PayListDAO {
	/**
	 * 결제내역 상세 조회
	 * @param impUid
	 * @return 존재하지 않으면, null 반환
	 */
	public PayListVO selectPay(String impUid);
	
	/**
	 * 상품 목록 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public List<PayListVO> selectPayList(PagingVO pagingVO);
	
	/**
	 * 상품 등록
	 * @param pay
	 * @return >0 : OK
	 */
	public int insertPayList(PayListVO pl);

}

