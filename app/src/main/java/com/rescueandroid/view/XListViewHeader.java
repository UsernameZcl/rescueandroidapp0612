/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.rescueandroid.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.example.rescueandroidapp.R;

public class XListViewHeader extends LinearLayout {
	private LinearLayout mContainer;
	private ImageView mArrowImageView;
	private ProgressBar mProgressBar;
	private TextView mHintTextView;
	private int mState = STATE_NORMAL;

	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;

	private final int ROTATE_ANIM_DURATION = 180;

	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;

	protected int mOtherViewHeight = 0;

	public XListViewHeader(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public XListViewHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	//
	// 闂備浇宕垫慨宕囩矆娴ｈ娅犲ù鐘差儐閸嬵亪鏌涢埄鍐檨闁抽攱妫冮弻娑㈠即閵娿儰绨诲銈庡墯閼村タew 婵犵數鍋為幐濠氭嚌妤ｅ喚鏁勯柛娑氳ˉ閿熶粙鏌涘┑鍫妴nner
	// zhubin
	// 2013-08-16
	//
	public void addHeadView(View view, int height) {
		mContainer.addView(view, LayoutParams.MATCH_PARENT, height);
		mOtherViewHeight += height;
		mContainer.getLayoutParams().height = mOtherViewHeight;
	}

	private void initView(Context context) {
		// 闂傚倸鍊风粈渚�箟閿涘嫭鏆滄俊銈呭暞瀹曟煡鏌涘畝锟戒汗闁跨喕妫勯埞鎴︽偐閸欏鎮欑紓浣哄У瀹�悂寮诲☉妯锋闁告鍋涚粻褰掓⒑閸濆嫷鍎忛柣顓炲�瀵濡搁妷銏☆瀲濠碘槅鍨甸崑鎰板礉椤旂⒈婀ら柕鍫濇閳锋劖绻涢崣澶岀煂婵″弶鍔欏顕�醇椤愶絾娅撻梻浣哄帶椤繘宕愬Δ鍛疅妞ゆ洩鎷烽埛鎴︽煟閻斿憡绶叉俊鑼帛閵囧嫰鏁冮敓鐣屽悑閻庤娲栭幖顐﹀煡婢舵劕顫块柣妯诲墯濡參姊绘担鍦菇闁告柨鐭傞獮鍐风粚鈩冪節瀵伴攱婢橀敓浠嬫煕婵犲倻绉洪柡浣割儔閺岋絾鎯旈敐搴㈡暞濠电偛鎳忓ú婊呭垝濞嗘劕绶為柟閭﹀幗濞呫垽姊虹捄銊ユ珢闁瑰嚖鎷�		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 190);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, 190);
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
		addView(mContainer, lp);
		setGravity(Gravity.BOTTOM);

		mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
		mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);

		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);
	}

	public void setState(int state) {
		if (state == mState)
			return;

		if (state == STATE_REFRESHING) { // 闂傚倸鍊风粈渚�箹椤愩倧鎷锋い鎺嶇劍濞呯姴霉閻樺樊鍎忓鍛攽椤旀枻渚涢柛妯垮Г缁傚秹濡堕崱娆戭啎闂佺懓顕导婵嬵敂閸偅鏅╅梺璺ㄥ櫐閹凤拷
											// mArrowImageView.clearAnimation();
			mArrowImageView.setVisibility(View.INVISIBLE);
			mProgressBar.setVisibility(View.VISIBLE);
		} else { // 闂傚倸鍊风粈渚�箹椤愩倧鎷锋い鎺嶇劍濞呯姴霉閻樺樊鍎忓鍛攽椤旂瓔鐒惧ù婊庡墯缁嬪顓奸崱鎰盎闂佽宕樺▔娑欑閻撳篃褰掓偐閼碱剙鈪甸梺鍝勬湰閻╊垱淇婇悜钘壩ㄩ柕澶堝灪鐎垫牕鈹戦悩顐ｅ鐎广儱妫楅锟�					// mArrowImageView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.INVISIBLE);
		}

		switch (state) {
		case STATE_NORMAL:
			if (mState == STATE_READY) {
				mArrowImageView.startAnimation(mRotateDownAnim);
			}
			if (mState == STATE_REFRESHING) {
				mArrowImageView.clearAnimation();
			}
			mHintTextView.setText(R.string.xlistview_header_hint_normal);
			break;
		case STATE_READY:
			if (mState != STATE_READY) {
				mArrowImageView.clearAnimation();
				mArrowImageView.startAnimation(mRotateUpAnim);
				mHintTextView.setText(R.string.xlistview_header_hint_ready);
			}
			break;
		case STATE_REFRESHING:
			mHintTextView.setText(R.string.xlistview_header_hint_loading);
			break;
		default:
		}

		mState = state;
	}

	public void setVisiableHeight(int height) {
		if (height < 0)
			height = 0;
		LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
		lp.height = height + mOtherViewHeight;
		mContainer.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
		return mContainer.getHeight() - mOtherViewHeight;
	}

}
