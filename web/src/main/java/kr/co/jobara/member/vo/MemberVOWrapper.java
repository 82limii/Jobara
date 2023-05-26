package kr.co.jobara.member.vo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberVOWrapper extends User{
	
	private PmemberVO pmemUser;
	private EmemberVO ememUser;
	
	public MemberVOWrapper(PmemberVO pmemUser) {
		super(pmemUser.getPmemId(), pmemUser.getPmemPass(),
				AuthorityUtils.createAuthorityList(pmemUser.getPmemRole()));
		this.pmemUser = pmemUser;
	}
	
	public MemberVOWrapper(EmemberVO ememUser) {
		super(ememUser.getEmemId(), ememUser.getEmemPass(),
				AuthorityUtils.createAuthorityList(ememUser.getEmemRole()));
		this.ememUser = ememUser;
	}
	
	public PmemberVO getPmemUser() {
		return pmemUser;
	}
	public EmemberVO getEmemUser() {
		return ememUser;
	}
	
}
