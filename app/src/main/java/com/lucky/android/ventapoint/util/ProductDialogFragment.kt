package com.lucky.android.ventapoint.util

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.lucky.android.ventapoint.R


class ProductDialogFragment(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_product, null))
                .setMessage("¿Deseas ordenar? Ingrese una cantidad menor al stock actual.")
                .setPositiveButton("GUARDAR") { dialog, id ->

                }
                .setNegativeButton("CANCELAR"){ dialog, id -> }

        return builder.create()
    }
}