package edu.esp.androidviewmodeldemo_kt

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 * Very simple example of using a android view model.
 */
class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: myAndroidViewModel
    lateinit var et_name: EditText
    lateinit var tv_name: TextView
    lateinit var tv_count: TextView
    lateinit var addcount: Button
    lateinit var btn_name: Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get the modelview.
        mViewModel = ViewModelProvider(this)[myAndroidViewModel::class.java]

        //count fields.  Modelview doesn't have livedata here.  So everything is manual.
        tv_count = findViewById(R.id.textView)
        addcount = findViewById(R.id.button)
        tv_count.text = "Count is " + mViewModel.getCount()
        addcount.setOnClickListener(View.OnClickListener {
            mViewModel.incrementCount()
            tv_count.text = "Count is " + mViewModel.getCount()
        })

        //Name field is liveData, so we have observers to the do the work here.
        btn_name = findViewById(R.id.button2)
        et_name = findViewById(R.id.et_name)
        tv_name = findViewById(R.id.tv_name)
        mViewModel.data.observe(
            this
        ) { data -> tv_name.text = "Name is $data" }
        btn_name.setOnClickListener(View.OnClickListener {
            mViewModel.setName(
                et_name.text.toString()
            )
        })
    }
}
