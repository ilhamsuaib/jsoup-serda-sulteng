package id.ilhamsuaib.serdasulteng.ui.publikasi

import id.ilhamsuaib.serdasulteng.base.BaseView
import id.ilhamsuaib.serdasulteng.model.Publikasi

/**
 * Created by @ilhamsuaib on 8/19/18.
 * github.com/ilhamsuaib
 */

interface PublikasiView : BaseView {
    fun displayData(listOfData: List<Publikasi>)
    fun showProgress(show: Boolean)
}