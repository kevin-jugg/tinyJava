package ast;

import libs.NameCheckException;
import libs.Node;

import java.util.ArrayList;

public class VARDEF extends STATEMENT {
    String name;
    String next;
    boolean isArray;
    String typeName;
    String paramName;
    String value;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("this.");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("=");
        next = tokenizer.getNext();
        if (next.equals("new")) {
            isArray = true;
        }
        if (isArray) {
            typeName = tokenizer.getNext();
            tokenizer.getAndCheckNext("[");
            paramName = tokenizer.getNext();
            tokenizer.getAndCheckNext("]");
        } else {
            value = next;
        }
        tokenizer.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {
        if(isArray){
            writer.println("this."+name+" = "+"new "+typeName+"["+paramName+"];");
        } else {
            writer.println("this."+name+" = "+value+";");
        }
    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }

    public String[] convertToString(ArrayList<String> s) {
        String[] myString = new String[s.size()];
        for (int i = 0; i < s.size(); i++) {
            myString[i] = s.get(i);
        }
        return myString;
    }

    public boolean isContain(String s, ArrayList<String> as) {
        String[] myString = convertToString(as);
        for (int i = 0; i < myString.length; i++) {
            if (myString[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
}
