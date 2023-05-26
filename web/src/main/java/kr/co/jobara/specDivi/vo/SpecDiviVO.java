package kr.co.jobara.specDivi.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecDiviVO implements Serializable {
	
	private int rnum;
	
	private Integer sdiSn;
	private String eduSn;
	private String sdiCareer;
	private String skillSn;
	private String sdiLang;
	private String sdiSerti;
	private String sdiAct;
	private String sdiComplete;
	private Integer empSn;
}
