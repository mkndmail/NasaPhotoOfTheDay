package com.mkndmail.nasaphotooftheday

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mkndmail.nasaphotooftheday.databinding.ActivityMainBinding
import com.mkndmail.nasaphotooftheday.viewmodel.MainViewModel
import java.util.*

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = mainViewModel
        mainViewModel.status.observe(this, Observer { status ->
            binding.status = status
        })

        mainViewModel.apodDataResponse.observe(this, Observer {
            binding.apodData = it
        })

        mainViewModel.startNewActivity.observe(this, Observer { apodData ->
            if (apodData.mediaType == "video") {
                val intent = Intent(this, VideoPlayActivity::class.java).apply {
                    putExtra(RESPONSE_DATA, apodData)
                }
                startActivity(intent)
            } else if (apodData.mediaType == "image") {
                val intent = Intent(this, ImageViewActivity::class.java).apply {
                    putExtra(RESPONSE_DATA, apodData)
                }
                startActivity(intent)
            }
        })

        mainViewModel.openDatePicker.observe(this, Observer {
            if (it) {
                val newFragment = DatePickerFragment(this, this)
                newFragment.show(supportFragmentManager, "datePicker")

            }
        })
    }

    companion object {
        const val RESPONSE_DATA = "response_data"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val dateInRequiredFormat = mainViewModel.getDateInYYYYMMDD(year, month, dayOfMonth)
        mainViewModel.getApodDataByDate(dateInRequiredFormat)
    }


    class DatePickerFragment(
        private val listener: DatePickerDialog.OnDateSetListener,
        private val mainActivity: MainActivity
    ) : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            // Create a new instance of DatePickerDialog and return it
            return DatePickerDialog(mainActivity, listener, year, month, day)

        }
    }
}



