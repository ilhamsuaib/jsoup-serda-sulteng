package id.ilhamsuaib.serdasulteng.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @ilhamsuaib on 8/17/18.
 * github.com/ilhamsuaib
 */

@Parcelize
data class SubMenu(val name: String = "", val url: String = "") : Parcelable