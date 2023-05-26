package kr.co.jobara.listener.event;

import kr.co.jobara.member.vo.PmemberVO;

public class LogoutSuccessEvent {
	private PmemberVO target;
	public LogoutSuccessEvent(PmemberVO target) {
		super();
		this.target = target;
	}
	public PmemberVO getTarget() {
		return target;
	}
	public void setTarget(PmemberVO target) {
		this.target = target;
	}
	
}
