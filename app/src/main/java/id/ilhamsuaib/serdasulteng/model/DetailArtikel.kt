package id.ilhamsuaib.serdasulteng.model

/**
 * Created by ilham on 11/11/17.
 */
data class DetailArtikel(
        val linkList: ArrayList<Link> = ArrayList(),
        val pdfArtikel: ArrayList<HomeItem> = ArrayList()
)