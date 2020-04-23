package ch08.spring;

public class VersionPrinter {
    private int majorVersion;
    private int minorVersion;
    public void setMajorVersion(int majorVersion){
        this.majorVersion = majorVersion;
    }
    public void setMinorVersion(int minorVersion){
        this.minorVersion = minorVersion;
    }
    public void print(){
        System.out.printf("프로그램 버전은 %d.%d 입니다.", majorVersion, minorVersion);
        System.out.println();
        System.out.println();
    }
}
