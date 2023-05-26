package kr.co.jobara.fitme.faceMatching.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="pmemId")
@Slf4j
public class FaceMatchingVO {
	private int rnum;
	
	
	private String faceEnter1;
	private String facePer1;
	private String faceEnter2;
	private String facePer2;
	private String faceEnter3;
	private String facePer3;
	private String faceEnter4;
	private String facePer4;
	private String faceEnter5;
	private String facePer5;
	private String pmemId;
		
	private String pmemNm;
	private String pmemPic;
	
	private String faceMsg;

	
}
