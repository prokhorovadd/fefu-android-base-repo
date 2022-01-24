package ru.fefu.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activity.databinding.UsersActivityDetailsBinding

class UsersActivityDetails: Fragment() {
    private var _binding: UsersActivityDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val tag = "user_activity_details"
        fun newInstance(): UsersActivityDetails {return UsersActivityDetails()}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UsersActivityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {activity?.onBackPressed()}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}