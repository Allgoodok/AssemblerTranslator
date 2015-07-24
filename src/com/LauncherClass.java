package com;

import javax.script.ScriptException;
import java.io.IOException;


public class LauncherClass {

    public static void main(String... args) throws IOException, ScriptException {
        AsmFirstSecondPassage processor = new AsmFirstSecondPassage();
        processor.analyze("true");

    }


}
