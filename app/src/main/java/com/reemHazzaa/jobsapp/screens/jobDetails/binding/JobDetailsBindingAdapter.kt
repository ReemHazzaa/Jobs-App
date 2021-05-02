package com.reemHazzaa.jobsapp.screens.jobDetails.binding

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter


class JobDetailsBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setTextFromHtml")
        fun setTextFromHtml(view: TextView, text: String) {
            view.text = Html.fromHtml(text)
        }

        @JvmStatic
        @BindingAdapter("openUrlInBrowser")
        fun openUrl(view: TextView, textUrl: String) {
            var tempUrl = textUrl
            if (!textUrl.startsWith("http://") && !textUrl.startsWith("https://")) {
                tempUrl = "http://$textUrl"
            }
            view.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(tempUrl))
                startActivity(view.context, browserIntent, null)
            }
        }
    }
}