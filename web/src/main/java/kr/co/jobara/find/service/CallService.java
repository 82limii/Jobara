package kr.co.jobara.find.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

//@Service
public class CallService {
	
	public static void pCall(String memTel, String ranNum) {

        String api_key = "NCSVLP2KETFHHZDE";
        String api_secret = "N5A6B2THTPVDNAMVTC2K4007WQ1S7RYR";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", memTel);    // 수신전화번호
        params.put("from", "01031142850");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "[JOBARA] 인증 메시지 : 인증번호는" + "["+ranNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

}
