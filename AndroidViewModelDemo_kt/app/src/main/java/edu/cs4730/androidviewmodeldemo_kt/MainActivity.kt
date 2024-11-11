package edu.cs4730.androidviewmodeldemo_kt

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.androidviewmodeldemo_kt.databinding.ActivityMainBinding

/**
 * Very simple example of using a android view model.
 */
class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: myAndroidViewModel
    lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //get the modelview.
        mViewModel = ViewModelProvider(this)[myAndroidViewModel::class.java]

        //count fields.  Modelview doesn't have livedata here.  So everything is manual.
        binding.tvCount.text = "Count is " + mViewModel.getCount()
        binding.addcount.setOnClickListener {
            mViewModel.incrementCount()
            binding.tvCount.text = "Count is " + mViewModel.getCount()
        }

        //Name field is liveData, so we have observers to the do the work here.
        mViewModel.data.observe(this) { data -> binding.tvName.text = "Name is $data" }

        binding.btnName.setOnClickListener {
            mViewModel.setName(
                binding.etName.text.toString()
            )
        }
    }
}
