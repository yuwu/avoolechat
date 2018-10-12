package com.avoole.im.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avoole.common.Applog;
import com.avoole.common.util.Constants;
import com.avoole.common.util.SoftKeyboardUtils;
import com.avoole.im.R;
import com.avoole.im.viewfeatures.ChatView;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天界面输入控件
 */
public class ChatInput extends RelativeLayout implements TextWatcher,View.OnClickListener {

    private static final String TAG = "ChatInput";

    private ImageButton btnAdd, btnVoice, btnKeyboard, btnEmotion, btnLoaction;
    private TextView btnSend;
    private EditText chatInputText;
    private boolean isSendVisible,isHoldVoiceBtn;
    private InputMode inputMode = InputMode.NONE;
    private ChatView chatView;
    private LinearLayout morePanel,textPanel;
    private TextView voicePanel;
    private LinearLayout chatPanels;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 100;

    private FrameLayout frame;

    private int fixMenuCount = 1;

    public ChatInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView();
    }

    private void initView(){
        frame = findViewById(R.id.frame);

        textPanel = (LinearLayout) findViewById(R.id.text_panel);
        btnAdd = (ImageButton) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        btnSend = (TextView) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
        btnVoice = (ImageButton) findViewById(R.id.btn_voice);
        btnVoice.setOnClickListener(this);
        btnEmotion = (ImageButton) findViewById(R.id.btnEmoticon);
        btnEmotion.setOnClickListener(this);
        morePanel = (LinearLayout) findViewById(R.id.morePanel);
        LinearLayout BtnImage = (LinearLayout) findViewById(R.id.btn_photo);
        BtnImage.setOnClickListener(this);
        LinearLayout BtnPhoto = (LinearLayout) findViewById(R.id.btn_image);
        BtnPhoto.setOnClickListener(this);
        LinearLayout btnVideo = (LinearLayout) findViewById(R.id.btn_video);
        btnVideo.setOnClickListener(this);
        LinearLayout btnFile = (LinearLayout) findViewById(R.id.btn_file);
        btnFile.setOnClickListener(this);
        LinearLayout btnLocation = (LinearLayout) findViewById(R.id.btn_location);
        btnLocation.setOnClickListener(this);
        setSendBtn();
        btnKeyboard = (ImageButton) findViewById(R.id.btn_keyboard);
        btnKeyboard.setOnClickListener(this);
        voicePanel = (TextView) findViewById(R.id.voice_panel);
        voicePanel.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isHoldVoiceBtn = true;
                        updateVoiceView();
                        break;
                    case MotionEvent.ACTION_UP:
                        isHoldVoiceBtn = false;
                        updateVoiceView();
                        break;
                }
                return true;
            }
        });
        chatInputText = (EditText) findViewById(R.id.input);
        chatInputText.addTextChangedListener(this);
        chatInputText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    updateView(InputMode.TEXT);
                }
            }
        });
        chatInputText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(inputMode == InputMode.TEXT && event.getAction() == MotionEvent.ACTION_UP){
                    chatView.onInputModeChangeAlfter(inputMode);
                }
                return false;
            }
        });
        chatInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    chatView.sendText();
                }
                return false;
            }
        });
        isSendVisible = chatInputText.getText().length() != 0;
        chatPanels = findViewById(R.id.panels);

        frame.setVisibility(GONE);
        btnSend.setVisibility(View.GONE);
        morePanel.setVisibility(View.GONE);
        chatPanels.setVisibility(View.GONE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int keyboardHeight = SoftKeyboardUtils.getSoftKeyboardHeight(getActivity());
        Applog.d("onMeasure:" + keyboardHeight + " heightSize:" + heightSize);
    }

    private void updateView(InputMode mode){
        if (mode == inputMode) return;
        final InputMode currentState = inputMode;
        chatView.onInputModeChangeBefore(currentState);
        leavingCurrentState(currentState);
        switch (inputMode = mode){
            case VOICE:
                voicePanel.setVisibility(VISIBLE);
                textPanel.setVisibility(GONE);
                btnVoice.setVisibility(GONE);
                btnKeyboard.setVisibility(VISIBLE);
                chatView.onInputModeChangeAlfter(inputMode);
                break;
            case TEXT:
                SoftKeyboardUtils.showSoftInput(getActivity(), chatInputText);
                if(currentState == InputMode.NONE || currentState == InputMode.VOICE){
                    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|
                            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    frame.setVisibility(GONE);
                    chatPanels.setVisibility(GONE);
                    morePanel.setVisibility(GONE);
                    chatView.onInputModeChangeAlfter(inputMode);
                }else{
                    frame.setVisibility(VISIBLE);
                    chatPanels.setVisibility(GONE);
                    morePanel.setVisibility(GONE);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|
                                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                            frame.setVisibility(GONE);
                            chatPanels.setVisibility(GONE);
                            morePanel.setVisibility(GONE);
                            chatView.onInputModeChangeAlfter(inputMode);
                        }
                    }, 250);
                }
                break;
            case EMOTICON:
                //setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                updatePanelHeight(Constants.softKeyboardHeight);
                frame.setVisibility(VISIBLE);
                morePanel.setVisibility(GONE);
                chatPanels.setVisibility(VISIBLE);
                chatView.onInputModeChangeAlfter(inputMode);
                break;
            case MORE:
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                updatePanelHeight(Constants.softKeyboardHeight);
                frame.setVisibility(View.VISIBLE);
                chatPanels.setVisibility(GONE);
                morePanel.setVisibility(VISIBLE);
                chatView.onInputModeChangeAlfter(inputMode);
                break;
        }
    }

    private void leavingCurrentState(InputMode inputMode){
        switch (inputMode){
            case TEXT:
                SoftKeyboardUtils.hideSoftInput(getActivity());
                chatInputText.clearFocus();
                break;
            case MORE:
                frame.setVisibility(GONE);
                morePanel.setVisibility(GONE);
                break;
            case VOICE:
                voicePanel.setVisibility(GONE);
                textPanel.setVisibility(VISIBLE);
                btnVoice.setVisibility(VISIBLE);
                btnKeyboard.setVisibility(GONE);
                break;
            case EMOTICON:
                frame.setVisibility(GONE);
                chatPanels.setVisibility(GONE);
                break;
        }
    }

    private void updateVoiceView(){
        if (isHoldVoiceBtn){
            voicePanel.setText(getResources().getString(R.string.chat_release_send));
            voicePanel.setBackground(getResources().getDrawable(R.drawable.btn_voice_pressed));
            chatView.startSendVoice();
        }else{
            voicePanel.setText(getResources().getString(R.string.chat_press_talk));
            voicePanel.setBackground(getResources().getDrawable(R.drawable.btn_voice_normal));
            chatView.endSendVoice();
        }
    }

    /**
     * 关联聊天界面逻辑
     */
    public void setChatView(ChatView chatView){
        this.chatView = chatView;
    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * are about to be replaced by new text with length <code>after</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * have just replaced old text that had length <code>before</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isSendVisible = s!=null&&s.length()>0;
        setSendBtn();
        if (isSendVisible){
            chatView.sending();
        }
        CharSequence changedText = s.subSequence(start, start+count);
        chatView.onInputText(changedText, start, start+count);
    }

    /**
     * This method is called to notify you that, somewhere within
     * <code>s</code>, the text has been changed.
     * It is legitimate to make further changes to <code>s</code> from
     * this callback, but be careful not to getInstance yourself into an infinite
     * loop, because any changes you make will cause this method to be
     * called again recursively.
     * (You are not told where the change took place because other
     * afterTextChanged() methods may already have made other changes
     * and invalidated the offsets.  But if you need to know here,
     * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
     * to mark your place and then look up from here where the span
     * ended up.
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {

    }

    private void setSendBtn(){
        if (isSendVisible){
            btnAdd.setVisibility(GONE);
            btnSend.setVisibility(VISIBLE);
        }else{
            btnAdd.setVisibility(VISIBLE);
            btnSend.setVisibility(GONE);
        }
    }

    public void deleteText() {
        int keyCode = KeyEvent.KEYCODE_DEL;
        chatInputText.onKeyDown(keyCode, new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
        chatInputText.onKeyUp(keyCode, new KeyEvent(KeyEvent.ACTION_UP, keyCode));
    }

    public void requestPressText() {
        int keyCode = KeyEvent.KEYCODE_UNKNOWN;
        chatInputText.onKeyDown(keyCode, new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
        chatInputText.onKeyUp(keyCode, new KeyEvent(KeyEvent.ACTION_UP, keyCode));
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Activity activity = (Activity) getContext();
        int id = v.getId();
        if (id == R.id.btn_send){
            chatView.sendText();
        }
        if (id == R.id.btn_add){
            updateView(inputMode == InputMode.MORE ? InputMode.TEXT : InputMode.MORE);
        }
        if (id == R.id.btn_photo){
            if(activity!=null && requestCamera(activity)){
                chatView.sendPhoto();
            }
        }
        if (id == R.id.btn_image){
            if(activity!=null && requestStorage(activity)){
                chatView.sendImage();
            }
        }
        if (id == R.id.btn_voice){
            if(activity!=null && requestAudio(activity)){
                updateView(InputMode.VOICE);
            }
        }
        if (id == R.id.btn_keyboard){
            updateView(InputMode.TEXT);
        }
        if (id == R.id.btn_video){
            if (getContext() instanceof FragmentActivity){
                FragmentActivity fragmentActivity = (FragmentActivity) getContext();
                if (requestVideo(fragmentActivity)){
                    if (requestRtmp()) {
                        chatView.videoAction();
                    }else {
                        Toast.makeText(activity, "系统版本太低", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
        if (id == R.id.btnEmoticon){
            updateView(inputMode == InputMode.EMOTICON? InputMode.TEXT: InputMode.EMOTICON);
        }
        if (id == R.id.btn_file){
            chatView.sendFile();
        }
        if(id == R.id.btn_location){
            chatView.sendLocation();
        }
    }

    /**
     * 获取输入框文字
     */
    public Editable getText(){
        return chatInputText.getText();
    }

    public int getSelectionStart(){
        return chatInputText.getSelectionStart();
    }

    public int getSelectionEnd(){
        return chatInputText.getSelectionEnd();
    }

    /**
     * 设置输入框文字
     */
    public void setText(String text){
        chatInputText.setText(text);
    }

    /**
     * 设置输入模式
     */
    public void setInputMode(InputMode mode){
        updateView(mode);
    }

    public enum InputMode{
        TEXT,
        VOICE,
        EMOTICON,
        MORE,
        VIDEO,
        NONE,
    }

    private boolean requestRtmp() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private boolean requestVideo(Activity activity){
        if (afterM()){
            final List<String> permissionsList = new ArrayList<>();
            if ((activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.CAMERA);
            if ((activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.RECORD_AUDIO);
            if (permissionsList.size() != 0){
                activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
            int hasPermission = activity.checkSelfPermission(Manifest.permission.CAMERA);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean requestCamera(Activity activity){
        if (afterM()){
            int hasPermission = activity.checkSelfPermission(Manifest.permission.CAMERA);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean requestAudio(Activity activity){
        if (afterM()){
            int hasPermission = activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean requestStorage(Activity activity){
        if (afterM()){
            int hasPermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean afterM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private View rootView;

    protected void inflateView() {
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.im_chat_input, this);
        initView();
        final Context context = getContext();
        if (context instanceof Activity) {
            rootView = ((Activity) context).getWindow().getDecorView().getRootView();
        } else {
            rootView = this;
        }
        detectSoftInput();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private Activity getActivity(){
        return (Activity)getContext();
    }

    private void setSoftInputMode(int softInputMode) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            WindowManager.LayoutParams params = activity.getWindow().getAttributes();
            if (params.softInputMode != softInputMode) {
                params.softInputMode = softInputMode;
                activity.getWindow().setAttributes(params);
            }
        }
    }

    private void updatePanelHeight(View more, int keyboardHeight) {
        ViewGroup.LayoutParams params = more.getLayoutParams();
        if (params != null && params.height != keyboardHeight) {
            params.height = keyboardHeight;
            more.setLayoutParams(params);
        }
    }

    private void updatePanelHeight(int keyboardHeight) {
        updatePanelHeight(morePanel, keyboardHeight);
        updatePanelHeight(chatPanels, keyboardHeight);
        updatePanelHeight(frame, keyboardHeight);
    }

    private void detectSoftInput() {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int keyboardHeight = SoftKeyboardUtils.getSoftKeyboardHeight(getActivity());
                Applog.d("keyboardHeight:" + keyboardHeight);
                if(keyboardHeight > 0){
                    Constants.setSoftKeyboardHeight(keyboardHeight);
                    //updatePanelHeight(morePanel, keyboardHeight);
                    //updatePanelHeight(chatPanels, keyboardHeight);
                    updatePanelHeight(keyboardHeight);
                }
            }
        });
    }
}
