package id.ilhamsuaib.serdasulteng.ui.publikasi

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.common.toast
import id.ilhamsuaib.serdasulteng.model.Publikasi
import id.ilhamsuaib.serdasulteng.model.SubMenu
import id.ilhamsuaib.serdasulteng.ui.detail_publikasi.DetailPublikasiActivity
import kotlinx.android.synthetic.main.fragment_publikasi_sheet.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

/**
 * Created by @ilhamsuaib on 8/19/18.
 * github.com/ilhamsuaib
 */

class PublikasiSheetFragment : BottomSheetDialogFragment(), PublikasiView {

    private val presenter = PublikasiPresenter()
    private val publikasiAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bind(this)
        return inflater.inflate(R.layout.fragment_publikasi_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menu: SubMenu? = arguments?.getParcelable("menu")

        view.rvPublikasi.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = publikasiAdapter
        }
        view.tvTitle.text = menu?.name
        view.btnHide.setOnClickListener {
            this.dismiss()
        }

        presenter.getDocument(menu?.url)
    }

    override fun displayData(listOfData: List<Publikasi>) {
        listOfData.forEach { publikasi ->
            publikasiAdapter.add(PublikasiAdapter(publikasi = publikasi) {
                this.dismiss()
                onItemClick(publikasi = it)
            })
        }
        isDataEmpty(empty = listOfData.isEmpty())
    }

    private fun onItemClick(publikasi: Publikasi) {
        context?.startActivity(context?.intentFor<DetailPublikasiActivity>()?.apply {
            putExtra("publikasi", publikasi)
        })
    }

    private fun isDataEmpty(empty: Boolean) {
        view?.tvNoData?.visibility = if (empty) View.VISIBLE else View.GONE
    }

    override fun showProgress(show: Boolean) {
        view?.progress?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    companion object {
        fun newInstance(menu: SubMenu): PublikasiSheetFragment {
            val fragment = PublikasiSheetFragment()
            val args = Bundle()
            args.putParcelable("menu", menu)
            fragment.arguments = args
            return fragment
        }
    }
}