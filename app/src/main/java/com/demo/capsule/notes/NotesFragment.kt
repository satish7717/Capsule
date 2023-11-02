package com.demo.capsule.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.demo.capsule.base.BaseFragment
import com.demo.capsule.databinding.NotesLayoutBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesFragmentViewModel, NotesLayoutBinding>(){

    override val mViewModel: NotesFragmentViewModel by viewModels()
    private var isSavedState: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getViewBinding(): NotesLayoutBinding =
        NotesLayoutBinding.inflate(layoutInflater)


}