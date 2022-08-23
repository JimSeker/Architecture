package edu.esp.viewmodeldemo_kt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 * this is a simple example to show how the ViewModel class works and how to separate
 * the data (ie model) from the UI (view/controller of the MVC) as well.
 *
 * using the modelView class also allows the data to survive a rotation on the device as well.
 * so it doesn't need to be reloaded or stored in preference bundle.
 */
class MainActivity : AppCompatActivity() {
    lateinit var et_name: EditText
    lateinit var logger: TextView
    lateinit var tv_count: TextView
    lateinit var addcount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        logger = findViewById(R.id.logger)
        tv_count = findViewById(R.id.tv_count)
        //comment out, click the button a couple of times and then rotate phone.  should reset to zero.
        //then return to see it work.
        //tv_count.setText(mViewModel.getCount());
        et_name = findViewById(R.id.et_name)
        et_name.setText(mViewModel.name)
        et_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                //ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.name = s.toString()
                logthis("storing name data")
            }

            override fun afterTextChanged(s: Editable) {
                //ignore
            }
        })
        addcount = findViewById(R.id.btn_add_count)
        addcount.setOnClickListener {
            logthis("using data from the modelview")
            mViewModel.incrementCount()
            tv_count.text = mViewModel.getCount()
        }
    }

    /*`
     * simple method to add the log TextView.
     */
    fun logthis(newinfo: String) {
        if (newinfo.compareTo("") != 0) {
            logger.append(newinfo + "\n")
        }
    }
}