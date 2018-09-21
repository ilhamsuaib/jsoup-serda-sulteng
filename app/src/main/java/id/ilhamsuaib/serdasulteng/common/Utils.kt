package id.ilhamsuaib.serdasulteng.common

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log

/**
 * Created by @ilhamsuaib on 7/30/18.
 * github.com/ilhamsuaib
 */

fun logD(tag: String, s: String) {
    Log.d(tag, s)
}

fun fromHtml(s: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(s)
    }
}