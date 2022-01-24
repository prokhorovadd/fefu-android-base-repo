package ru.fefu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.fefu.activity.R
import ru.fefu.activity.databinding.ActivityMainBinding
import ru.fefu.activity.ActivityFragment
import ru.fefu.activity.ProfileFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActivity(savedInstanceState)

        binding.navActivitiesAndProfile.setOnNavigationItemSelectedListener {
            setupNavHandler(it.itemId)
            true
        }
    }

    private fun setupActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_view_Activities, ActivityFragment.newInstance(), ActivityFragment.tag,)
                commit()
            }
        }
    }

    private fun setupNavHandler(btn_id: Int) {
        val tabFragments = listOf(
            TabFragment(R.id.activity, ActivityFragment.Companion::newInstance, ActivityFragment.tag),
            TabFragment(R.id.profile, ProfileFragment.Companion::newInstance, ProfileFragment.tag))

        val activeFragment = supportFragmentManager.fragments.firstOrNull{!it.isHidden}
        val hiddenMetaFragment = tabFragments.first { it.buttonId == btn_id }
        val hiddenFragment = supportFragmentManager.findFragmentByTag(hiddenMetaFragment.tag)

        if (activeFragment == hiddenFragment) return
        if (hiddenFragment == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_view_Activities, hiddenMetaFragment.fragment(), hiddenMetaFragment.tag)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                show(hiddenFragment)
                commit()
            }
        }
        if (activeFragment != null) {
            supportFragmentManager.beginTransaction().apply {
                hide(activeFragment)
                commit()
            }
        }

    }

    override fun onBackPressed() {
        val active = supportFragmentManager.fragments.firstOrNull{!it.isHidden}!!
        if (active.childFragmentManager.backStackEntryCount != 0) active.childFragmentManager.popBackStack()
    }
}


class TabFragment(val buttonId: Int, val fragment: () -> Fragment, val tag: String)