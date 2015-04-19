package com.lexemanalizer;

import javax.jws.soap.SOAPBinding;
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


    public void LexemTableFill() {

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
                    LexemTable lexemTable = new LexemTable();
                    String trim = tokenizer.nextToken().trim();
                    if (trim.isEmpty()) {
                        continue;
                    }
                    String token = i++ + ": " + trim;
                    token += " length:" + trim.length();
                    if (lexemCheck.isMnemoIdentifier(trim)) {
                        MnemoIdentifier mnemoIdentifier = new MnemoIdentifier(trim.length(), trim, i, "command");
                        lexemTable.lexemTableList.add(mnemoIdentifier);
                        token += " - command";
                    } else if (lexemCheck.isTextConstant(trim)) {
                        TextConstant textConstant = new TextConstant(trim.length(), trim, i, "text constant");
                        lexemTable.lexemTableList.add(textConstant);
                        token += " - text constant";
                    } else if (lexemCheck.isKeyWord(trim)) {
                        KeyWord keyWord = new KeyWord(trim.length(), trim, i, "key word");
                        lexemTable.lexemTableList.add(keyWord);
                        token += " - key word";
                    } else if (lexemCheck.isDataRegister8(trim)) {
                        DataRegister8 dataRegister8 = new DataRegister8(trim.length(), trim, i, "8-bit register");
                        lexemTable.lexemTableList.add(dataRegister8);
                        token += " - 8-bit register";
                    }else if (lexemCheck.isDataRegister32(trim)){
                        DataRegister32 dataRegister32 = new DataRegister32(trim.length(), trim, i, "32-bit register");
                        lexemTable.lexemTableList.add(dataRegister32);
                        token += " - 32-bit register";
                    } else if (lexemCheck.isConstant(trim)) {
                        Constant constant = new Constant(trim.length(), trim, i, "constant");
                        lexemTable.lexemTableList.add(constant);
                        token += " - constant";
                    }else if (lexemCheck.isSegmentRegister(trim)){
                        SegmentRegister segmentRegister = new SegmentRegister(trim.length(), trim, i, "segment register");
                        lexemTable.lexemTableList.add(segmentRegister);
                        token += "- segment register";
                    } else if (lexemCheck.isUserIdentifier(trim)) {
                        UserIdetifier userIdetifier = new UserIdetifier(trim.length(), trim, i, "user identifier");
                        lexemTable.lexemTableList.add(userIdetifier);
                        token += " - identifier";
                    } else if (lexemCheck.isDelimiter(trim)) {
                        Delimiter delimiter = new Delimiter(trim.length(), trim, i, "delimiter");
                        lexemTable.lexemTableList.add(delimiter);
                        token += " - one delimiter";
                    }else{
                        System.out.println("Error in text code!");
                    }



                    if(lexemCheck.flagTextConstantStart && lexemCheck.flagTextConstantEnd){
                        i--;
                        TextConstant textConstant = new TextConstant(trim.length(), trim, i, "text constant");
                        lexemTable.lexemTableList.add(textConstant);
                        //System.out.println(i + ": " + lexemCheck.stringCash + " length:" + lexemCheck.stringCash.length() + " - text constant");
                    }else if(lexemCheck.flagTextConstantStart && !lexemCheck.flagTextConstantEnd) {
                        i--;
                    }else{
                        //System.out.println(token);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
