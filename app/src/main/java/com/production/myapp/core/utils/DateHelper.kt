package com.production.myapp.core.utils

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    companion object{
        fun monthNameDateFormat(strDate: String) : String{
           val date: Date? = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(strDate)
            return if (date != null){
                SimpleDateFormat("dd MMM yyyy", Locale.US).format(date)
            } else {
                ""
            }
        }
    }
}