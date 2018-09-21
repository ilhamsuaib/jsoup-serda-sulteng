package id.ilhamsuaib.serdasulteng.ui.detail_publikasi

import id.ilhamsuaib.serdasulteng.base.BaseView
import id.ilhamsuaib.serdasulteng.model.DetailPublikasi
import org.jsoup.select.Elements

/**
 * Created by @ilhamsuaib on 8/20/18.
 * github.com/ilhamsuaib
 */

interface DetailPublikasiView : BaseView {
    fun displayContent(content: DetailPublikasi)
}