package kr.co.jobara.listener.event;

import kr.co.jobara.member.vo.EmemberVO;

public class ELogoutSuccessEvent {
	private EmemberVO target;
	public ELogoutSuccessEvent(EmemberVO target) {
		super();
		this.target = target;
	}
	public EmemberVO getTarget() {
		return target;
	}
	public void setTarget(EmemberVO target) {
		this.target = target;
	}
	
}
