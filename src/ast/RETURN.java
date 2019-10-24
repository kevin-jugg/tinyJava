package ast;

import libs.NameCheckException;
import libs.Node;

import java.util.ArrayList;

public class RETURN extends STATEMENT {
    String returnVarName;
    String[] vartypes = {"int","String","boolean"};
    boolean isReturnMethod;
    String next;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("return");
        if (tokenizer.checkTokens(convertToString(Node.methodNames))) {
            System.out.println("true");
            isReturnMethod = true;
            next = tokenizer.getNext();
            tokenizer.getAndCheckNext("(");
            tokenizer.getAndCheckNext(")");
        } else {
            returnVarName = tokenizer.getNext();
        }
        tokenizer.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {
        if (isReturnMethod) {
            writer.println("return " + next + "()" +";");
        } else {
            writer.println("return " + returnVarName + ";");
        }
    }

    @Override
    public void nameCheck() {
        if (isReturnMethod) {
            if (!isContain(next,Node.methodNames)) {
                throw new NameCheckException(next);
            }
        }
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
