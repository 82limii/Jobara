package kr.co.jobara.utils;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import kr.co.jobara.commons.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadFile extends AbstractView {

   @Override
   protected void renderMergedOutputModel(
         Map<String, Object> model,
         HttpServletRequest request,
         HttpServletResponse response
   ) throws Exception {
      // FileDownloadController에서 model에 추가한 attribute를 불러오기
      AttatchVO file = (AttatchVO) model.get("attatch");
      
      // UTF-8로 인코딩 후 원래 파일명에 공백이 있는 경우 공백이 '+'로 바뀌어 오므로 '+'를 공백으로 변경
      String fileName = file.getAttNm();
      fileName = URLEncoder.encode(fileName, "UTF-8").replace('+', ' ');
      
      // attachment : 바로 다운로드, inline : 브라우저에서 렌더링
      response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
      response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
      response.setContentLengthLong(file.getAttSize());
      
      String attRoute = file.getAttRoute();
      String attSave = file.getAttSave();
      
      // 파일이 저장된 위치와 저장명으로 내보낼 파일을 찾아 출력
      File saveFile = new File(new File(attRoute), attSave);
      try (
         OutputStream os = response.getOutputStream();
      ) {
         FileUtils.copyFile(saveFile, os);
      }
   }
}