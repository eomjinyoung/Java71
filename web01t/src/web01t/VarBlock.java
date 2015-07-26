package web01t;

import java.util.HashMap;

public class VarBlock {

  public static void main(String[] args) {
    // 자바는 블록 단위로 변수의 생명이 유지된다.
    int a;
    //int a; // 변수를 중복 선언하는 것은 오류다!
    {
      int b;
      a = 20;
      b = 20;
      {
        int c;
        a = 10;
        b = 20;
        c = 30;
      }
      //c = 40;
    }
    //b = 50;
    
    HashMap<String,Object> map = new HashMap<String,Object>();
    
    map.put("a", 20); // 20 --> new Integer(20)
    System.out.println(map.get("a"));

    map.put("a", "Hello");
    System.out.println(map.get("a"));
    
    int i1 = 20;
    Integer obj = new Integer(20);
    int i2 = obj; // --> obj.intValue() 호출한다.
                  // 객체에 보관된 int 값을 자동으로 꺼낸다. : unboxing
    
    Integer i3 = new Integer(30);
    Integer i4 = 40; // --> new Integer(40)
                     // 40 값을 가지고 Integer 객체로 자동으로 만든다.: boxing
    
    // 변수의 타입에 따라 객체를 값으로 값을 객체로 자동 변환하는 것을 "auto-boxing"이라 부른다.

  }

}





















