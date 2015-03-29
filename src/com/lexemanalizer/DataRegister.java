package com.lexemanalizer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vlad on 29.03.2015.
 */
public class DataRegister extends Lexem {

    public DataRegister() {

        setData32Register();

    }

    private ArrayList<String> data32Register = new ArrayList<String>();

    public void setData32Register(){

        data32Register.add("EAX");
        data32Register.add("ECX");
        data32Register.add("EDX");
        data32Register.add("EBS");
        data32Register.add("EBP");
        data32Register.add("ESP");
        data32Register.add("ESI");
        data32Register.add("EDI");
    }

    public boolean isDataRegister(String toCheck){

        Iterator iterator = data32Register.iterator();

        while(iterator.hasNext()){

            if(toCheck == iterator.next()){

                setTYPE("32-bit data register identifier");
                return true;
            }

        }

        return false;

    }

}
