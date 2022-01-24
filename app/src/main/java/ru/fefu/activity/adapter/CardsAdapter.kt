package ru.fefu.activity.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activity.R
import ru.fefu.activity.models.ActivityModel
import ru.fefu.activity.models.UsersActivityModel
import ru.fefu.activity.models.DateModel
import java.time.Duration
import java.time.LocalDateTime

class CardsAdapter(private val activities: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var ItemClickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if(viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.my_card, parent, false)
            return ActivityViewHolder(view)
        }
        else if (viewType == 1) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.users_card, parent, false)
            return UserActivityViewHolder(view)
        }
        else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_time, parent, false)
            return DateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == 0 -> {
                ((holder as ActivityViewHolder)).bind(activities[position] as ActivityModel)
            }
            getItemViewType(position) == 1 -> {
                ((holder as UserActivityViewHolder)).bind(activities[position] as UsersActivityModel)
            }
            else -> {
                ((holder as DateViewHolder)).bind(activities[position] as DateModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            activities[position] is ActivityModel -> 0
            activities[position] is UsersActivityModel -> 1
            else -> 2
        }
    }

    override fun getItemCount(): Int = activities.size;

    companion object {
        fun getNoun(number: Long, one: String, two: String, three: String) : String {
            var n = number
            n %= 100
            if (n in 5..20) {
                return three
            }
            n %= 10
            if (n == 1L) {
                return one
            }
            if (n in 2..4) {
                return two
            }
            return three
        }
    }

    fun setItemClickListener(listener: (Int) -> Unit) {
        ItemClickListener = listener
    }

    inner class ActivityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val distance = itemView.findViewById<TextView>(R.id.card_distance)
        private val duration = itemView.findViewById<TextView>(R.id.card_duration)
        private val type = itemView.findViewById<TextView>(R.id.card_type)
        private val date = itemView.findViewById<TextView>(R.id.card_date)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                ItemClickListener.invoke(position)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(activity: ActivityModel) {
            distance.text = activity.distance
            type.text = activity.activityType
            val duration_ = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Duration.between(activity.endTime, activity.startTime)
            } else {
                TODO("VERSION.SDK_INT < O")
            };
            var seconds: Long = Math.abs(duration_.getSeconds())
            val hours = seconds / 3600
            seconds -= hours * 3600
            val minutes = seconds / 60
            val hours_ = getNoun(hours, "час", "часа", "часов")
            val minutes_ = getNoun(minutes, "минута", "минуты", "минут")
            duration.text = hours.toString() + ' ' +hours_ + ' ' +minutes.toString() + ' '+ minutes_
            if (LocalDateTime.now().year == activity.endTime.year &&
                LocalDateTime.now().monthValue == activity.endTime.monthValue &&
                LocalDateTime.now().dayOfMonth == activity.endTime.dayOfMonth) {
                date.text = Duration.between(activity.endTime, LocalDateTime.now()).toHours().toString() +
                        getNoun(Duration.between(activity.endTime, LocalDateTime.now()).toHours(), " час", " часа", " часов") +
                        " назад"
            }
            else date.text = activity.endTime.dayOfMonth.toString() + '.'+
                    activity.endTime.monthValue.toString() + '.' + activity.endTime.year.toString()
        }
    }

    inner class UserActivityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val distance = itemView.findViewById<TextView>(R.id.card_distance)
        private val duration = itemView.findViewById<TextView>(R.id.card_duration)
        private val type = itemView.findViewById<TextView>(R.id.card_type)
        private val date = itemView.findViewById<TextView>(R.id.card_date)
        private val user = itemView.findViewById<TextView>(R.id.card_user)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                ItemClickListener.invoke(position)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(activity: UsersActivityModel) {
            distance.text = activity.distance
            type.text = activity.activityType
            user.text = activity.username
            val duration_ = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Duration.between(activity.endTime, activity.startTime)
            } else {
                TODO("VERSION.SDK_INT < O")
            };
            var seconds: Long = Math.abs(duration_.getSeconds())
            val hours = seconds / 3600
            seconds -= hours * 3600
            val minutes = seconds / 60
            val hours_ = getNoun(hours, "час", "часа", "часов")
            val minutes_ = getNoun(minutes, "минута", "минуты", "минут")
            duration.text = hours.toString() + ' ' +hours_ + ' ' +minutes.toString() + ' '+ minutes_
            if (LocalDateTime.now().year == activity.endTime.year &&
                LocalDateTime.now().monthValue == activity.endTime.monthValue &&
                LocalDateTime.now().dayOfMonth == activity.endTime.dayOfMonth) {
                date.text = Duration.between(activity.endTime, LocalDateTime.now()).toHours().toString() +
                        getNoun(Duration.between(activity.endTime, LocalDateTime.now()).toHours(), " час", " часа", " часов") +
                        " назад"
            }
            else date.text = activity.endTime.dayOfMonth.toString() + '.'+
                    activity.endTime.monthValue.toString() + '.' + activity.endTime.year.toString()
        }
    }

    inner class DateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val date = itemView.findViewById<TextView>(R.id.card_time)

        @SuppressLint("SetTextI18n")
        fun bind(date_: DateModel) {
            date.text = date_.Date
        }
    }
}

