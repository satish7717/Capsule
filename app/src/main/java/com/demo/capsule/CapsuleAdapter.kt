package com.demo.capsule

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.demo.capsule.notes.NotesFragment
import com.demo.capsule.quiz.QuizFragment
import com.demo.capsule.video.VideoFragment

internal class CapsuleAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                VideoFragment()
            }
            1 -> {
                NotesFragment()
            }
            2 -> {
                QuizFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}