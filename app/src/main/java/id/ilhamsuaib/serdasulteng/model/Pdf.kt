package id.ilhamsuaib.serdasulteng.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @ilhamsuaib on 8/20/18.
 * github.com/ilhamsuaib
 */

@Parcelize
data class Pdf(
        val description: String? = "",
        val title: String? = "",
        val url: String? = "",
        val imgUrl: String? = ""
): Parcelable