package ru.fefu.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class NavbarHandler(private val fragments: List<TabFragment>, private val fragmentManager: FragmentManager) {
    private lateinit var _hiddenMetaFragment : TabFragment

    fun switchFragments(clickedButtonId: Int) {
        val activeFragment = fragmentManager.fragments.firstOrNull { !it.isHidden }

        _hiddenMetaFragment = fragments.filter { it.button_id == clickedButtonId }[0]
        val hiddenFragment = fragmentManager.findFragmentByTag(_hiddenMetaFragment.tag)

        if (activeFragment == hiddenFragment) return

        if (hiddenFragment == null) {
            fragmentManager.beginTransaction().apply {
                add(
                    R.id.fragment_view_Activities,
                    _hiddenMetaFragment.fragment,
                    _hiddenMetaFragment.tag
                )
                commit()
            }
        }
        else {
            fragmentManager.beginTransaction().apply {
                show(hiddenFragment)
                commit()
            }
        }

        if (activeFragment != null) {
            fragmentManager.beginTransaction().apply {
                hide(activeFragment)
                commit()
            }
        }

    }
}

class TabFragment(val button_id: Int, val fragment: Fragment, val tag: String)
