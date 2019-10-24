package ast;

import libs.NameCheckException;
import libs.Node;

import java.util.ArrayList;

public class VARDEC extends STATEMENT {
    String name = null;
    String vartype;
    String next;
    boolean isArray;
    String[] vartypes = {"int","String","boolean"};
    @Override
    public void parse() {
        vartype = tokenizer.getNext();
        next = tokenizer.getNext();
        System.out.println("hello>" + vartype +" " +next);
        if (next.equals("[")) {
            isArray = true;
        } else {
            isArray = false;
        }
        if (isArray) {
            tokenizer.getAndCheckNext("]");
            name = tokenizer.getNext();
        } else {
            name = next;
        }
        Node.variableNames.add(name);
        tokenizer.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {
        if (isArray) {
            writer.println(vartype+"[] "+name+";");
        }
        else {
            writer.println(vartype+" "+name+";");
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