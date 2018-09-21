package id.ilhamsuaib.serdasulteng.ui.detail_publikasi

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.common.logD
import id.ilhamsuaib.serdasulteng.common.toJson
import id.ilhamsuaib.serdasulteng.model.DetailPublikasi
import id.ilhamsuaib.serdasulteng.model.Pdf
import id.ilhamsuaib.serdasulteng.model.Publikasi
import kotlinx.android.synthetic.main.activity_detail_publikasi.*
import kotlinx.android.synthetic.main.appbar.*

class DetailPublikasiActivity : AppCompatActivity(), DetailPublikasiView {

    private val tag = "DetailPublikasiAct"
    private val presenter = DetailPublikasiPresenter()
    private val contentAdapter = GroupAdapter<ViewHolder>()
    private var publikasi: Publikasi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_publikasi)

        presenter.bind(this)
        publikasi = intent.getParcelableExtra("publikasi")
        logD(tag, "publikasi : ${publikasi.toJson()}")
        initView()
    }

    private fun initView() {
        presenter.getDocument(url = publikasi?.url)
        setSupportActionBar(toolbar)
        this.title = publikasi?.judul
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvContent.apply {
            layoutManager = LinearLayoutManager(this@DetailPublikasiActivity)
            adapter = contentAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun displayContent(content: DetailPublikasi) {
        tvTitle.text = content.title
        content.documents.forEach {
            contentAdapter.add(KontenPublikasiAdapter(it) {
                showPdfPreview(pdf = it)
            })
        }
    }

    private fun showPdfPreview(pdf: Pdf) {
        val previewSheet = PdfPreviewFragment.newInstance(pdf)
        previewSheet.isCancelable = false
        previewSheet.showNow(supportFragmentManager, previewSheet.tag)
    }
}
