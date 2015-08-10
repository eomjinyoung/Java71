package com.model2.mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload/FileUpload")
public class FileUploadServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 멀티 파트 형식으로 전송된 데이터는 다음과 같이 getParameter()를 사용하여
    // 꺼낼 수 없다.
    /*
    String name = req.getParameter("name");
    String photo = req.getParameter("photo");
    */
    
    try {
      //1) 클라이언트가 보낸 데이터를 파트 별로 분리하여 FileItem 객체로 만들어 줄 도구를 준비한다.
      DiskFileItemFactory factory = new DiskFileItemFactory();
      
      //2) 클라이언트 요청을 분석하여 FileItemFactory에 전달할 도구 준비한다. 
      ServletFileUpload upload = new ServletFileUpload(factory);
  
      //3) 클라이언트 요청을 분석한다.
      List<FileItem> items = upload.parseRequest(req);
      
      String name = null;
      String photo = null;
      
      for (FileItem item : items) {
        if (item.isFormField()) { // 일반 폼 데이터이냐?
          // 주의! 클라이언트가 보낸 한글 데이터를 Unicode로 제대로 변환하기 위해 
          // request.setCharacterEncoding()을 호출하는 것은 의미없다.
          // => 다음과 같이 getString()에서 문자집합을 지정하라!
          name = item.getString("UTF-8");
          
        } else { // file이냐?
          photo = item.getName(); // 클라이언트가 보낸 파일의 이름
          
          // 클라이언트가 보낸 파일은 임시 폴더에 저장되어 있다.
          // 이 파일을 원하는 경로에 옮긴다.
          // 파일을 옮길 경로를 File 객체에 담아서 파라미터로 전달하라!
          // 1) 현재 웹 애플리케이션의 배치 폴더 경로를 알아내기
          String realUploadPath = 
              this.getServletContext().getRealPath("/upload");
          File newPath = new File(realUploadPath + "/" + photo);
          item.write(newPath);
        }
      }
      
      
      resp.setContentType("text/html;charset=UTF-8");
      PrintWriter out = resp.getWriter();
      
      out.println("<html><body>");
      out.printf("이름: %s<br>\n", name);
      out.printf("사진: %s<br>\n", photo);
      out.printf("<img src='%s'><br>\n", photo);
      out.println("</body></html>");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}











