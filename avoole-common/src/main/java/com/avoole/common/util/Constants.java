package com.avoole.common.util;

public class Constants {

    public static int softKeyboardHeight = 700;

    public static void setSoftKeyboardHeight(int softKeyboardHeight){
        if(softKeyboardHeight <= 0) return;
        Constants.softKeyboardHeight = softKeyboardHeight;
    }
}
