package ui;

import ast.PROGRAM;
import libs.Node;
import libs.Tokenizer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> literals = Arrays.asList("public", "class", "{", "}", "this.","=","new","[","]","return",";","int","(",")","+","-","*","/","&&","||","String","boolean","for","<",">");
        Tokenizer.makeTokenizer("input.tdot",literals);
        Node.setWriter("output.java");
        Node program = new PROGRAM();
        program.parse();
        program.nameCheck();
//        program.typeCheck();
        program.evaluate();
        Node.closeWriter();
    }

}
