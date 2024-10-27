package com.example.spin_bottle.view.dialog

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle_app.R
import com.example.spin_bottle_app.databinding.DialogBinding

class ChallengeDialog(
    private val context: Context,
    private val onChallengeAdded: (Challenge) -> Unit
) {

    fun show() {
        val dialogBinding = DialogBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(context)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)

        val text = dialogBinding.tvDialog
        val editText = dialogBinding.etDialog
        val buttonCancel = dialogBinding.btnCancelDialog
        val buttonSave = dialogBinding.btnSaveDialog

        text.text = context.getString(R.string.dialog_add_text)
        buttonSave.isEnabled = false
        buttonSave.backgroundTintList =
            ContextCompat.getColorStateList(context, R.color.gray_disabled)

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        buttonSave.setOnClickListener {
            val challenge = Challenge(description = editText.text.toString())
            onChallengeAdded(challenge)
            dialog.dismiss()
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                buttonSave.isEnabled = s.toString().isNotEmpty()
                buttonSave.backgroundTintList = if (buttonSave.isEnabled) {
                    ContextCompat.getColorStateList(context, R.color.orange1)
                } else {
                    ContextCompat.getColorStateList(context, R.color.gray_disabled)
                }
            }
        })

        dialog.show()
    }
}
