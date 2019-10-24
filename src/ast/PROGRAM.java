package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends Node {
    private List<STATEMENT> statements = new ArrayList<>();
    String className;
    public int howManyLeftBracketsLeft = 1;
    String[] vartypes = {"int","String","boolean"};
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("public");
        tokenizer.getAndCheckNext("class");
        className = tokenizer.getNext();
        Node.className = className;
        tokenizer.getAndCheckNext("{");
        while(!(tokenizer.checkToken("}"))){
            STATEMENT s = null;
            if (tokenizer.checkTokens(vartypes)){
                s = new VARDEC();
            }
            else if (tokenizer.checkToken("public")) {
                s = new METHOD();
            }
            s.parse();
            statements.add(s);
        }
    }

    @Override
    public void evaluate() {
        writer.println("public class "+className+" {");
        for (STATEMENT s : statements){
            s.evaluate();
        }
        writer.println("}");
    }

    public void nameCheck(){
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

}
