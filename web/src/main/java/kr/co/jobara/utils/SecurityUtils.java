package kr.co.jobara.utils;

import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SecurityUtils {
	public static String encryptSha512(String plain){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = plain.getBytes();
			byte[] encypted = md.digest(input);
			System.out.println(encypted.length*8);
			String encoded = Base64.getEncoder().encodeToString(encypted);
			System.out.println(encoded);
			return encoded;
		}catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	// 실제 회원가입 시 패스워드 Bcrypt 암호화 적용
	public static String encode(String plain) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoded = "{bcrypt}" + encoder.encode(plain);
		return encoded;
	}
	
//	Bcrypt 암호화 방법
//	public static void main(String[] args) {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		String plain = "java";
//		String encoded = "{bcrypt}" + encoder.encode(plain);
//		System.out.println("encoded : " + encoded);
//		String pass = "$2a$10$R/UaMuwU.S6n8KH2Z/eeGuGSiXtDA5F.nYgTyYTDyv1G/dBKBUrdi";
//		
//		boolean matches = encoder.matches(plain, pass);
//		System.out.println(matches);
//	}
	
}
