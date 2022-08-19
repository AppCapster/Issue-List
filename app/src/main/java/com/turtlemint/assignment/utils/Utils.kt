package com.turtlemint.assignment.utils

import android.app.AlertDialog
import android.content.Context
import com.turtlemint.assignment.R

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
}
