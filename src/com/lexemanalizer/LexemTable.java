package com.lexemanalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Vlad on 19.04.2015.
 */
public class LexemTable {
    public static List<Lexem> lexemTableList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        LexemAnalizer lexemAnalizer = new LexemAnalizer();

        lexemAnalizer.LexemTableFill();

        for(Lexem o : lexemTableList) {
            System.out.print(o.getID() + " " );
            System.out.print(o.getLEXEMLENGTH() + " ");
            System.out.print(o.getNAME() + " ");
            System.out.println(o.getTYPE());

        }
        System.out.println();
    }
}
