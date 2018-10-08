package com.avoole.im.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.avoole.common.wiget.TemplateTitle;
import com.avoole.im.R;
import com.avoole.im.viewfeatures.ChatView;

/**
 * Created by wuyu on 18/9/29.
 */

public class ChatActivity extends Activity implements ChatView {

    private TemplateTitle templateTitle;
    private RecyclerView recyclerView;
    private ChatInput chatInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_activity_chat);
        chatInput = findViewById(R.id.input_panel);
        chatInput.setChatView(this);
    }

    @Override
    public void onInputText(CharSequence changedText, int start, int end) {

    }

    @Override
    public void onInputModeChange(ChatInput.InputMode mode) {

    }

    @Override
    public void clearAllMessage() {

    }

    @Override
    public void sendImage() {

    }

    @Override
    public void sendPhoto() {

    }

    @Override
    public void sendText() {

    }

    @Override
    public void sendFile() {

    }

    @Override
    public void startSendVoice() {

    }

    @Override
    public void endSendVoice() {

    }

    @Override
    public void sendLocation() {

    }

    @Override
    public void sendVideo(String fileName) {

    }

    @Override
    public void cancelSendVoice() {

    }

    @Override
    public void sending() {

    }

    @Override
    public void videoAction() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void imageGalleryAction() {

    }

    @Override
    public void shearPost(Object post) {

    }
}
