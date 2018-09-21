package id.ilhamsuaib.serdasulteng.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ilham on 11/10/17.
 */

@Parcelize
data class HomeItem(var title: String? = "",
                    var url: String? = "",
                    var imgUrl: String? = "") : Parcelable