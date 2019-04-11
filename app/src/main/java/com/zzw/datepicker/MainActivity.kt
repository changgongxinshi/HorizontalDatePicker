package com.zzw.datepicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zzw.picker.OnSelectTimeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSelectTimeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        picker.setOnSelectClickListener(this)
    }

    override fun onTimeSelectClick(monthDate: String?, indexClick: Int) {
        Toast.makeText(this, monthDate, Toast.LENGTH_SHORT).show()
    }
}
