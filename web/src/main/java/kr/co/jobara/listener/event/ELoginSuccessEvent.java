package kr.co.jobara.listener.event;

import kr.co.jobara.member.vo.EmemberVO;

public class ELoginSuccessEvent {

	private EmemberVO target;
	public ELoginSuccessEvent(EmemberVO target) {
		super();
		this.target = target;
	}
	public void setTarget(EmemberVO target) {
		this.target = target;
	}
	public EmemberVO getTarget() {
		return target;
	}	
}
