package edu.cs4730.livedatademo_kt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 * a simple example of using liveData.
 *
 * this example has a problem with the edittext, so this will actually cause the user
 * to insert information "backward" from the order of typing.
 *
 * The "logger" text is the only one that is not saved, so it resets when the phone rotates.
 */
class MainActivity : AppCompatActivity() {
    lateinit var et_name: EditText
    lateinit var logger: TextView
    lateinit var tv_count: TextView
    lateinit var addcount: Button
    lateinit var mViewModel: DataViewModel

    //this is a terrible way to do this, since the text change triggers the observer to update
    //the edittext text is reversed.  but this is a demo, so I'm going with it anyway.
    var mlistener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //ignore
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            mViewModel.setName(s.toString())
            logthis("storing name data")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get the modelview.
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        logger = findViewById(R.id.logger)
        tv_count = findViewById(R.id.tv_count)
        et_name = findViewById(R.id.et_name)
        et_name.addTextChangedListener(mlistener)

        //set the observers for when the data changes.  It will then update.
        //Note, when the phone rotates, this will be called, because the data is new to the activity.
        mViewModel.dataName.observe(this) { s ->
            et_name.removeTextChangedListener(mlistener) //if not, then this will loop infinitely.
            et_name.setText(s)
            et_name.addTextChangedListener(mlistener)
        }
        mViewModel.dataCount.observe(
            this
        ) { integer -> tv_count.setText(integer.toString()) }
        addcount = findViewById(R.id.btn_add_count)

        //change the count.
        addcount.setOnClickListener(View.OnClickListener {
            logthis("using data from the modelview")
            mViewModel.incrementCount()
        })
    }

    /**
     * simple method to add the log TextView.
     */
    fun logthis(newinfo: String) {
        if (newinfo.compareTo("") != 0) {
            logger.append(newinfo + "\n")
            Log.wtf("mainActivity", newinfo)
        }
    }
}
