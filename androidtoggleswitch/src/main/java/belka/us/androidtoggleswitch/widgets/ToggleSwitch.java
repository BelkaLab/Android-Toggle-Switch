package belka.us.androidtoggleswitch.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import belka.us.androidtoggleswitch.R;

/**
 * Created by lorenzorigato on 2/22/16.
 */
public class ToggleSwitch extends LinearLayout{

    public static abstract class OnToggleSwitchChangeListener{
        public abstract void onToggleSwitchChangeListener(int position);
    }

    private final float CORNER_RADIUS_DP = 4;
    private float CORNER_RADIUS_PX;

    private ArrayList<LinearLayout> mToggleButtons;
    private LinearLayout mToggleSwitchesContainer;
    private LayoutInflater mInflater;
    private int mActiveBgColor;
    private int mActiveTextColor;
    private int mInactiveBgColor;
    private int mInactiveTextColor;
    private int mSeparatorColor;
    private int mTextSize;
    private int mCheckedTogglePosition;
    private float mToggleWidth;
    private ArrayList<String> mLabels;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LinearLayout toggleBtn = (LinearLayout) v.getParent();
            setCheckedTogglePosition(mToggleButtons.indexOf(toggleBtn));
        }
    };

    private OnToggleSwitchChangeListener mOnToggleSwitchChangeListener = null;

    public void setLabels(ArrayList<String> labels){
        if(labels == null || labels.isEmpty())
            throw new RuntimeException("The list of labels must contains at least 2 elements");
        mLabels = labels;
        buildToggleButtons();
    }

    public ToggleSwitch(Context context) {
        super(context);
    }

    public ToggleSwitch(final Context context, AttributeSet attrs) {
        super(context, attrs);
        if(attrs != null){
            CORNER_RADIUS_PX = dp2px(context, CORNER_RADIUS_DP);
            mToggleButtons = new ArrayList<>();

            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mInflater.inflate(R.layout.widget_toggle_switch, this, true);

            mToggleSwitchesContainer = (LinearLayout) findViewById(R.id.toggle_switches_container);

            TypedArray a                        = context.obtainStyledAttributes(attrs, R.styleable.ToggleSwitchOptions, 0, 0);
            String centerToggleText             = a.getString(R.styleable.ToggleSwitchOptions_textToggleCenter);
            String leftToggleText               = a.getString(R.styleable.ToggleSwitchOptions_textToggleLeft);
            String rightToggleText              = a.getString(R.styleable.ToggleSwitchOptions_textToggleRight);
            mActiveBgColor                      = a.getColor(R.styleable.ToggleSwitchOptions_activeBgColor, ContextCompat.getColor(context, R.color.blue));
            mActiveTextColor                    = a.getColor(R.styleable.ToggleSwitchOptions_activeTextColor, ContextCompat.getColor(context, android.R.color.white));
            mInactiveBgColor                    = a.getColor(R.styleable.ToggleSwitchOptions_inactiveBgColor, ContextCompat.getColor(context, R.color.gray_light));
            mInactiveTextColor                  = a.getColor(R.styleable.ToggleSwitchOptions_inactiveTextColor, ContextCompat.getColor(context, R.color.gray));
            mSeparatorColor                     = a.getColor(R.styleable.ToggleSwitchOptions_separatorColor, ContextCompat.getColor(context, R.color.gray_very_light));
            mTextSize                           = a.getDimensionPixelSize(R.styleable.ToggleSwitchOptions_android_textSize, (int) dp2px(context, 12.0f));
            mToggleWidth                        = a.getDimension(R.styleable.ToggleSwitchOptions_toggleWidth, dp2px(getContext(), 64));

            if(leftToggleText != null && !leftToggleText.isEmpty() &&
                    rightToggleText != null && !rightToggleText.isEmpty()){
                mLabels = new ArrayList<>();
                mLabels.add(leftToggleText);
                if(centerToggleText != null && !centerToggleText.isEmpty())
                    mLabels.add(centerToggleText);
                mLabels.add(rightToggleText);
                buildToggleButtons();
            }
        }
    }

    private void buildToggleButtons(){
        mToggleSwitchesContainer.removeAllViews();
        for(String label : mLabels)
            addToogleBtn(label);
        setCheckedTogglePosition(0);
    }

    private void active(LinearLayout toggleBtn){
        getTextView(toggleBtn).setTextColor(mActiveTextColor);
        ShapeDrawable sd = new ShapeDrawable(buildRect(toggleBtn));
        sd.getPaint().setColor(mActiveBgColor);
        toggleBtn.setBackground(sd);
    }

    private void addToogleBtn(String text) {
        LinearLayout toggleBtn = (LinearLayout) mInflater.inflate(R.layout.item_widget_toggle_switch, null);
        getTextView(toggleBtn).setText(text);
        setStyle(toggleBtn);
        disable(toggleBtn);
        getTextView(toggleBtn).setOnClickListener(mOnClickListener);
        mToggleButtons.add(toggleBtn);
        mToggleSwitchesContainer.addView(toggleBtn);
    }

    private RoundRectShape buildRect(LinearLayout toggleBtn){
        int index = mToggleButtons.indexOf(toggleBtn);
        if(index == 0)
            return new RoundRectShape(
                    new float[] {CORNER_RADIUS_PX,CORNER_RADIUS_PX, 0,0, 0,0, CORNER_RADIUS_PX,CORNER_RADIUS_PX},
                    null,
                    null);
        else if (index == (mToggleButtons.size() - 1))
            return new RoundRectShape(
                    new float[] {0,0, CORNER_RADIUS_PX,CORNER_RADIUS_PX, CORNER_RADIUS_PX,CORNER_RADIUS_PX, 0,0},
                    null,
                    null);
        else
            return new RoundRectShape(
                    new float[] {0,0, 0,0, 0,0, 0,0},
                    null,
                    null);
    }

    private void disable(LinearLayout toggleBtn){
        ShapeDrawable sd = new ShapeDrawable(buildRect(toggleBtn));
        sd.getPaint().setColor(mInactiveBgColor);
        toggleBtn.setBackground(sd);
        getTextView(toggleBtn).setTextColor(mInactiveTextColor);
    }

    private void disableAll(){
        for(LinearLayout toggleBtn : mToggleButtons)
            disable(toggleBtn);
    }

    public int getCheckedTogglePosition() {
        return mCheckedTogglePosition;
    }

    private View getSeparator(LinearLayout linearLayout){
        return linearLayout.findViewById(R.id.separator);
    }

    private TextView getTextView(LinearLayout linearLayout){
        return (TextView) linearLayout.findViewById(R.id.text_view);
    }

    private void hideSeparator(LinearLayout linearLayout){
        getSeparator(linearLayout).setVisibility(View.INVISIBLE);
    }

    public void setCheckedTogglePosition(int position){
        LinearLayout toggleBtn = mToggleButtons.get(position);
        disableAll();
        active(toggleBtn);
        mCheckedTogglePosition = position;
        setSeparatorVisibility(mCheckedTogglePosition);
        if(mOnToggleSwitchChangeListener != null)
            mOnToggleSwitchChangeListener.onToggleSwitchChangeListener(mCheckedTogglePosition);
    }

    private void setSeparatorVisibility(int activeIndex){
        for(int i=0; i<mToggleButtons.size() - 1;i++){
            if(i == activeIndex || i == (activeIndex - 1))
                hideSeparator(mToggleButtons.get(i));
            else
                showSeparator(mToggleButtons.get(i));
        }
    }

    private void showSeparator(LinearLayout linearLayout){
        getSeparator(linearLayout).setVisibility(View.VISIBLE);
    }

    public void setOnToggleSwitchChangeListener(OnToggleSwitchChangeListener onToggleSwitchChangeListener){
        this.mOnToggleSwitchChangeListener = onToggleSwitchChangeListener;
    }

    private void setStyle(LinearLayout toggleBtn){
        TextView toggleBtnTxt = getTextView(toggleBtn);
        toggleBtnTxt.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        toggleBtnTxt.setLayoutParams(new LayoutParams((int) mToggleWidth, LayoutParams.WRAP_CONTENT));
        getSeparator(toggleBtn).setBackgroundColor(mSeparatorColor);
    }

    private float dp2px(Context context, float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

}
