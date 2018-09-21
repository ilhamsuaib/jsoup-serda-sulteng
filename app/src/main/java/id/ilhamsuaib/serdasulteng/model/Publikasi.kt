package id.ilhamsuaib.serdasulteng.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @ilhamsuaib on 8/19/18.
 * github.com/ilhamsuaib
 */

@Parcelize
data class Publikasi(val judul: String? = "",
                     val penulis: String? = "",
                     val hits: String? = "",
                     val url: String? = ""): Parcelable