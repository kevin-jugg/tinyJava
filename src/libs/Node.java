package libs;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Node {
    public static String className = "";
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> methodNames = new ArrayList<>();
    public static ArrayList<String> variableNames = new ArrayList<>();
    public static HashMap<String, String> types = new HashMap<>();
    public static HashMap<String, String> values = new HashMap<>();
    protected Tokenizer tokenizer = Tokenizer.getTokenizer();
    static protected PrintWriter writer; //in case you need to write something to a file!
    public static void setWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(name, "UTF-8");
    }
    public static void closeWriter(){
        writer.close();
    }

    abstract public void parse();
    abstract public void evaluate();
    abstract public void nameCheck();
    abstract public void typeCheck();

}
