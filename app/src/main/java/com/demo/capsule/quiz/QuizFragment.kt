package com.demo.capsule.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import com.demo.capsule.CapsuleActivity
import com.demo.capsule.R
import com.demo.capsule.base.BaseFragment
import com.demo.capsule.databinding.QuizLayoutBinding
import com.demo.capsule.models.QuizModel
import com.demo.capsule.utils.UiHelper.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizFragment : BaseFragment<QuizFragmentViewModel, QuizLayoutBinding>(),
    View.OnClickListener {

    override val mViewModel: QuizFragmentViewModel by viewModels()
    private var progress: Int = 0
    private var index: Int? = 0
    private var questionsItems: List<QuizModel>? = null
    private var checkedRadioButton: RadioButton? = null
    private var selectedItems: Map<Int, String> = mapOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.ivLeftArrow.setOnClickListener(this)
        mViewBinding.ivRightArrow.setOnClickListener(this)
        mViewBinding.btnSubmit.setOnClickListener(this)
        questionsItems = (requireActivity() as CapsuleActivity).getQuestions()
        mViewBinding.progressbar.max = questionsItems?.size?.times(10)!!
        mViewBinding.ivRightArrow.performClick()
        mViewBinding.rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if(group.findViewById<View>(checkedId)!=null){
                checkedRadioButton = group.findViewById<View>(checkedId) as RadioButton
                mViewBinding.btnSubmit.isEnabled = true
            }
        })
    }

    override fun getViewBinding(): QuizLayoutBinding =
        QuizLayoutBinding.inflate(layoutInflater)

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_right_arrow -> {
                questionsItems?.get(progress)?.let { loadQuestions(it) }
                progress += 1
                mViewBinding.progressbar.progress = progress * 10
            }

            R.id.iv_left_arrow -> {
                progress -= 1
                mViewBinding.progressbar.progress = progress * 10
                questionsItems?.get(progress.minus(1))?.let { loadQuestions(it) }
            }

            R.id.btn_submit -> {
                val item = index?.let { questionsItems?.get(it) }
                checkedRadioButton =
                    mViewBinding.rg.findViewById(mViewBinding.rg.checkedRadioButtonId)
                if (checkedRadioButton?.text?.equals(item?.answer) == true)
                    requireActivity().toast("Correct Answer")
                else
                    requireActivity().toast("Wrong Answer")
            }
        }
    }

    private fun loadQuestions(model: QuizModel) {
        mViewBinding.tvQues.text = model.question
        mViewBinding.rb1.text = model.options?.get(0)
        mViewBinding.rb2.text = model.options?.get(1)
        mViewBinding.rb3.text = model.options?.get(2)
        mViewBinding.rb4.text = model.options?.get(3)
        index = questionsItems?.indexOf(model)
        mViewBinding.tvQuesStatus.setText("Question " + index?.plus(1) + "/" + questionsItems?.size)
        mViewBinding.ivRightArrow.isEnabled = (index?.plus(1) != questionsItems?.size)
        mViewBinding.ivLeftArrow.isEnabled = (index?.plus(1) != 1)
        mViewBinding.rg.clearCheck()
        mViewBinding.btnSubmit.isEnabled = false
    }


}