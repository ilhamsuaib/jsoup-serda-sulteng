package id.ilhamsuaib.serdasulteng.ui.publikasi

import android.annotation.SuppressLint
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.serdasulteng.model.Publikasi
import id.ilhamsuaib.serdasulteng.R
import kotlinx.android.synthetic.main.item_publikasi.view.*

/**
 * Created by @ilhamsuaib on 8/19/18.
 * github.com/ilhamsuaib
 */

class PublikasiAdapter(private val publikasi: Publikasi,
                       private val onItemClick: (publikasi: Publikasi) -> Unit): Item() {

    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvTitle.text = publikasi.judul
        viewHolder.itemView.tvAuthor.text = "Oleh : ${publikasi.penulis}"
        viewHolder.itemView.tvHits.text = publikasi.hits
        viewHolder.itemView.setOnClickListener {
            onItemClick(publikasi)
        }
    }

    override fun getLayout(): Int = R.layout.item_publikasi
}