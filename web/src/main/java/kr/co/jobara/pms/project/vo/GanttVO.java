package kr.co.jobara.pms.project.vo;

import java.util.Date;

import lombok.Data;

@Data
public class GanttVO {
	
	private String proName;
	private String name;
	private Date start;
	private Date end;
	private String impCd;
	private String state;
	private String pid;
	private String eid;
	private Integer protSn;
	private Integer proSn;
	private Integer y;
}
