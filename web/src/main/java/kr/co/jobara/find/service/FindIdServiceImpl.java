package kr.co.jobara.find.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.member.dao.EmemberDAO;
import kr.co.jobara.member.dao.PmemberDAO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.PmemberVO;

@Service("service")
public class FindIdServiceImpl implements FindIdService {
//	장인슬
	@Inject
	private PmemberDAO pmemberDAO;
	
	@Inject
	private EmemberDAO ememberDAO;
	
	@Override
	public PmemberVO findIdPmem (PmemberVO inputData) {
		
		return pmemberDAO.findIdPmem(inputData);
	}
	@Override
	public EmemberVO findIdEmem (EmemberVO inputData) {
		return ememberDAO.findIdEmem(inputData);
	}

}
