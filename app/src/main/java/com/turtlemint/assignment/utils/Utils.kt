package com.turtlemint.assignment.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.turtlemint.assignment.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val INTENT_MAP_DATA_KEY = "mapData"
    const val ISSUE_OBJECT = "issueObject"

    private const val SECOND = 1
    private const val MINUTE = 60 * SECOND
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR
    private const val MONTH = 30 * DAY
    private const val YEAR = 12 * MONTH

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

    fun stringDateToLong(date: String?): Long {
        var calendar: Long = 0
        val dateFormat = SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH)
        try {
            if (date != null && !TextUtils.isEmpty(date)) {
                calendar = dateFormat.parse(date)?.time ?: 0
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return calendar
    }

    private fun currentDate(): Long {
        val calendar = Calendar.getInstance()
        return calendar.timeInMillis
    }

    // Long: time in millisecond
    fun Long.toTimeAgo(): String {
        val time = this
        val now = currentDate()

        // convert back to second
        val diff = (now - time) / 1000

        return when {
            diff < MINUTE -> "Just now"
            diff < 2 * MINUTE -> "a minute ago"
            diff < 60 * MINUTE -> "${diff / MINUTE} minutes ago"
            diff < 2 * HOUR -> "an hour ago"
            diff < 24 * HOUR -> "${diff / HOUR} hours ago"
            diff < 2 * DAY -> "yesterday"
            diff < 30 * DAY -> "${diff / DAY} days ago"
            diff < 2 * MONTH -> "a month ago"
            diff < 12 * MONTH -> "${diff / MONTH} months ago"
            diff < 2 * YEAR -> "a year ago"
            else -> "${diff / YEAR} years ago"
        }
    }
}

fun ImageView.showLoader(isLoading: Boolean) {
    try {
        if (isLoading) {
            val myAnimationDrawable = this.drawable as AnimationDrawable
            myAnimationDrawable.start()
            this.visibility = View.VISIBLE
        } else {
            val myAnimationDrawable = this.drawable as AnimationDrawable
            myAnimationDrawable.stop()
            this.visibility = View.GONE
        }
    } catch (e: Exception) {
        Log.e("Utils", "Exception: Animation: " + e.message)
    }
}

fun Context.launchActivity(
    cls: Class<*>,
    maps: HashMap<String, Any> = HashMap(),
    flags: Int = 0,
    intentTransformer: Intent.() -> Unit = {}
) {
    val intent = Intent(this, cls).apply {
        addFlags(flags)
        putExtra(Utils.INTENT_MAP_DATA_KEY, maps)
        intentTransformer()
    }
    this.startActivity(intent)
}
