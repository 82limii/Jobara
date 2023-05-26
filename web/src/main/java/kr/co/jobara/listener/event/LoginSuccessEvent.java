package kr.co.jobara.listener.event;

import kr.co.jobara.member.vo.PmemberVO;

public class LoginSuccessEvent {
	
	private PmemberVO target;
	public LoginSuccessEvent(PmemberVO target) {
		super();
		this.target = target;
	}
	public void setTarget(PmemberVO target) {
		this.target = target;
	}
	public PmemberVO getTarget() {
		return target;
	}	
	
}
