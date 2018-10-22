package com.avoole.im.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avoole.common.wiget.StateButton;
import com.avoole.common.wiget.TemplateTitle;
import com.avoole.im.R;
import com.avoole.im.viewfeatures.ChatView;

/**
 * Created by wuyu on 18/9/29.
 */

public class ChatActivity extends Activity implements ChatView {

    private TemplateTitle templateTitle;

    public RecyclerView chatList;
    public ImageView emotionVoice;
    public EditText editText;
    public TextView voiceText;
    public ImageView emotionButton;
    public ImageView emotionAdd;
    public StateButton emotionSend;
    public RelativeLayout emotionLayout;

    private EmotionInputDetector mDetector;

    private ChatInput chatInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_activity_chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();
        //init2();
    }

    private void init(){
        chatInput = findViewById(R.id.input_panel);
        chatInput.setChatView(this);

        chatList = findViewById(R.id.list);

        chatList.setLayoutManager(new LinearLayoutManager(this));
        chatList.setAdapter(new ChatAdapter());

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

    class ChatAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_left, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            return new ChatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ChatViewHolder viewHolder = (ChatViewHolder)holder;
            viewHolder.text.setText("position:" + position);
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public ChatViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }

    @Override
    public void onInputText(CharSequence changedText, int start, int end) {
    }

    @Override
    public void onInputModeChangeBefore(ChatInput.InputMode oldMode) {
        RecyclerView contentView = chatList;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) contentView.getLayoutParams();
        params.height = contentView.getHeight();
        params.weight = 0f;
        contentView.setLayoutParams(params);
    }

    @Override
    public void onInputModeChangeAlfter(ChatInput.InputMode newMode) {
        final RecyclerView contentView = chatList;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) contentView.getLayoutParams();
        params.weight = 1f;
        contentView.setLayoutParams(params);

        RecyclerView.Adapter adapter = contentView.getAdapter();
        if(adapter != null && adapter.getItemCount() > 0){
            final int lastItemPosition = adapter.getItemCount()-1;
            contentView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    contentView.scrollToPosition(lastItemPosition);
                }
            }, 200);
        }
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
