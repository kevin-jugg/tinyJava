package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class FORLOOP extends STATEMENT {
    private List<STATEMENT> statements = new ArrayList<>();
    String[] vartypes = {"int","String","boolean"};
    String next;
    String indexName;
    String indexInitialValue;
    String operation;
    String operationC;
    String endValue;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("for");
        tokenizer.getAndCheckNext("(");
        tokenizer.getAndCheckNext("int");
        indexName = tokenizer.getNext();
        tokenizer.getAndCheckNext("=");
        indexInitialValue = tokenizer.getNext();
        tokenizer.getAndCheckNext(";");
        tokenizer.getAndCheckNext(indexName);
        operation = tokenizer.getNext();
        endValue = tokenizer.getNext();
        tokenizer.getAndCheckNext(";");
        tokenizer.getAndCheckNext(indexName);
        operationC = tokenizer.getNext();
        tokenizer.getAndCheckNext(operationC);
        tokenizer.getAndCheckNext(")");
        tokenizer.getAndCheckNext("{");
        while (!tokenizer.checkToken("}")) {
            STATEMENT s = null;
            if (tokenizer.checkTokens(convertToString(Node.variableNames))) {
                s = new VARUSE();
            }
            s.parse();
            statements.add(s);
        }
        tokenizer.getAndCheckNext("}");
    }

    @Override
    public void evaluate() {
        writer.println("for (int " + indexName + " = " + indexInitialValue + "; "+indexName+operation+endValue+"; "+indexName+operationC+operationC+") {");
        for (STATEMENT s : statements){
            s.evaluate();
        }
        writer.println("}");
    }

    @Override
    public void nameCheck() {
        for (STATEMENT s : statements){
            s.nameCheck();
        }
    }

    @Override
    public void typeCheck() {
        for (STATEMENT s : statements){
            s.typeCheck();
        }
    }

    public String[] convertToString(ArrayList<String> s) {
        String[] myString = new String[s.size()];
        for (int i = 0; i < s.size(); i++) {
            myString[i] = s.get(i);
        }
        return myString;
    }
}
