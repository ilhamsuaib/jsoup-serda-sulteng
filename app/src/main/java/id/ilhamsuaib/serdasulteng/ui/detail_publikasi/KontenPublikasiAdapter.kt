package id.ilhamsuaib.serdasulteng.ui.detail_publikasi

import android.annotation.SuppressLint
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.common.loadImage
import id.ilhamsuaib.serdasulteng.model.Pdf
import kotlinx.android.synthetic.main.item_konten_publikasi.view.*

/**
 * Created by @ilhamsuaib on 8/22/18.
 * github.com/ilhamsuaib
 */

class KontenPublikasiAdapter(private val pdf: Pdf,
                             private val onItemClick: () -> Unit) : Item() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val imageView = viewHolder.itemView.imgPdf
        imageView.loadImage(pdf.imgUrl, R.drawable.ic_pdf)
        viewHolder.itemView.tvTitle.text = pdf.title

        viewHolder.itemView.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.item_konten_publikasi
}