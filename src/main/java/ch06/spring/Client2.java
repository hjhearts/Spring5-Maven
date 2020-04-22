package ch06.spring;

public class Client2 {
    private String host;
    public void setHost(String host){
        this.host = host;
    }
    public void connect(){
        System.out.println("Client2.connect()");
    }
    public void send(){
        System.out.println("host : " + host);
    }
    public void close(){
        System.out.println("Client2.close()");
    }
}
