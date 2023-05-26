package kr.co.jobara.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainVO {
	public String rnum;

	public String ememId;
	public String ememNm;
	public String reviewComment;
	public String reviewDate;
	public String coceDeptCd;
	public String coceNm;
}
