package com.example.spin_bottle.view.dialog

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle_app.R
import com.example.spin_bottle_app.databinding.DialogBinding

class ChallengeDialog(
    private val context: Context,
    private val challengeToEdit: Challenge? = null,
    private val callback: (Challenge) -> Unit,
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

        text.text = if (challengeToEdit == null) {
            context.getString(R.string.dialog_add_text)
        } else {
            context.getString(R.string.dialog_edit_text)
        }

        editText.setText(challengeToEdit?.description)
        buttonSave.isEnabled = challengeToEdit != null
        buttonSave.backgroundTintList =
            ContextCompat.getColorStateList(
                context,
                if (challengeToEdit != null) R.color.orange1 else R.color.gray_disabled
            )

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        buttonSave.setOnClickListener {
            val challengeDescription = editText.text.toString()
            val challenge = challengeToEdit?.copy(description = challengeDescription)
                ?: Challenge(description = challengeDescription)
            callback(challenge)
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

    fun showStandart() {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle(context.getString(R.string.dialog_delete_text))
            .setMessage(challengeToEdit?.description)
            .setPositiveButton(context.getString(R.string.si)) { dialog, _ ->
                val challenge = challengeToEdit
                    ?: Challenge(description = "")
                callback(challenge)
                dialog.dismiss()
            }
            .setNegativeButton(context.getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.orange1))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.orange1))
    }
}
