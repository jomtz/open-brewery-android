package com.josuemartinez.openbrewery.ui.util

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError", "breweryList")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, breweryList: Any?) {
    view.visibility = if (breweryList != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}