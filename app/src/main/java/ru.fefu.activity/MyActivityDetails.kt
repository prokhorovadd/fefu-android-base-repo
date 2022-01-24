package ru.fefu.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activity.databinding.MyActivityDetailsBinding

class MyActivityDetails: Fragment() {
    private var _binding: MyActivityDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val tag = "my_activity_details"
        fun newInstance(): MyActivityDetails {return MyActivityDetails()}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyActivityDetailsBinding.inflate(inflater, container, false)
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