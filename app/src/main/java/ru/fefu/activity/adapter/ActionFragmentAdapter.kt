package ru.fefu.activity.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activity.Activity
import ru.fefu.activity.MyActivity
import ru.fefu.activity.UsersActivity

class ActionFragmentAdapter(fragment : Activity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) MyActivity()
        else UsersActivity()
    }
}