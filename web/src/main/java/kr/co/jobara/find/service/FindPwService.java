package kr.co.jobara.find.service;

import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

public interface FindPwService {
	
	public PmemberVO findPwPmem (PmemberVO inputData);
	
	public EmemberVO findPwEmem (EmemberVO inputData);
	
	public void updatePassEmem (EmemberVO inputData);
	
	public void updatePassPmem (PmemberVO inputData);

}
