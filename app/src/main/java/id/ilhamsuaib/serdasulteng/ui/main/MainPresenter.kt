package id.ilhamsuaib.serdasulteng.ui.main

import id.ilhamsuaib.serdasulteng.base.BasePresenter
import id.ilhamsuaib.serdasulteng.common.Cons
import id.ilhamsuaib.serdasulteng.common.logD
import id.ilhamsuaib.serdasulteng.common.toJson
import id.ilhamsuaib.serdasulteng.model.Banner
import id.ilhamsuaib.serdasulteng.model.MainMenu
import id.ilhamsuaib.serdasulteng.model.SubMenu
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by ilham on 11/10/17.
 */

class MainPresenter : BasePresenter<MainView>() {

    private val tag = "HomePresenter"

    fun getHomeDocument() {
        val obs = Observable
                .fromCallable {
                    Jsoup.connect(Cons.BASE_URL).get()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getBanners(document = it)
                    getAllMenu(document = it)
                }, Throwable::printStackTrace)
        disposables.add(obs)
    }

    private fun getAllMenu(document: Document) {
        val obs = Observable.just(document)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .map {
                    it.select("nav.navigation > ul > li")
                }
                .flatMapIterable { it }
                .map {
                    val n1 = it.select("span")?.first()?.text()
                    val n2 = it.select("a")?.first()?.text()
                    val map = mutableMapOf<String, Any?>()
                    map["groupName"] = if (n1.isNullOrEmpty()) n2 else n1
                    map["element"] = it.select("li > ul")
                    return@map map
                }
                .filter { it.isNotEmpty() }
                .map { map ->
                    val ul = (map["element"] ?: "") as Elements
                    val groupName = (map["groupName"] ?: "") as String

                    logD(tag, "ul : $ul")
                    val subMenuList = ul.select("li").map {
                        val text = it.select("li a").text()
                        val url = Cons.BASE_URL + it.select("li a").attr("href")
                        SubMenu(name = text, url = url.replace("//", "/"))
                    }
                    return@map MainMenu(groupName = groupName, subMenuList = subMenuList)
                }
                .filter { it.subMenuList.isNotEmpty() }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logD(tag, "menu : ${it.toJson()}")
                    view?.displayMenu(menuList = it)
                }, Throwable::printStackTrace)
        disposables.add(obs)
    }

    private fun getBanners(document: Document) {
        val obs = Observable.just(document)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .map {
                    document.select("article")
                }
                .flatMapIterable { it }
                .map {
                    Banner(
                            title = it.select("h2.item__title a").text(),
                            url = Cons.BASE_URL + it.select("h2.item__title a").attr("href"),
                            imageUrl = Cons.BASE_URL + it.select("img").attr("src")
                    )
                }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.displayBanner(bannerList = it)
                }, Throwable::printStackTrace)
        disposables.add(obs)
    }
}