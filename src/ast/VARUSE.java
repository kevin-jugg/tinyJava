package ast;

import libs.NameCheckException;
import libs.Node;

import javax.swing.*;
import java.util.ArrayList;

public class VARUSE extends STATEMENT {
    String varName;
    String operation;
    String next;
    String anotherVarName;
    String nameAfterOperation;
    boolean useAnotherVarAfterEquation;
    @Override
    public void parse() {
        varName = tokenizer.getNext();
        tokenizer.getAndCheckNext("=");
        // use variable after equation
        if (tokenizer.checkTokens(convertToString(Node.variableNames))) {
            useAnotherVarAfterEquation = true;
            anotherVarName = tokenizer.getNext();
            operation = tokenizer.getNext();
            nameAfterOperation = tokenizer.getNext();
        } else{
            next = tokenizer.getNext();
        }
        tokenizer.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {
        if (useAnotherVarAfterEquation) {
            writer.println(varName + " = " + anotherVarName + " " + operation+ " " + nameAfterOperation + ";");
        } else {
            writer.println(varName + " = " + next + ";");
        }

    }

    @Override
    public void nameCheck() {
        if (!isContain(varName,Node.variableNames)) {
            throw new NameCheckException(varName);
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
