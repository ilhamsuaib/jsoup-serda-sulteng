package id.ilhamsuaib.serdasulteng.ui.artikel

import id.ilhamsuaib.serdasulteng.base.BaseView
import id.ilhamsuaib.serdasulteng.model.DetailArtikel

/**
 * Created by ilham on 11/11/17.
 */

interface ArtikelView : BaseView {
    fun displayArtikel(detailArtikel: DetailArtikel?)
    fun showProgress(show: Boolean)
}