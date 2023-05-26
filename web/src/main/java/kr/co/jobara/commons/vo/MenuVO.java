package kr.co.jobara.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {
	private String menuId;
	private String menuSort;
	private String menuName;
	private String menuUrl;
	private String menuUpper;
	private String menuFlag;
}
