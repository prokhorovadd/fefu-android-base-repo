package ru.fefu.activity

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import ru.fefu.activity.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActivity(savedInstanceState)
        setupNavbarHandler()
    }

    private fun setupActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(
                    R.id.fragment_view_Activities,
                    Activity.newInstance(),
                    Activity.tag
                )
                commit()
            }
        }
    }

    private fun setupNavbarHandler() {
        val tabFragments = listOf(
            TabFragment(R.id.activity, getFragment(Activity.tag),
                Activity.tag
            ),
            TabFragment(R.id.profile, getFragment(Profile.tag),
                Profile.tag
            )
        )

        val navbarHandler = NavbarHandler(tabFragments, supportFragmentManager)

        binding.navActivitiesAndProfile.setOnItemSelectedListener { item ->
            navbarHandler.switchFragments(item.itemId)
            true
        }
    }

    private fun getFragment(tag: String) : Fragment {
        return supportFragmentManager.findFragmentByTag(tag)
            ?: when (tag) {
                Activity.tag -> Activity.newInstance()
                Profile.tag -> Profile.newInstance()
                else -> return Fragment()
            }
    }
}