package com.zzw.picker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * zzw
 * 描述：时间选择的group
 */
public class SelectTimeGroup extends LinearLayout {

    private SelectItem mSelectTimeFirst;
    private SelectItem mSelectTimeSecond;
    private SelectItem mSelectTimeThird;
    private SelectItem mSelectTimeForth;
    private SelectItem mSelectTimeFifth;
    private SelectItem mSelectTimeSixth;
    private List<SelectItem> mTimeGroup = new ArrayList<>();
    private int mSelectIndex = 5;//当前选中的月份的index
    private int mCurrentMonth;//当前选中的月份
    private int mCurrentYear;//当前选中的年份
    private OnSelectTimeListener mSelectListener;

    public SelectTimeGroup(Context context) {
        super(context);
    }

    public SelectTimeGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public SelectTimeGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.ui_time_group, this, true);

        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        setOrientation(HORIZONTAL);
        mSelectTimeFirst = findViewById(R.id.tvTimeFirst);
        mSelectTimeSecond = findViewById(R.id.tvTimeSecond);
        mSelectTimeThird = findViewById(R.id.tvTimeThird);
        mSelectTimeForth = findViewById(R.id.tvTimeForth);
        mSelectTimeFifth = findViewById(R.id.tvTimeFifth);
        mSelectTimeSixth = findViewById(R.id.tvTimeSixth);
        initData();
    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        mCurrentYear = calendar.get(Calendar.YEAR);
        mCurrentMonth = calendar.get(Calendar.MONTH) + 1;

        mTimeGroup.add(mSelectTimeFirst);
        mTimeGroup.add(mSelectTimeSecond);
        mTimeGroup.add(mSelectTimeThird);
        mTimeGroup.add(mSelectTimeForth);
        mTimeGroup.add(mSelectTimeFifth);
        mTimeGroup.add(mSelectTimeSixth);

        for (int i = 0; i < mTimeGroup.size(); i++) {
            final int finalI = i;
            if (i == 5) {
                mTimeGroup.get(i).setTimeData(mCurrentMonth + "月", mCurrentYear + "年");
            } else {
                mTimeGroup.get(i).setTimeData(getMonthBefore(5 - i) + "月", getYearBefore(5 - i) + "年");
            }

            mTimeGroup.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectIndex = finalI;
                    updateSelectStatus();
                    if (null != mSelectListener) {
                        String monthDate="";
                        if(mTimeGroup.get(finalI).getMonthNum()<10){
                            monthDate= mTimeGroup.get(finalI).getYearNum()+"-0"+mTimeGroup.get(finalI).getMonthNum();
                        }else{
                            monthDate= mTimeGroup.get(finalI).getYearNum()+"-"+mTimeGroup.get(finalI).getMonthNum();
                        }

                        mSelectListener.onTimeSelectClick(monthDate,mSelectIndex);
                    }
                }
            });
        }
        updateSelectStatus();
    }


    private int getMonthBefore(int monthBefore) {
        int dValue = mCurrentMonth - monthBefore;
        if (dValue > 0) {
            return dValue;
        } else {
            return 12 + dValue;
        }
    }

    private int getYearBefore(int monthBefore) {
        int dValue = mCurrentMonth - monthBefore;
        if (dValue > 0) {
            return mCurrentYear;
        } else {
            return (mCurrentYear - 1);
        }
    }

    //更新选中的状态
    public void updateSelectStatus() {
        for (int i = 0; i < mTimeGroup.size(); i++) {
            if (i == mSelectIndex) {
                mTimeGroup.get(i).setSelectStatus(true);
            } else {
                mTimeGroup.get(i).setSelectStatus(false);
            }
        }

    }

    //设置被选中的item
    public void setCurrnetSelectItem(int index){
        mSelectIndex=index;
        updateSelectStatus();
    }

    //获取当先被选中的年份月份
    public String getCurrrentSelectDate(){
        String monthDate="";
        if(mTimeGroup.get(mSelectIndex).getMonthNum()<10){
            monthDate= mTimeGroup.get(mSelectIndex).getYearNum()+"-0"+mTimeGroup.get(mSelectIndex).getMonthNum();
        }else{
            monthDate= mTimeGroup.get(mSelectIndex).getYearNum()+"-"+mTimeGroup.get(mSelectIndex).getMonthNum();
        }
        return monthDate;
    }

    public String getDateStart(){
        if(mCurrentMonth-1<=0){
            return mCurrentYear-1+"-"+12;
        }else{
            if(mCurrentMonth-1<10){
                return mCurrentYear+"-0"+(mCurrentMonth-1);
            }else{
                return mCurrentYear+"-"+(mCurrentMonth-1);
            }
        }
    }

    public void setOnSelectClickListener(OnSelectTimeListener selectClickListener) {
        mSelectListener = selectClickListener;
    }
}
