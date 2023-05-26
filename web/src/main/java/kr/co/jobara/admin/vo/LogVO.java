package kr.co.jobara.admin.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 이상림
 * 최초작성 2022.03.16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="logSn")
public class LogVO {
	private Integer logSn;
	private String pmemId;
	private String ememId;
	private String logTime;
	private String logAdd;
}
