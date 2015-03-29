package com.lexemanalizer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vlad on 29.03.2015.
 */
public class MnemoIdentifier extends Lexem {

    public MnemoIdentifier(){

        setTYPE("Mnemonic identifier of machine instruction");


        fillArrayList();

    }


    private ArrayList<String> mnemoCommands = new ArrayList<String>();

    private void fillArrayList(){

        mnemoCommands.add("NOP");
        mnemoCommands.add("NEG");
        mnemoCommands.add("PUSH");
        mnemoCommands.add("MOV");
        mnemoCommands.add("TEST");
        mnemoCommands.add("JLE");
        mnemoCommands.add("JMP");

    }

    public boolean isMnemoIdentifier(String toCheck) {

        Iterator iterator = mnemoCommands.iterator();

        while(iterator.hasNext()){
            if(toCheck == iterator.next()) {
                return true;
            }
        }
        return false;
    }
}
