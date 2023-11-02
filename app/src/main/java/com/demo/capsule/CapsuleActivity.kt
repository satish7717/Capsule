package com.demo.capsule

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.demo.capsule.base.BaseActivity
import com.demo.capsule.databinding.CapsuleLayoutBinding
import com.demo.capsule.models.QuizModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class CapsuleActivity : BaseActivity<CapsuleViewModel, CapsuleLayoutBinding>() {

    override val mViewModel: CapsuleViewModel by viewModels()
    private var questionsItems: List<QuizModel>? = null
    private var timeTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeServicesResponse()
        mViewModel.questions("questions", this)
        mViewBinding.tabLayout.addTab(
            mViewBinding.tabLayout.newTab().setText(getString(R.string.video))
        )
        mViewBinding.tabLayout.addTab(
            mViewBinding.tabLayout.newTab().setText(getString(R.string.notes))
        )
        mViewBinding.tabLayout.addTab(
            mViewBinding.tabLayout.newTab().setText(getString(R.string.quiz))
        )

        val adapter = CapsuleAdapter(
            this, supportFragmentManager,
            mViewBinding.tabLayout.tabCount
        )
        mViewBinding.viewPager.adapter = adapter
        mViewBinding.viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                mViewBinding.tabLayout
            )
        )

        updateFooter("NCERT Lines", "Read from ncert book")
        mViewBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 2)
                    hideFooter(true)
                else {
                    updateItems(tab.position)
                    hideFooter(false)
                }
                mViewBinding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun updateItems(position: Int) {
        if (position == 0) {
            updateFooter("NCERT Lines", "Read from ncert book")
        } else {
            updateFooter("Quiz Test", "Questions: ${questionsItems?.size}")
        }
    }

    private fun startTimer() {
        val timer = object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeTextView?.text = String.format(
                    "%02d min, %02d sec",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    millisUntilFinished
                                )
                            )
                )
            }

            override fun onFinish() {

            }
        }
        timer.start()
    }

    public fun hideFooter(isHide: Boolean) {
        mViewBinding.pagerBottom.visibility = if (isHide) View.GONE else View.VISIBLE
    }

    public fun updateFooter(title: String, subTitle: String) {
        mViewBinding.tvTitle.text = title
        mViewBinding.tvSubTitle.text = subTitle
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var menuItem = menu?.findItem(R.id.action_timer)
        timeTextView = menuItem?.actionView?.findViewById<TextView>(R.id.tv_timer)
        startTimer()
        return super.onPrepareOptionsMenu(menu)
    }

    public fun getQuestions(): List<QuizModel>? {
        return questionsItems
    }

    private fun observeServicesResponse() {
        mViewModel.questionsResponse.observe(this) { response ->
            questionsItems = response
            mViewBinding.viewPager.offscreenPageLimit = mViewBinding.tabLayout.tabCount
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.timer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun getViewBinding(): CapsuleLayoutBinding =
        CapsuleLayoutBinding.inflate(layoutInflater)
}
