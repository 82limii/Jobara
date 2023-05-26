package kr.co.jobara.pms.project.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="proSn")
public class ProCreateVO {
	private Integer proSn;
	private String ememId;
	private String proName;
	private String proStartd;
	private String proEndd;
	
	private List<ProMemberVO> projectMemberList;
}
