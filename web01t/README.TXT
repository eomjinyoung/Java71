[Gradle 프로젝트 만들기]
1. 일반 프로젝트를 만든다.
2. 소스 코드 및 웹 자원을 저장할 폴더를 준비한다.
   => src
        /main
            /java : 자바 소스 파일 보관
            /resources : .xml, .properties 등 기타 파일 보관
            /webapp : 웹 자원(.html, .css, .js, .png, .gif 등) 보관
        /test
            /java : 단위 테스트를 수행하는 자바 소스 파일 보관
            /resources : 단위 테스트 관련 기타 파일 보관
   
3. .project 파일을 열고, 
   Gradle 플러그인을 사용한다고 설정하라.
   => <natures> 태그 안에 Gradle 플러그인 사용을 지정하라.
   => 어떻게? Google: eclipse .project nature gradle로 검색하라.
   => 다음을 <natures> 태그 안에 넣어라. 
   <nature>org.springsource.ide.eclipse.gradle.core.nature</nature>
4. build.gradle 파일을 생성한다.
5. build.gradle 파일 설정
  => 자바 및 이클립스 명령 플러그인 가져오기.
    apply plugin: 'java'
    apply plugin: 'eclipse'
6. 이클립스 관련 파일 생성
  => .classpath 파일 생성하기
    [컨텍스트 메뉴] Run As -> Gracle Build... 클릭
    "Gradle tasks"에서 다음 명령 입력
    eclipseClasspath 입력
    [run] 버튼 클릭
  => .project 파일을 개정하기
    "Gradle tasks"에서 다음 명령 입력
    eclipseProject 입력
    [run] 버튼 클릭
  => JDT(이클립스 자바 플러그인) 관련 파일 생성하기
    "Gradle tasks"에서 다음 명령 입력
    eclipseJdt 입력 --> 실행 
  => 팁!
    이클립스 관련 파일(.project, .classpath, .settings/*)을 모두 생성하는 방법
    "Gradle tasks"에서 다음 명령 입력
    eclipse 입력 --> 실행 
  => 참고
    cleanEclipse : 모든 이클립스 관련 파일 지우기
    cleanEclipseXxxxx : 해당 이클립스 파일 지우기
        
7. 자바 웹 프로젝트 설정하기
  => 웹 프로젝트 관련 명령어를 갖고 있는 플러그인 추가
     apply plugin: 'war'
     apply plugin: 'eclipse-wtp'    
  => Gradle Build..에서 
     eclipse 명령을 다시 실행하라!
     
8. 프로젝트 빌드 명령어 소개
  => clean
     /build 폴더를 제거한다.
  => compileJava
     /src/main/java 폴더의 소스 파일을 컴파일 하여 
     /build/classes/main 폴더에 클래스 파일을 둔다.
  
  => processResources
     /src/main/resources 폴더에 있는 파일을
     /build/resources/main 폴더에 복사한다.
  
  => classes
     compileJava + processResources 수행한다.   
        
  => compileTestJava
     classes 수행한다.
     /src/test/java 폴더의 소스 파일을 컴파일하여
     /build/classes/test 폴더에 클래스 파일을 둔다.     
     
  => jar
     classes 수행한다.
     /build/libs 폴더에 컴파일된 클래스 파일과 리소스 파일을 
     .jar 파일로 묶어서 저장한다.
  
  => build
       모든 빌드 작업을 수행한다.   
     
9. 소스 파일의 문자집합과 컴파일을 수행할 자바 버전 지정하기
  => 소스 파일의 문자집합 지정하기.
     Gradle은 소스 파일의 문자집합이 운영체제의 기본 문자집합으로 되어있다고
       간주한다. 
       그래서 컴파일할 때 한글 부분에 오류가 발생한다.
    => 해결 방안
       build.gradle에 소스 파일이 어떤 문자집합으로 인코딩 되었는지 알려준다.
  
  => 자바 문법의 버전을 지정한다.
  
10. 의존 라이브러리 자동으로 가져오기.
  => 의존 라이브러리를 가져올 서버 지정하기
    repositories {
      // 메이븐 중앙서버(https://repo1.maven.org/maven2/)에서 다운로드 받는다. 
      mavenCentral() 
    }
                
  => 의존 라이브러리 지정
    dependencies {
      //compile "그룹이름:라이브러리이름:버전"
      compile 'org.springframework:spring-context:4.1.7.RELEASE' 
      compile 'commons-codec:commons-codec:1.10'
    }
      
11. 스프링 MVC 프로젝트 준비
  => 의존 라이브러리 가져오기
     
 
     
     
     
     
     
     
     





