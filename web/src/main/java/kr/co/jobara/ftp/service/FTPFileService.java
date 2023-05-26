package kr.co.jobara.ftp.service;

import java.nio.file.Path;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.ftp.vo.FTPFileWrapper;

/**
 * 장인슬
 * @author PC-17
 * 2022-03-15
 */
public interface FTPFileService {
	//여러개의 파일을 횡단?
	public List<FTPFileWrapper> traversing(String path) throws Exception;
	//파일을 보냄
	public boolean sendFile(String path, MultipartFile sendFile) throws Exception;
	//파일을 받음
	public Path receiveFiles(String[] paths) throws Exception;
}
