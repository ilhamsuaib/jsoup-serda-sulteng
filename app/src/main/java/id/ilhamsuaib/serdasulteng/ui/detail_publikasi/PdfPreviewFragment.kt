package id.ilhamsuaib.serdasulteng.ui.detail_publikasi

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.model.Pdf
import kotlinx.android.synthetic.main.fragment_pdf_preview_sheet.view.*


/**
 * Created by @ilhamsuaib on 8/22/18.
 * github.com/ilhamsuaib
 */

class PdfPreviewFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(pdf: Pdf): PdfPreviewFragment {
            val fragment = PdfPreviewFragment()
            val args = Bundle()
            args.putParcelable("pdf", pdf)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pdf_preview_sheet, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pdf: Pdf? = arguments?.getParcelable("pdf")

        view.webView.settings.javaScriptEnabled = true
        view.webView.loadUrl(pdf?.url?.replace("/view", "/preview"))
    }
}