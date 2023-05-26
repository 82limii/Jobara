package kr.co.jobara.find.service;

import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

public interface FindIdService {
	
	public PmemberVO findIdPmem (PmemberVO inputData);
	
	public EmemberVO findIdEmem (EmemberVO inputData);

}
