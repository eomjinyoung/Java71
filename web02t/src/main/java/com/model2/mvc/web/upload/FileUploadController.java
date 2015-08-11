package com.model2.mvc.web.upload;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
  @Autowired ServletContext sc;

	@RequestMapping("/FileUpload.do")
	public String upload(
	    String name,
	    @RequestParam(required=false) MultipartFile photo,
	    Model model) throws Exception {
		
	  if (photo.getSize() != 0) {
  	  // 업로드한 원래 파일 이름
  	  String originFilename = photo.getOriginalFilename();
  	  
  	  // 원래 파일 이름에서 확장명을 추출: 예) .pdf, .jpg
  	  int lastDotPosition = originFilename.lastIndexOf(".");
  	  String extname = originFilename.substring(lastDotPosition);
  	  
  	  // 새 파일명 준비
  	  // 다른 사람이 올린 파일 이름과 중돌을 방지하지 위해 밀리초를 기반으로 새 파일명을 만든다.
  	  String newFilename = System.currentTimeMillis() + extname;
  	  
  	  // 파일을 저장할 위치 알아내기
  	  String realUploadPath = sc.getRealPath("/upload");
  	  
  	  // 파일을 저장할 위치 + 새 파일명
      File newPath = new File(realUploadPath + "/" + newFilename);
      
      // 임시 폴더에 있는 파일을 새 위치와 새 파일명으로 옮긴다.
  	  photo.transferTo(newPath);

  	  model.addAttribute("filename", newFilename);
	  }
	  
	  // JSP가 새 파일 이름을 알 수 있도록 Model 객체에 담는다.
    model.addAttribute("name", name);
    
	  return "/upload/FileUploadResult.jsp";
	}
	
	
}









