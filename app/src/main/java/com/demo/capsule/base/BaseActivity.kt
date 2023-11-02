package com.demo.capsule.base

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Abstract Activity which binds [ViewModel] [VM] and [ViewBinding] [VB]
 */
abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        setContentView(mViewBinding.root)
    }

    fun handleToolbar(isHide: Boolean) {
        if (isHide)
            supportActionBar?.hide()
        else supportActionBar?.show()
    }

    fun toolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun toolbarIcon(@DrawableRes icon: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(icon)
    }

    fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return List(length) { charset.random() }
            .joinToString("")
    }

    /**
     * It returns [VB] which is assigned to [mViewBinding] and used in [onCreate]
     */
    abstract fun getViewBinding(): VB
}
