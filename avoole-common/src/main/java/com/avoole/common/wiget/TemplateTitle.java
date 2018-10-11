package com.avoole.common.wiget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avoole.common.R;
import com.avoole.common.util.DrawableUtil;
import com.bumptech.glide.Glide;

/**
 * 标题控件
 */
public class TemplateTitle extends RelativeLayout {

    private String subTitleText;
    private String titleText;
    private int titleAllow;
    private boolean canBack;
    private String backText;
    private String moreText;
    private int backImg;
    private int moreImg;
    private TextView tvMore;
    private boolean line;

    private
    @ColorInt
    int themeColor;

    public TemplateTitle(Context context) {
        this(context, null);
    }

    public TemplateTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TemplateTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int moreTextColor;
        float moreTextTextSize;
        int backTextColor;
        LayoutInflater.from(context).inflate(R.layout.title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TemplateTitle, 0, 0);
        try {
            final Resources res = getResources();
            titleText = ta.getString(R.styleable.TemplateTitle_titleText);
            subTitleText = ta.getString(R.styleable.TemplateTitle_subTitleText);
            titleAllow = ta.getResourceId(R.styleable.TemplateTitle_titleAllow, R.drawable.ic_title_allow);
            line = ta.getBoolean(R.styleable.TemplateTitle_line, true);
            canBack = ta.getBoolean(R.styleable.TemplateTitle_canBack, false);
            backImg = ta.getResourceId(R.styleable.TemplateTitle_backImg, R.drawable.ic_back);
            backText = ta.getString(R.styleable.TemplateTitle_backText);
            moreImg = ta.getResourceId(R.styleable.TemplateTitle_moreImg, 0);
            moreText = ta.getString(R.styleable.TemplateTitle_moreText);
            moreTextColor = ta.getColor(R.styleable.TemplateTitle_moreTextColor, 0);
            moreTextTextSize = ta.getDimensionPixelSize(R.styleable.TemplateTitle_moreTextSize, 0);
            backTextColor = ta.getColor(R.styleable.TemplateTitle_backTextColor, 0);
            setUpView();
        } finally {
            ta.recycle();
        }
        if (getBackground() == null) {
            setBackgroundColor(0xffffffff);
        }
        themeColor = 0xff333333;
        setThemeColor(themeColor);
        if (moreTextColor != 0) {
            setMoreTextColor(moreTextColor);
        }
        if (moreTextTextSize != 0) {
            setMoreTextSize(moreTextTextSize);
        }
        if (backTextColor != 0) {
            setBackTextColor(backTextColor);
        }
    }

    private void setUpView() {
        TextView tvTitle = (TextView) findViewById(R.id.nav_title);
        tvTitle.setText(titleText);
        if (titleAllow > 0) {
            tvTitle.setCompoundDrawables(null, null, getContext().getResources().getDrawable(titleAllow), null);
        }

        LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
        //backBtn.setVisibility(canBack ? VISIBLE : INVISIBLE);
        TextView tvBack = (TextView) findViewById(R.id.txt_back);
        tvBack.setText(backText);
        if (canBack) {
            backBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        }

        if (moreImg != 0) {
            ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
            moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
        }

        canBack(canBack);
        if (backImg != 0) {
            ImageView backImgView = (ImageView) findViewById(R.id.img_back);
            backImgView.setImageDrawable(getContext().getResources().getDrawable(backImg));
        }
        tvMore = (TextView) findViewById(R.id.txt_more);
        tvMore.setText(moreText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 返回按钮
     *
     * @param drawable 设置返回按钮
     */
    public void setBackImg(Drawable drawable) {
        ImageView backImgView = (ImageView) findViewById(R.id.img_back);
        backImgView.setImageDrawable(drawable);
    }

    public Drawable getBackImg() {
        ImageView backImgView = (ImageView) findViewById(R.id.img_back);
        return backImgView.getDrawable();
    }

    public void setThemeColor(@ColorInt int color) {
        ColorStateList colorStateList = ColorStateList.valueOf(color);

        if (backImg != 0) {
            ImageView backImgView = (ImageView) findViewById(R.id.img_back);
            backImgView.setImageDrawable(DrawableUtil.tintDrawable(getContext().getResources().getDrawable(backImg).mutate(), colorStateList));
        }

        TextView tvTitleBack = (TextView) findViewById(R.id.txt_back);
        tvTitleBack.setTextColor(color);

        TextView tvTitle = (TextView) findViewById(R.id.nav_title);
        tvTitle.setTextColor(color);

        TextView tvSubTitle = (TextView) findViewById(R.id.titleSub);
        tvSubTitle.setTextColor(color);

        if (moreImg != 0) {
            ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
            moreImgView.setImageDrawable(DrawableUtil.tintDrawable(getContext().getResources().getDrawable(moreImg).mutate(), colorStateList));
        }

        TextView txt_more = (TextView) findViewById(R.id.txt_more);
        txt_more.setTextColor(color);

        if (titleAllow > 0) {
            tvTitle.setCompoundDrawables(null, null, DrawableUtil.tintDrawable(getContext().getResources().getDrawable(titleAllow).mutate(), colorStateList), null);
        }
    }

    public void setBackText(String backText) {
        this.backText = backText;
        TextView tvBack = (TextView) findViewById(R.id.txt_back);
        tvBack.setText(backText);
    }

    public void setBackTextColor(int color) {
        TextView tvBack = findViewById(R.id.txt_back);
        tvBack.setTextColor(color);
    }

    public TemplateTitle canBack(boolean canBack) {
        this.canBack = canBack;
        ImageView backImgView = (ImageView) findViewById(R.id.img_back);
        backImgView.setVisibility(canBack ? VISIBLE : GONE);
        return this;
    }

    /**
     * 标题控件
     *
     * @param titleText 设置标题文案
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
        TextView tvTitle = (TextView) findViewById(R.id.nav_title);
        tvTitle.setText(titleText);
    }

    public void setTitleAlpha(float alpha) {
        TextView tvTitle = (TextView) findViewById(R.id.nav_title);
        tvTitle.setAlpha(alpha);
    }

    /**
     * 标题控件
     *
     * @param设置标题文案
     */
    public void setTitleImage(String uri) {
        findViewById(R.id.nav_title).setVisibility(View.GONE);
        findViewById(R.id.titleSub).setVisibility(View.GONE);
        ImageView img = findViewById(R.id.nav_img);
        img.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(uri).asBitmap().placeholder(R.color.color_placeholder).into(img);
    }

    /**
     * 子标题控件
     *
     * @param subTitleText 设置子标题文案
     */
    public void setSubTitleText(String subTitleText) {
        this.subTitleText = subTitleText;
        TextView tvSubTitle = (TextView) findViewById(R.id.titleSub);
        tvSubTitle.setText(subTitleText);
        tvSubTitle.setVisibility(TextUtils.isEmpty(subTitleText) ? View.GONE : View.VISIBLE);
    }

    /**
     * 标题更多按钮
     *
     * @param img 设置更多按钮
     */
    public void setMoreImg(int img) {
        moreImg = img;
        ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
        if (img == 0) {
            moreImgView.setImageDrawable(null);
        } else {
            moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
        }
    }

    public void setMoreImg(Drawable drawable) {
        ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
        moreImgView.setImageDrawable(drawable);
    }

    public Drawable getMoreImg() {
        ImageView backImgView = (ImageView) findViewById(R.id.img_more);
        return backImgView.getDrawable();
    }

    public void setEnabledMore(boolean enabled) {
        findViewById(R.id.btn_more).setEnabled(enabled);
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreImgAction(OnClickListener listener) {
        LinearLayout moreRoot = findViewById(R.id.btn_more);
        moreRoot.setOnClickListener(listener);
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreTextAction(OnClickListener listener) {
        LinearLayout moreRoot = findViewById(R.id.btn_more);
        moreRoot.setOnClickListener(listener);
    }

    /**
     * 设置更多文字内容
     *
     * @param text 更多文本
     */
    public void setMoreText(String text) {
        tvMore.setText(text);
    }

    /**
     * 设置更多文字颜色
     *
     * @param color 颜色
     */
    public void setMoreTextColor(@ColorInt int color) {
        tvMore.setTextColor(color);
    }

    /**
     * 设置更多文字大小
     *
     * @param size 大小 unit/px
     */
    public void setMoreTextSize(@Dimension float size) {
        tvMore.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * 设置返回按钮事件
     *
     * @param listener 事件监听
     */
    public void setBackListener(OnClickListener listener) {
        LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
        backBtn.setOnClickListener(listener);
    }

    public void setCanBack(boolean canBack) {
        this.canBack = canBack;
        ImageView backImgView = (ImageView) findViewById(R.id.img_back);
        backImgView.setVisibility(canBack ? VISIBLE : GONE);
        TextView tv = (TextView) findViewById(R.id.txt_back);
        tv.setVisibility(GONE);
    }

    @Override
    public void setBackgroundColor(int color) {
        View view = findViewById(R.id.tab_bar);
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }

    @Override
    public void setBackground(Drawable background) {
        View view = findViewById(R.id.tab_bar);
        if (view != null)
            view.setBackground(background);
    }

    @Override
    public Drawable getBackground() {
        View view = findViewById(R.id.tab_bar);
        if (view != null)
           return view.getBackground();
        return null;
    }
}
