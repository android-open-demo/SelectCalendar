package sing.select

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sing.select.calendar.CalendarView
import kotlinx.android.synthetic.main.activity_main.*
import sing.select.calendar.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendar_view.selectDataListener = object : CalendarView.SelectDataListener{
            override fun select(start: String, end: String) {
                Toast.makeText(this@MainActivity,"$start - $end",Toast.LENGTH_LONG).show()
            }
        }
    }
}