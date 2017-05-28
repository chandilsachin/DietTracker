package com.chandilsachin.diettracker.model

import java.io.Serializable
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by sachin on 28/5/17.
 */
class Date : Serializable{
    var date:Int by Delegates.notNull()
    var month:Int by Delegates.notNull()
    var year:Int by Delegates.notNull()

    constructor():this(Calendar.getInstance())

    constructor(date:Int, month:Int, year:Int){
        this.date = date
        this.month = month
        this.year = year
    }

    constructor(calendar: Calendar){
        date = calendar.get(Calendar.DATE)
        month = calendar.get(Calendar.MONTH)+1
        year = calendar.get(Calendar.YEAR)
    }
}