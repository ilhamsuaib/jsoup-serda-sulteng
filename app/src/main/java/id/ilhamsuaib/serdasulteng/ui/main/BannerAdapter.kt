package id.ilhamsuaib.serdasulteng.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import id.ilhamsuaib.serdasulteng.model.Banner

/**
 * Created by @ilhamsuaib on 8/17/18.
 * github.com/ilhamsuaib
 */

class BannerAdapter(private val banners: List<Banner>,
                    fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(i: Int): Fragment = BannerFragment.newInstance(banners[i])

    override fun getCount(): Int = banners.size
}