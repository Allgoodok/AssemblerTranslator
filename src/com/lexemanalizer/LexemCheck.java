package com.lexemanalizer;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Vlad on 29.03.2015.
 */
public class LexemCheck {

    public String stringCash;
    public Boolean flagTextConstantStart;
    public Boolean flagTextConstantEnd;

    private List<String> mnemoCommands = new CopyOnWriteArrayList<>();
    private List<String> data32Register = new CopyOnWriteArrayList<>();
    private List<String> data8Register = new CopyOnWriteArrayList<>();
    private List<String> segmentRegister = new CopyOnWriteArrayList<>();
    private List<String> keyWordList  = new CopyOnWriteArrayList<>();

    public void setMnemoCommands(){

        mnemoCommands.add("NOP");
        mnemoCommands.add("NEG");
        mnemoCommands.add("PUSH");
        mnemoCommands.add("MOV");
        mnemoCommands.add("TEST");
        mnemoCommands.add("JLE");
        mnemoCommands.add("JMP");
        mnemoCommands.add("GOADDR");

    }


    public void setData8Register(){

        data8Register.add("AH");
        data8Register.add("AL");
        data8Register.add("CH");
        data8Register.add("CL");
        data8Register.add("DH");
        data8Register.add("DL");
        data8Register.add("BH");
        data8Register.add("BL");
        data8Register.add("SPL");
        data8Register.add("BPL");
        data8Register.add("SIL");
        data8Register.add("DIL");
    }

    public void setData32Register(){

        data32Register.add("EAX");
        data32Register.add("EBX");
        data32Register.add("ECX");
        data32Register.add("EDX");
        data32Register.add("EBS");
        data32Register.add("EBP");
        data32Register.add("ESP");
        data32Register.add("ESI");
        data32Register.add("EDI");
        data32Register.add("EDI");
    }


    //сегментные регистры, 8ричные, 32ричные сегменты, ключевые слова, машинные команды, делиметры
    public void  setKeyWordList() {

        keyWordList.add("END");
        keyWordList.add("ENDS");
        keyWordList.add("SEGMENT");
        keyWordList.add("ASSUME");
        keyWordList.add("DD");
        keyWordList.add("DW");
        keyWordList.add("DB");
        keyWordList.add("PTR");
        keyWordList.add("FAR");
        keyWordList.add("CS");
        keyWordList.add("SS");

    }


    public void setSegmentRegister() {

        segmentRegister.add("CS");
        segmentRegister.add("SS");
        segmentRegister.add("DS");

    }

    public void setupTheLists(){
        setMnemoCommands();
        setData8Register();
        setData32Register();
        setKeyWordList();
        setSegmentRegister();

    }

    public boolean isMnemoIdentifier(String toCheck) {

        Iterator iterator = mnemoCommands.iterator();



        while(iterator.hasNext()){
            if(toCheck.equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isDataRegister8(String toCheck) {

        Iterator iterator = data8Register.iterator();



        while(iterator.hasNext()){
            if(toCheck.equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isDataRegister32(String toCheck) {

        Iterator iterator = data32Register.iterator();



        while(iterator.hasNext()){
            if(toCheck.equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isSegmentRegister(String toCheck) {

        Iterator iterator = segmentRegister.iterator();



        while(iterator.hasNext()){
            if(toCheck.equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }



    public boolean isUserIdentifier(String toCheck) {

        if(isTextConstant(toCheck)){
            return false;
        }
        else if(isDataRegister8(toCheck)) {
            return false;
        }else if(isDataRegister32(toCheck)){
            return false;
        } else if(isKeyWord(toCheck)) {
            return false;
        }else if(isSegmentRegister(toCheck)){
            return false;
        }

        for (int i = 0; i <= toCheck.length(); i++) {

            if(toCheck.length() <= 8 &&(((int)toCheck.charAt(0) >= 65) && ((int)toCheck.charAt(0) <= 90) || ((int)toCheck.charAt(0) >= 97) && ((int)toCheck.charAt(0) <= 122)) && (((int)toCheck.charAt(i) >= 65) && ((int)toCheck.charAt(i) <= 90) || ((int)toCheck.charAt(i) >= 97) && ((int)toCheck.charAt(i) <= 122) || ((int)toCheck.charAt(i)>=48) && (int)toCheck.charAt(i)<=57)){
                return true;
            }else{
                return false;
            }

        }

        return false;

    }


    public boolean isTextConstant(String toCheck) {
        flagTextConstantStart = false;

            if(((int)toCheck.charAt(0) == 39 && (int)toCheck.charAt((toCheck.length()-1))== 39) || ((int)toCheck.charAt(0) == 34 && (int)toCheck.charAt((toCheck.length()-1))== 34) || (int)toCheck.charAt((toCheck.length()-1))== 39 || (int)toCheck.charAt((toCheck.length()-1))== 34){
                flagTextConstantStart = true;
                flagTextConstantEnd = true;
                stringCash += toCheck;
                return true;
            } else {
                if ((int) toCheck.charAt(0) == 39 || (int) toCheck.charAt(0) == 34) {
                    flagTextConstantStart = true;
                    flagTextConstantEnd = false;
                    stringCash = "";
                    stringCash += toCheck;
                    stringCash += " ";

                    return true;
                }
            }
        return false;
    }

    public boolean isKeyWord(String toCheck){


        Iterator iterator = keyWordList.iterator();




        while(iterator.hasNext()){
            if (toCheck.equals(iterator.next())) {
                return true;
            }
        }
        return false;

    }

    public boolean isConstant(String toCheck){
        if((int)toCheck.charAt(0) >= 48 && (int)toCheck.charAt(0) <= 57){
            return true;
        }
        return false;
    }

    public boolean isDelimiter(String toCheck){
        if (toCheck.length() == 1) {
            return true;
        }
        return false;
    }




}
