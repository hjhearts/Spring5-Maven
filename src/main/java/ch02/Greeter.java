package ch02;

public class Greeter {
    private String format;
    public String greet(String msg){
        return String.format(format, msg);
    }
    public void setFormat(String format){
        this.format = format;
    }
}
