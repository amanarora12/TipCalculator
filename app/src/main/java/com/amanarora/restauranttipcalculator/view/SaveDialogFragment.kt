package com.amanarora.restauranttipcalculator.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.EditText
import com.amanarora.restauranttipcalculator.R

class SaveDialogFragment : DialogFragment() {

    interface Callback {
        fun onSaveTip(name: String)
    }

    private var saveTipCallback: SaveDialogFragment.Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        saveTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val saveDialog = context?.let { ctx ->

            val editText = EditText(ctx)
            editText.id = viewId
            editText.hint = getString(R.string.save_hint)

            AlertDialog.Builder(ctx)
                    .setView(editText)
                    .setPositiveButton(R.string.action_save, null)
                    .setNegativeButton(R.string.action_cancel, {_,_ ->  onSave(editText)})
                    .create()

        }

        return saveDialog!!
    }

    private fun onSave(editText: EditText) {
        val text = editText.text.toString()
        if(text.isNotEmpty()) {
            saveTipCallback?.onSaveTip(text )
        }
    }

    companion object {
        val viewId = View.generateViewId()
    }
}