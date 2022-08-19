package com.turtlemint.assignment.utils

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import com.turtlemint.assignment.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun showSimpleDialog(context: Context?, title: String?, text: String?) {
        context?.let { safeContext ->
            AlertDialog.Builder(safeContext, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton(safeContext.getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun getFormattedDate(date: String?): String {
        var dateStr = ""
        val dateFormat = SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH)
        val simpleDateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH)
        try {
            if (date != null && !TextUtils.isEmpty(date)) {
                val date1 = dateFormat.parse(date)
                dateStr = simpleDateFormat.format(date1)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return dateStr
    }
}
