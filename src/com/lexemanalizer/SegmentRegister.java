package com.lexemanalizer;

/**
 * Created by Vlad on 19.04.2015.
 */
public class SegmentRegister extends Lexem {
    public SegmentRegister(int ID, String NAME, int LEXEMLENGTH, String TYPE){

        setLEXEMLENGTH(LEXEMLENGTH);
        setNAME(NAME);
        setID(ID);
        setTYPE(TYPE);

    }
}
