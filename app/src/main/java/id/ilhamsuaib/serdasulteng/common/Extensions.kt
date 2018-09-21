package id.ilhamsuaib.serdasulteng.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.JsonElement

/**
 * Created by @ilhamsuaib on 8/17/18.
 * github.com/ilhamsuaib
 */

fun Any?.toJson(): JsonElement = Gson().toJsonTree(this)

fun Context.toast(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String? = "http://serda.sultengprov.go.id") {
    Glide.with(this.context)
            .load(url)
            .into(this)
}

fun ImageView.loadImage(url: String? = "http://serda.sultengprov.go.id", @DrawableRes onErrorRes: Int) {
    Glide.with(this.context)
            .load(url)
            .error(Glide.with(this.context).load(onErrorRes))
            .into(this)
}