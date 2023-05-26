package kr.co.jobara.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of="adminId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminVO {

	private String adminId;
	private String adminPass;
	
}
