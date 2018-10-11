package com.avoole.im.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avoole.common.wiget.StateButton;
import com.avoole.common.wiget.TemplateTitle;
import com.avoole.im.R;
import com.avoole.im.viewfeatures.ChatView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuyu on 18/9/29.
 */

public class ChatActivity extends Activity implements ChatView {

    private TemplateTitle templateTitle;
    private RecyclerView recyclerView;

    public RecyclerView chatList;
    public ImageView emotionVoice;
    public EditText editText;
    public TextView voiceText;
    public ImageView emotionButton;
    public ImageView emotionAdd;
    public StateButton emotionSend;
    public RelativeLayout emotionLayout;

    private EmotionInputDetector mDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_activity_chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();
        //init2();
    }

    private void init(){
        ChatInput input = findViewById(R.id.input_panel);
        input.setChatView(this);
    }

    private void init2(){
        chatList = findViewById(R.id.list);
        emotionVoice = findViewById(R.id.emotion_voice);
        editText = findViewById(R.id.edit_text);
        voiceText = findViewById(R.id.voice_text);
        emotionButton = findViewById(R.id.emotion_button);
        emotionAdd = findViewById(R.id.emotion_add);
        emotionSend = findViewById(R.id.emotion_send);
        emotionVoice = findViewById(R.id.emotion_voice);
        emotionLayout = findViewById(R.id.emotion_layout);

//        ChatInput chatInput = findViewById(R.id.input_panel);
//        if(chatInput != null){
//            chatInput.setChatView(this);
//            //AndroidBug5497Workaround.assistActivity(this);
//        }

        mDetector = EmotionInputDetector.with(this)
                .setChatView(this)
                .setEmotionView(emotionLayout)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .build();
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
