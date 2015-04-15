package com.lexemanalizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Vlad on 15.04.2015.
 */
public class LexemAnalizer {

    public static final String RESOURCES_PATH = "";
    public static final String fileName = "Test";



    public static List<String> readFIle(String fileName) throws IOException {
        List<String> stringLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_PATH + fileName + ".asm"));
        String line;
        while ((line = reader.readLine()) != null) {
            stringLines.add(line.toUpperCase());
        }
        return stringLines;
    }


    public static void main(String[] args) {

        LexemAnalizer lexemAnalizer = new LexemAnalizer();
        LexemCheck lexemCheck = new LexemCheck();
        lexemCheck.setupTheLists();

        try {
            List<String> stringLines = readFIle(lexemAnalizer.fileName);

            StringTokenizer tokenizer;
            for (String s : stringLines) {
                if (s.trim().isEmpty()) {
                    continue;
                }
                tokenizer = new StringTokenizer(s, ";,:+-[] *", true);
                System.out.println("Line ---  " + s + " --- contains:");
                int i = 1;
                while (tokenizer.hasMoreTokens()) {
                    String trim = tokenizer.nextToken().trim();
                    if (trim.isEmpty()) {
                        continue;
                    }
                    String token = i++ + ": " + trim;
                    token += " length:" + trim.length();
                    if (lexemCheck.isMnemoIdentifier(trim)) {
                        token += " - command";
                    } else if (lexemCheck.isTextConstant(trim)) {
                        token += " - text constant";
                    } else if (lexemCheck.isKeyWord(trim)) {
                        token += " - key word";
                    } else if (lexemCheck.isDataRegister(trim)) {
                        token += " - register";
                    } else if (lexemCheck.isConstant(trim)) {
                        token += " - constant";
                    } else if (lexemCheck.isUserIdentifier(trim)) {
                        token += " - identifier";
                    } else if (lexemCheck.isDelimiter(trim)) {
                        token += " - one delimiter";
                    }
                    if(lexemCheck.flagTextConstantStart && lexemCheck.flagTextConstantEnd){
                        i--;
                        System.out.println(i + ": " + lexemCheck.stringCash + " length:" + lexemCheck.stringCash.length() + " - text constant");
                    }else if(lexemCheck.flagTextConstantStart && !lexemCheck.flagTextConstantEnd) {
                        i--;
                    }else{
                        System.out.println(token);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
