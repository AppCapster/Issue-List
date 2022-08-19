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
