package kr.co.jobara.commons.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.jobara.member.dao.EmemberDAO;
import kr.co.jobara.member.dao.PmemberDAO;
import kr.co.jobara.member.vo.EmemberVO;
import kr.co.jobara.member.vo.MemberVOWrapper;
import kr.co.jobara.member.vo.PmemberVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticateServiceImpl implements UserDetailsService {
	
	@Inject
	private EmemberDAO eMemberDAO;
	@Inject
	private PmemberDAO pMemberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		EmemberVO eSaved = eMemberDAO.selectEmemberForAuth(username);
		PmemberVO pSaved = pMemberDAO.selectPmemberForAuth(username);
		log.info("username" + username);
		if(eSaved == null && pSaved != null) {
			log.info("===========" + pSaved);
			return new MemberVOWrapper(pSaved);	

		} else if (eSaved != null && pSaved == null) {
			log.info("===========" + eSaved);
			return new MemberVOWrapper(eSaved);	
		} else {
			throw new UsernameNotFoundException(username+"으로 가입된 회원이 없음.");
		}
	}
}












