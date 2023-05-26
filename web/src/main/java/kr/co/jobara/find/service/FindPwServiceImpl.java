package kr.co.jobara.find.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.member.dao.EmemberDAO;
import kr.co.jobara.member.dao.PmemberDAO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;
import kr.co.jobara.utils.SecurityUtils;


/**
 * @author PC-17
 * 장인슬
 */
@Service("servicePw")
public class FindPwServiceImpl implements FindPwService {

	
	@Inject
	private EmemberDAO ememberDAO;
	
	@Inject
	private PmemberDAO pmemberDAO;
	
	private void encryptEmemPass(EmemberVO input) {
		String plain = input.getEmemPass();
		String encoded = SecurityUtils.encode(plain);
		input.setEmemPass(encoded);	
	}
	
	private void encryptPmemPass(PmemberVO input) {
		String plain = input.getPmemPass();
		String encoded = SecurityUtils.encode(plain);
		input.setPmemPass(encoded);	
	}


	@Override
	public EmemberVO findPwEmem(EmemberVO inputData) {
		return ememberDAO.findPwEmem(inputData);
	}

	@Override
	public void updatePassEmem(EmemberVO inputData) {	
		
		encryptEmemPass(inputData);
		ememberDAO.updatePassEmem(inputData);
	}

	@Override
	public PmemberVO findPwPmem(PmemberVO inputData) {
		return pmemberDAO.findPwPmem(inputData);
	}

	@Override
	public void updatePassPmem(PmemberVO inputData) {
		encryptPmemPass(inputData);
		pmemberDAO.updatePassPmem(inputData);
	}

}
