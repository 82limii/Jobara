package kr.co.jobara.pms.project.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of="proSn")
public class ProMemberVO implements Serializable {
	
	private int rnum;
	private Integer proSn;
	private String pmemId;
	private String pmemNm;
	private String memPosition;
	private String memEmail;
	private String memTel;
	private String memFree;
	private String proName;
	private String memState;
	
	private List<ProMemberVO> projectMemberList;
}
