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
     
     
     
     
     
     
     
     
     
     
     
     
     





