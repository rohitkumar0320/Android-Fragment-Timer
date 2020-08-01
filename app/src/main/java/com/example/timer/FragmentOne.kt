package com.example.timer

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_one.view.*
import java.util.*

class FragmentOne : Fragment() {

    private var Timer: CountDownTimer? = null
    private var mTimerRunning = false
    private val START_TIME_IN_MILLIS: Long = 10000
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS

    private lateinit var fragView: View
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragView = inflater.inflate(R.layout.fragment_one, container, false)
        //val mtextViewCountDown=fragView.findViewById<TextView>(R.id.tv)
        val mButton = fragView.findViewById<Button>(R.id.startBtn)
        val rButton = fragView.findViewById<Button>(R.id.resetBtn)
        mButton.setOnClickListener(View.OnClickListener {
            if (mTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        })

        rButton.setOnClickListener(View.OnClickListener {
            resetTimer()
        })
        updateCountDownText()
        return fragView
    }

    private fun startTimer() {
        Timer = object : CountDownTimer(mTimeLeftInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                fragView.findViewById<TextView>(R.id.tv).setText("Finished")
                fragView.findViewById<Button>(R.id.startBtn).setText("Start")
                // fragView.startBtn.isEnabled=false
            }
        }.start()
        mTimerRunning = true
        fragView.findViewById<Button>(R.id.startBtn).setText("Pause")
        fragView.resetBtn.visibility = View.GONE
    }

    private fun pauseTimer() {
        Timer?.cancel()
        mTimerRunning = false
        fragView.findViewById<Button>(R.id.startBtn).setText("Start")
        fragView.resetBtn.visibility = View.VISIBLE
    }

    private fun resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        updateCountDownText()
        //fragView.startBtn.isEnabled=true
    }

    private fun updateCountDownText() {
        val minutes: Long = (mTimeLeftInMillis / 1000) / 60
        val second: Long = (mTimeLeftInMillis / 1000) % 60
        val timeFormatted: String = String.format(Locale.getDefault(), "%02d:%02d", minutes, second)
        fragView.findViewById<TextView>(R.id.tv).setText(timeFormatted)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        startTimer()
        super.onResume()
    }

    override fun onPause() {
        pauseTimer()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }


}