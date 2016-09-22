package belka.us.androidtoggleswitch.widgets;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by lorenzorigato on 3/31/16.
 */
public class MultipleToggleSwitch extends BaseToggleSwitch {

    private Set<Integer> mCheckedPositions;

    public MultipleToggleSwitch(Context context) {
        this(context, null);
    }

    public MultipleToggleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCheckedPositions = new TreeSet<>();
        prepareToggleSwitchLayouts();
    }

    public void setCheckedTogglePosition(int position) {
        mCheckedPositions.add(position);
        prepareToggleSwitchLayouts();
        notifyOnToggleChange(position);
    }

    public void setUncheckedTogglePosition(int position) {
        mCheckedPositions.remove(position);
        prepareToggleSwitchLayouts();
        notifyOnToggleChange(position);
    }

    private void prepareToggleSwitchLayouts() {
        for (int i = 0; i < getNumButtons(); i++) {
            if(isActive(i)) {
                activate(i);
                prepareSeparator(true, i);
            }
            else {
                disable(i);
                prepareSeparator(false, i);
            }
        }
    }

    public Set<Integer> getCheckedTogglePositions() {
        return mCheckedPositions;
    }

    private void prepareSeparator(boolean isActive, int position) {
        if( !isLast(position) && isActive == isActive(position + 1))
            getToggleSwitchButton(position).showSeparator();
        else
            getToggleSwitchButton(position).hideSeparator();
    }

    protected boolean isActive(int position) {
        return mCheckedPositions.contains(position);
    }

    @Override
    protected void onClickOnToggleSwitch(int position) {
        if(isActive(position))
            setUncheckedTogglePosition(position);
        else
            setCheckedTogglePosition(position);
    }
}
