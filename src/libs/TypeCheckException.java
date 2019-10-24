package libs;

public class TypeCheckException extends RuntimeException {
    public TypeCheckException(String n1, String s1, String n2, String s2){
        System.out.print("TYPE CHECK FAIL!!!!!!! Tried to connect ");
        System.out.print(n1);
        System.out.print(" (a "+s1+")");
        System.out.print(" to ");
        System.out.print(n2);
        System.out.println(" (a "+s2+")");

    }
}
