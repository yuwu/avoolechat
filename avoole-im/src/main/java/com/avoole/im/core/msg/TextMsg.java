package com.avoole.im.core.msg;

public class TextMsg extends Msg {

    public TextMsg() {
        this("");
    }

    public TextMsg(String text) {
        super();
        this.payload = text;
    }

    public static TextMsg newMsg(String text){
        return new TextMsg(text);
    }
}
