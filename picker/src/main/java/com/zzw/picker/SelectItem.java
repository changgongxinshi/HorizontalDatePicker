package com.zzw.picker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * zzw
 * 描述：时间选择的item
 */
public class SelectItem extends LinearLayout {

    private TextView tvMonth;
    private TextView tvYear;
    private View line;
    private Context mContext;

    private boolean mIsSelect=false;
    public SelectItem(Context context) {
        super(context);
    }

    public SelectItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public SelectItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.ui_item_time_select, this, true);

        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(HORIZONTAL);

        tvMonth = findViewById(R.id.tv_month);
        tvYear = findViewById(R.id.tv_year);
        line = findViewById(R.id.line);
        mContext = context;

    }


    public void updateSelectStatus() {
        if(mIsSelect){
            //被选中
            tvMonth.setTextColor(ContextCompat.getColor(mContext,R.color.ui_color_6ca5));
            tvYear.setTextColor(ContextCompat.getColor(mContext,R.color.ui_color_6ca5));
            line.setVisibility(View.VISIBLE);
        }else{

            tvMonth.setTextColor(ContextCompat.getColor(mContext,R.color.ui_color_6e7b));
            tvYear.setTextColor(ContextCompat.getColor(mContext,R.color.ui_color_aab2));
            line.setVisibility(View.INVISIBLE);
        }

    }

    public void setTimeData(String month, String year) {
        tvMonth.setText(month);
        tvYear.setText(year);
    }

    public String getMonth() {
       return tvMonth.getText().toString();
    }
    public String getYear() {
        return tvYear.getText().toString();
    }

    public int getMonthNum() {
        return Integer.parseInt(tvMonth.getText().toString().replace("月",""));
    }
    public int getYearNum() {
        return Integer.parseInt(tvYear.getText().toString().replace("年",""));
    }


    public void setSelectStatus(boolean isSelect) {
        mIsSelect=isSelect;
        updateSelectStatus();
    }

    public boolean getSelectStatus() {
        return mIsSelect;
    }


}
