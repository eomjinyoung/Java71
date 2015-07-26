package web01t;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.apache.commons.codec.binary.Base64;

public class Base64Generator {

  public static void main(String[] args) throws Exception {
    FileInputStream in = 
        new FileInputStream("WebContent/step01/pic02.jpg");
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    
    int b;
    while ((b = in.read()) != -1) {
      out.write(b);
    }
    
    String base64 = Base64.encodeBase64String(out.toByteArray());
    System.out.println(base64);
    
    in.close();
    out.close();
  }

}














