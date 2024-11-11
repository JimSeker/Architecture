package edu.cs4730.viewmodeldemo_kt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.viewmodeldemo_kt.databinding.ActivityMainBinding

/**
 * this is a simple example to show how the ViewModel class works and how to separate
 * the data (ie model) from the UI (view/controller of the MVC) as well.
 *
 * using the modelView class also allows the data to survive a rotation on the device as well.
 * so it doesn't need to be reloaded or stored in preference bundle.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        val mViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        //comment out, click the button a couple of times and then rotate phone.  should reset to zero.
        //then return to see it work.
        //binding.tvCount.setText(mViewModel.getCount());

        binding.etName.setText(mViewModel.name)
        binding.etName.addTextChangedListener(object : TextWatcher {
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

        binding.btnAddCount.setOnClickListener {
            logthis("using data from the modelview")
            mViewModel.incrementCount()
            binding.tvCount.text = mViewModel.getCount()
        }
    }

    /*`
     * simple method to add the log TextView.
     */
    fun logthis(newinfo: String) {
        if (newinfo.compareTo("") != 0) {
            binding.logger.append(newinfo + "\n")
        }
    }
}