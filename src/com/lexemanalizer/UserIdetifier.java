package com.lexemanalizer;

/**
 * Created by Vlad on 29.03.2015.
 */
public class UserIdetifier extends Lexem {

    public UserIdetifier(){ setTYPE("User's identifier or not qualified"); }

    public boolean isUserIdentifier(String toCheck) {

        for (int i = 0; i <= toCheck.length(); i++) {

            if(((int)toCheck.charAt(i) >= 65) && ((int)toCheck.charAt(i) <= 90) || ((int)toCheck.charAt(i) >= 97) && ((int)toCheck.charAt(i) <= 122)){
                return true;
            }else{
                return false;
            }

        }

        return false;

    }
}
