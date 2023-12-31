package sing.select.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class CalendarAdapter : RecyclerView.Adapter<ViewHolder>() {
    var data = listOf<CalendarItem>()

    var onClick: ((dateStr: String, position: Int) -> Unit)? = null

    fun setNewData(d: List<CalendarItem>) {
        data = d
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 1) {
            VHMonth(LayoutInflater.from(parent.context).inflate(R.layout.sc_calendar_item_month, parent, false))
        } else {
            VHDay(LayoutInflater.from(parent.context).inflate(R.layout.sc_calendar_item_day, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is VHMonth) {
            holder.tvMonth.text = data[position].monthStr
        } else {
            val helper = holder as VHDay
            helper.tvDay.text = data[position].dayStr
            helper.squareView.setBackGround(data[position].itemType)
            helper.tvDay.setTextColor(
                    when (data[position].dateType) {
                        -1 -> Color.LTGRAY
                        101, 102 -> Color.GRAY
                        else -> Color.BLACK
                    }
            )
            if (data[position].dateType >= 0) {
                onClick?.apply {
                    helper.itemView.setOnClickListener {
                        this(data[position].dateStr, position)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int) = if (data[position].dateType == -1000) 1 else 0

    class VHDay(v: View) : ViewHolder(v) {
        val tvDay: TextView = v.findViewById(R.id.tvDay)
        val squareView: SquareLayout = itemView as SquareLayout
    }

    class VHMonth(v: View) : ViewHolder(v) {
        val tvMonth: TextView = v.findViewById(R.id.tvMonth)
    }
}