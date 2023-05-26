package kr.co.jobara.incumbent.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="empSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IncumbentVO implements Serializable {
	
	private int rnum;
	
	private Integer empSn;
	
	private String pmemId;
	private String pmemEmail;
	private String pmemTel;
	private String empStateCd;
	
	private String ememId;
	
	private String empStartd;
	
	private String empEndd;
	
	private String empPosiCd;
	
	private String empDeptCd;
	
	private String empSalary;
	
	//db 조인 용도
	private String pmemNm;
	private int manYear;
	private String pmemSex;
}
