package com.rescueandroid.utils.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rescueandroid.utils.SystemUtil;

import java.util.ArrayList;


public class RadioSelectorView extends LinearLayout {
	public static final int KEEP_COLOR = -3243;
	public static int START_ID = 3829;

	private Context context;
	private DisplayMetrics displayMetrics;

	private int buttonCount;

	private int strokeColor;
	private int strokeWidth;

	private int corner;

	private int originColor;
	private int onPressedColor;

	private int textOriginColor;
	private int textOnPressedColor;

	private int fontsize = 16;

	public int getFontsize() {
		return fontsize;
	}

	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
	}

	private ArrayList<Button> buttonArr;
	private int buttonHeight;
	private int buttonWidth = 100;

	public int getButtonWidth() {
		return buttonWidth;
	}

	public void setButtonWidth(int buttonWidth) {

		this.buttonWidth = buttonWidth;
	}

	private int position = 0;
	private OnChangeListener mOnChangeListener;

//	 @SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			mOnChangeListener.onChange();
		}
	};

	public RadioSelectorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		if (attrs == null) {
			setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
		}
		setOrientation(HORIZONTAL);
		setBackgroundColor(getResources().getColor(android.R.color.transparent));
		buttonArr = new ArrayList<Button>();
		// Default values can change it by init***() methods;
		int RightViewGrey = Color.parseColor("#FF0000");// δѡ������(������)
		int BackButtonGreen = Color.parseColor("#FF0000");// ѡ�б���(������)
		int White = getResources().getColor(android.R.color.white);
		strokeColor = RightViewGrey;
		originColor = White;
		onPressedColor = BackButtonGreen;
		textOriginColor = strokeColor;
		textOnPressedColor = originColor;
		strokeWidth = 1;
		corner = 5;
		buttonHeight = -1;

		// Get ScreenMetrics and put it into displayMetrics
		// Translating dp-unit to px-unit depends on it
		displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
	}

	// like setOnClickListener()
	public void setOnChangeListener(OnChangeListener mOnChangeListener) {
		this.mOnChangeListener = mOnChangeListener;
	}

	public void init(int buttonCount, String[] str) {

		int width = SystemUtil.getScreenWidth(this.getContext());
		int bw = width * 80 / 100 / buttonCount;
		this.buttonWidth = bw;

		if (str.length != buttonCount)
			try {
				throw new Exception();
			} catch (Exception e) {
				Log.e("", "The Number of buttons can't match str[].length");
			}
		this.buttonCount = buttonCount;

		for (int i = 0; i < buttonCount; i++) {
			final RadioButton temp = new RadioButton(context);
			temp.setTextSize(fontsize);
			temp.setWidth(this.buttonWidth);
			temp.setPadding(0, 0, 0, 0);
			buttonArr.add(temp);
			temp.setId(START_ID + i);
			temp.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					position = temp.getId() - START_ID;
					handler.sendEmptyMessage(0);
					draw();
				}
			});
			temp.setText(str[i]);
			addView(temp);
		}
	}

	public void init(int buttonCount) {
		String[] str = new String[buttonCount];
		for (int i = 0; i < buttonCount; i++) {
			str[i] = "Button" + i;
		}
		init(buttonCount, str);
	}

	// init height of button
	public void initButtonHeight(int dp) {

		buttonHeight = transDpToPx(dp);
		Log.e("ddddddddddddddddddddddddddddddddddddddd", "dp=" + dp + ",px="
				+ buttonHeight);
		for (int i = 0; i < buttonCount; i++) {
			buttonArr.get(i).setHeight(buttonHeight);
			// buttonArr.get(i).setHeight(45);
		}
	}

	// Initial text color(If you only want to change one of two colors,
	// set param RadioSelectorView.keepColor to keep it)
	public void initTextColor(int textOriginColor, int textOnPressedColor) {
		if (textOriginColor != KEEP_COLOR)
			this.textOriginColor = textOriginColor;
		if (textOnPressedColor != KEEP_COLOR)
			this.textOnPressedColor = textOnPressedColor;
	}

	public void initCorner(int cornerInDp) {
		this.corner = cornerInDp;
	}

	public void initStrokeColor(int strokeColor) {
		this.strokeColor = strokeColor;
	}

	public void initStrokeWidth(int strokeWidthInDp) {
		this.strokeWidth = strokeWidthInDp;
	}

	public void initBackgroundColor(int originColor, int onPressedColor) {
		if (originColor != KEEP_COLOR)
			this.originColor = originColor;
		if (onPressedColor != KEEP_COLOR)
			this.onPressedColor = onPressedColor;
	}

	@SuppressWarnings("deprecation")
	public void draw() {

		float[] radiiLeft = { transDpToPx(corner), transDpToPx(corner), 0, 0,
				0, 0, transDpToPx(corner), transDpToPx(corner) };
		float[] radiiRight = { 0, 0, transDpToPx(corner), transDpToPx(corner),
				transDpToPx(corner), transDpToPx(corner), 0, 0 };

		for (int i = 0; i < buttonCount; i++) {

			GradientDrawable temp = new GradientDrawable();
			temp.setStroke(transDpToPx(strokeWidth), strokeColor);
			temp.setColor(originColor);
			if (i == position) {

				buttonArr.get(i).setTextColor(textOnPressedColor);

				temp.setColor(onPressedColor);
				if (i == 0) {
					temp.setCornerRadii(radiiLeft);
					buttonArr.get(i).setBackgroundDrawable(temp);
				} else if (i == buttonCount - 1) {
					temp.setCornerRadii(radiiRight);
					buttonArr.get(i).setBackgroundDrawable(temp);
				} else {
					buttonArr.get(i).setBackgroundDrawable(temp);
				}
				continue;
			} else {

				buttonArr.get(i).setTextColor(textOriginColor);
				if (i == 0) {
					temp.setCornerRadii(radiiLeft);
					buttonArr.get(i).setBackgroundDrawable(temp);
					continue;
				} else if (i == buttonCount - 1) {
					temp.setCornerRadii(radiiRight);
					buttonArr.get(i).setBackgroundDrawable(temp);
					continue;
				} else
					buttonArr.get(i).setBackgroundDrawable(temp);
			}
		}
	}

	public int getButtonCount() {
		return buttonCount;
	}

	public int getPosition() {
		return position;
	}

	private class RadioButton extends Button {
		public RadioButton(Context context) {
			super(context);
			LayoutParams params = new LayoutParams(0,
					LayoutParams.MATCH_PARENT);
			params.weight = 1;
			setLayoutParams(params);
		}
	}

	private int transDpToPx(int dp) {
		return (displayMetrics.densityDpi / 160) * dp;
	}

	public void setPosition(int target) {
		position = target;
		handler.sendEmptyMessage(0);
		draw();
	}

	public interface OnChangeListener {
		void onChange();
	}
}
