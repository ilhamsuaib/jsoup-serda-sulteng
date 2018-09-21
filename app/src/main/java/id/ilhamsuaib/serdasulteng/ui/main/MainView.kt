package id.ilhamsuaib.serdasulteng.ui.main

import id.ilhamsuaib.serdasulteng.base.BaseView
import id.ilhamsuaib.serdasulteng.model.Banner
import id.ilhamsuaib.serdasulteng.model.MainMenu

/**
 * Created by ilham on 11/10/17.
 */
interface MainView : BaseView {
    fun displayBanner(bannerList: List<Banner>)
    fun displayMenu(menuList: List<MainMenu>)
}