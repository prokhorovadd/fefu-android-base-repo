package ru.fefu.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activity.adapter.CardsAdapter
import ru.fefu.activity.models.DateModel
import ru.fefu.activity.models.UsersActivityModel
import ru.fefu.activity.databinding.FragmentUsersActivityCardBinding
import java.time.LocalDateTime

class UsersActivity : Fragment(R.layout.fragment_users_activity_card) {
    private var _binding: FragmentUsersActivityCardBinding? = null
    private val binding get() = _binding!!
    private val activitiesDate = mutableListOf<Any>()

    private val activities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        listOf(
            UsersActivityModel("30 км", "Биатлон", LocalDateTime.now(), LocalDateTime.now(), "@anton_shipulin"),
            UsersActivityModel("4 м", "Бег", LocalDateTime.of(2020, 1, 1, 10, 0),
                LocalDateTime.of(2020, 1, 1, 10, 1), "@anna_karenina"))
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    private val datesBind = mapOf(1 to "Январь", 2 to "Февраль", 3 to "Март", 4 to "Апрель", 5 to "Май", 6 to "Июнь",
        7 to "Июль", 8 to "Август", 9 to "Сентябрь", 10 to "Октябрь", 11 to "Ноярь", 12 to "Декабрь")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersActivityCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupDate(tasks: List<UsersActivityModel>) {
        val nowTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        var date = DateModel("")
        for (activity in activities) {
            if (nowTime.year == activity.endTime.year && nowTime.monthValue == activity.endTime.monthValue &&
                nowTime.dayOfMonth == activity.endTime.dayOfMonth && date.Date != "Сегодня")
                activitiesDate.add(DateModel("Сегодня"))
            else activitiesDate.add(DateModel(datesBind[activity.endTime.monthValue] + ' ' + activity.endTime.year.toString() + " года"))
            activitiesDate.add(activity)
        }
    }

    private val adapter = CardsAdapter(activitiesDate)

    private fun switchFragment(pos: Int) {
        if (pos in activitiesDate.indices) {
            val manager = activity?.supportFragmentManager?.findFragmentByTag(ActivityFragment.tag)?.childFragmentManager
            manager?.beginTransaction()?.apply {
                manager.fragments.forEach(::hide)
                add (R.id.fragment_my_activity, MyActivityDetails.newInstance(), MyActivityDetails.tag)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDate(activities)
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setItemClickListener {switchFragment(it)}
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}