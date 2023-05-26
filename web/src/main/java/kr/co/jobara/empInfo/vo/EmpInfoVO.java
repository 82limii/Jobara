package kr.co.jobara.empInfo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpInfoVO {
	private int empSn;
	private String pmemId;
	private String empStateCd;
	private String ememId;
	private String empStartd;
	private String empEndd;
	private String empPosiCd;
	private String empDeptCd;
	private String empSalary;
}
