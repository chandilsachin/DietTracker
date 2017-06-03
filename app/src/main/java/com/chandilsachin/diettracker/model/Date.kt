package com.chandilsachin.diettracker.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * Created by sachin on 28/5/17.
 */
class Date : Serializable, Parcelable{

    var date:Int by Delegates.notNull()
    var month:Int by Delegates.notNull()
    var year:Int by Delegates.notNull()
    var calendar:Calendar by Delegates.notNull()

    constructor():this(Calendar.getInstance())

    constructor(date:Int, month:Int, year:Int){
        this.date = date
        this.month = month
        this.year = year
        this.calendar = Calendar.getInstance()
        initCalendar()
    }

    constructor(calendar: Calendar){
        date = calendar.get(Calendar.DATE)
        month = calendar.get(Calendar.MONTH)+1
        year = calendar.get(Calendar.YEAR)
        this.calendar = calendar

        initCalendar()
    }

    private fun initCalendar(){
        var dateFormat = SimpleDateFormat("dd/MM/yyyy")
        calendar.time = dateFormat.parse("$date/$month/$year")
    }

    fun getTime():Long{
        return calendar.timeInMillis
    }

    fun getPrettyDate():String{
        var prettyDate = ""
        val today = Date()
        prettyDate = if(today.equals(this))"Today" else if(today - this == 1) "Yesterday" else "$date/$month/$year"
        return prettyDate
    }

    override fun equals(other: Any?): Boolean {
        if(other is Date){
            return other.date == date && other.month == month && other.year == year
        }
        return false
    }

    operator fun minus(value:Date):Int{
        return TimeUnit.DAYS.convert(getTime() - value.getTime(),
                TimeUnit.MILLISECONDS).toInt()
    }

    companion object{
        infix fun past(days:Int):Date{
            return getDateTo(days)
        }

        infix fun ahead(days:Int):Date{
            return getDateTo(days)
        }

        private fun getDateTo(days:Int):Date{
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, days)
            return Date(cal)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        val array = IntArray(3)
        array[0] = date
        array[1] = month
        array[2] = year
        dest?.writeIntArray(array)
    }

    override fun describeContents(): Int {
        return 0
    }
}