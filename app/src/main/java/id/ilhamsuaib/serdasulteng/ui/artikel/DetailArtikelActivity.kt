package id.ilhamsuaib.serdasulteng.ui.artikel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log.d
import android.util.Log.i
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.common.Cons
import id.ilhamsuaib.serdasulteng.model.DetailArtikel
import id.ilhamsuaib.serdasulteng.model.HomeItem
import kotlinx.android.synthetic.main.activity_detail_artikel.*
import kotlinx.android.synthetic.main.appbar.*

class DetailArtikelActivity : AppCompatActivity(), ArtikelView {

    private val TAG = this::class.java.simpleName
    private lateinit var homeItem: HomeItem
    private val presenter = ArtikelPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_artikel)
        presenter.bind(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Detail Artikel"

        homeItem = intent.getParcelableExtra(Cons.ARTIKEL)?: HomeItem()

        d(TAG, "home item : ${Gson().toJsonTree(homeItem)}")

        presenter.loadDetailPage(homeItem)

        rvPostInfo.layoutManager = GridLayoutManager(this, 2)
        rvMajalah.layoutManager = GridLayoutManager(this, 2)
        tvTitle.text = homeItem.title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress(show: Boolean) {
        runOnUiThread {
            progress.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun displayArtikel(detailArtikel: DetailArtikel?) {
        runOnUiThread {
            detailArtikel?.let {
                i(TAG, "detail artikel ${Gson().toJsonTree(it)}")


            }
        }
    }
}
