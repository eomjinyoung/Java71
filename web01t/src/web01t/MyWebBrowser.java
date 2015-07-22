package web01t;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyWebBrowser {

  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("www.daum.net", 80);
    PrintStream out = new PrintStream(socket.getOutputStream());
    BufferedReader in = 
        new BufferedReader(
            new InputStreamReader(
                socket.getInputStream()));
    
    out.println("GET / HTTP/1.1");
    out.println("Host: www.daum.net");
    out.println();
    
    String line = null;
    while (!(line = in.readLine()).equals("")) {
      System.out.println(line);
    }
    System.out.println();
    
    char[] buf = new char[1024];
    int len = 0;
    while ((len = in.read(buf)) > 0) {
      System.out.println(String.valueOf(buf, 0, len));
    }
    
    in.close();
    out.close();
    socket.close();
  }

}









