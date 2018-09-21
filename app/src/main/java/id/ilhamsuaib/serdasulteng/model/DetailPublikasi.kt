package id.ilhamsuaib.serdasulteng.model

/**
 * Created by @ilhamsuaib on 8/20/18.
 * github.com/ilhamsuaib
 */

data class DetailPublikasi(
        val title: String? = "",
        val date: String? = "",
        val category: String? = "",
        val documents: List<Pdf> = emptyList()
)