package id.ilhamsuaib.serdasulteng.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.ui.publikasi.PublikasiSheetFragment
import id.ilhamsuaib.serdasulteng.model.Banner
import id.ilhamsuaib.serdasulteng.model.MainMenu
import id.ilhamsuaib.serdasulteng.model.SubMenu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter()

    private val mainMenuAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bind(this)
        initView()
        presenter.getHomeDocument()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        this.title = "Serde Sulteng".toUpperCase()
        rvMainMenu.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainMenuAdapter
        }
    }

    override fun displayBanner(bannerList: List<Banner>) {
        val bannerAdapter = BannerAdapter(bannerList, supportFragmentManager)
        bannerViewPager.adapter = bannerAdapter
        indicator.setViewPager(bannerViewPager)

        if (bannerList.size > 1)
            autoScrollViewPager(bannerList.size)
    }

    private fun autoScrollViewPager(pagerCount: Int) {
        val timerTask = object : TimerTask() {
            override fun run() {
                bannerViewPager.post {
                    val nextPage = if (pagerCount == (bannerViewPager.currentItem + 1)) {
                        0
                    } else {
                        bannerViewPager.currentItem + 1
                    }
                    bannerViewPager.currentItem = (nextPage)
                }
            }
        }
        val timer = Timer()
        timer.schedule(timerTask, 5000, 5000)
    }

    override fun displayMenu(menuList: List<MainMenu>) {
        menuList.forEach { mainMenu ->
            mainMenuAdapter.add(MainMenuAdapter(mainMenu = mainMenu) {
                onMenuClick(menu = it)
            })
        }
    }

    private fun onMenuClick(menu: SubMenu) {
        val publikasiSheet = PublikasiSheetFragment.newInstance(menu = menu)
        publikasiSheet.showNow(supportFragmentManager, publikasiSheet.tag)
    }
}
