package libs;

public class NameCheckException extends RuntimeException {
    public NameCheckException(String s){
        System.out.println("NAME CHECK FAIL!!! You forgot to declare shape: "+s);
    }
}
