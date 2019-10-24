package ast;

import libs.NameCheckException;
import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class METHOD extends STATEMENT {
    private List<STATEMENT> statements = new ArrayList<>();
    String name;
    String next;
    String returnType;
    String parameterType;
    String parameterName;
    String methodName;
    String returnName;
    // it's either a method or a constructor
    boolean isMethod;
    boolean hasParameter;
    String[] returnTypes = {"int","String","boolean","void"};
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("public");
        next = tokenizer.getNext();
        if (isMethodFunction(next)) {
            isMethod = true;
            returnType = next;
        }
        else {
            isMethod = false;
            name = next;
        }
        //if this is a constructor
        if (!isMethod) {
            tokenizer.getAndCheckNext("(");
            next = tokenizer.getNext();
            if (next.equals(")")) {
                hasParameter = false;
            } else {
                hasParameter = true;
            }
            if (hasParameter) {
                parameterType = next;
                parameterName = tokenizer.getNext();
                tokenizer.getAndCheckNext(")");
                tokenizer.getAndCheckNext("{");
            } else {
                tokenizer.getAndCheckNext("{");
            }
            while (!tokenizer.checkToken("}")) {
                STATEMENT s = null;
                if (tokenizer.checkToken("this.")) {
                    s = new VARDEF();
                }
                s.parse();
                statements.add(s);
            }
            tokenizer.getAndCheckNext("}");
        } else {        // if this is a method
            methodName = tokenizer.getNext();
            Node.methodNames.add(methodName);
            tokenizer.getAndCheckNext("(");
            next = tokenizer.getNext();
            // has parameter
            if (!next.equals(")")) {
                hasParameter = true;
                parameterType = next;
                parameterName = tokenizer.getNext();
                tokenizer.getAndCheckNext(")");
            } else { // no parameter
                hasParameter = false;
            }
            System.out.println("here");
            tokenizer.getAndCheckNext("{");
            while (!tokenizer.checkToken("}")) {
                STATEMENT s = null;
                if (tokenizer.checkToken("return")) {
                    s = new RETURN();
                }
                else if (tokenizer.checkToken("for")) {
                    s = new FORLOOP();
                }
                else if (tokenizer.checkTokens(convertToString(Node.variableNames))) {
                    s = new VARUSE();
                }
                s.parse();
                statements.add(s);
            }
            tokenizer.getAndCheckNext("}");
        }
    }

    @Override
    public void evaluate() {
        if (!isMethod) {
            if (hasParameter) {
                writer.println("public " + name + "(" + parameterType + " " + parameterName + ") {");
            }
            else {
                writer.println("public " + name + "()"+" {");
            }
            for (STATEMENT s : statements) {
                s.evaluate();
            }
            writer.println("}");
        } else { // method
            if (hasParameter) {
                writer.println("public "+returnType+" "+methodName+"("+parameterType+" " +parameterName+")"+"{");
            } else {
                writer.println("public "+returnType+" "+methodName+"() "+"{");
            }
            for (STATEMENT s : statements) {
                s.evaluate();
            }
            writer.println("}");
        }
    }

    @Override
    public void nameCheck() {
        if (!isMethod) {
            if (!name.equals(Node.className)) {
                throw new NameCheckException(name);
            }
        }

    }

    @Override
    public void typeCheck() {
        for (STATEMENT s : statements){
            s.typeCheck();
        }
    }

    private boolean isMethodFunction(String s) {
        for (int i = 0; i < returnTypes.length; i++) {
            if (s.equals(returnTypes[i])) {
                return true;
            }
        }
        return false;
    }

    public String[] convertToString(ArrayList<String> s) {
        String[] myString = new String[s.size()];
        for (int i = 0; i < s.size(); i++) {
            myString[i] = s.get(i);
        }
        return myString;
    }
}
