package kr.co.jobara.pms.project.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import kr.co.jobara.validate.hints.InsertGroup;
import kr.co.jobara.validate.hints.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="protSn")
@Builder
@Slf4j
public class ProTaskVO implements Serializable {
	private int rnum;
	
	@NotNull(groups=UpdateGroup.class)
	private Integer protSn;
	@NotNull(groups=UpdateGroup.class)
	private Integer proSn;

	private String pmemId;
	@NotBlank(groups= {InsertGroup.class, Default.class})	
	//@NotBlank
	private String protNm;
	private String protImprtCd;
	private String protImprt;
	@NotBlank
	private String protManager;
	private String protDate;
	@NotBlank
	private String protStartd;
	@NotBlank
	private String protEndd;
	private Integer protProgress;
	@NotBlank
	private String protState;
	private String protStateNm;
	
	private String protEdit;
	private String pmemNm;
	
	
}










