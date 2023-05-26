package kr.co.jobara.listener.event;

import kr.co.jobara.admin.vo.AdminVO;

public class ALoginSuccessEvent {
	
	private AdminVO target;
	public ALoginSuccessEvent(AdminVO target) {
		super();
		this.target = target;
	}
	public void setTarget(AdminVO target) {
		this.target = target;
	}
	public AdminVO getTarget() {
		return target;
	}
}
