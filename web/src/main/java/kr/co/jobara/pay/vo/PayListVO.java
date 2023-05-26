package kr.co.jobara.pay.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayListVO {
	private String impUid;
	private String merchantUid;
	private Date payDate;
	private String cardNo;
}
