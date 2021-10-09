package com.wayyan.countriessqldelight.extensions

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import com.wayyan.countriessqldelight.databinding.DialogLoadingBinding

fun Context.showShortToast(message: CharSequence) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT)
    .show()
}

fun Context.getDialog(
  view: View,
  cancelable: Boolean
): Dialog {
  val dialog = Dialog(this)
  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
  dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
  dialog.setContentView(view)
  dialog.setCancelable(cancelable)
  return dialog
}

fun Context.getLoadingDialog(description: String): Dialog {
  val binding = DialogLoadingBinding.inflate(LayoutInflater.from(this))
  binding.tvDescription.text = description
  return this.getDialog(binding.root, false)
}