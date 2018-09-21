package id.ilhamsuaib.serdasulteng.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.model.Banner
import kotlinx.android.synthetic.main.fragment_banner.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BannerFragment : Fragment() {

    companion object {
        fun newInstance(banner: Banner): BannerFragment {
            val fragment = BannerFragment()
            val args = Bundle()
            args.putParcelable("banner", banner)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val banner: Banner? = arguments?.getParcelable("banner")

        view.tvBannerTitle.text = banner?.title
        Glide.with(this)
                .load(banner?.imageUrl)
                .into(view.imgBanner)
    }
}
