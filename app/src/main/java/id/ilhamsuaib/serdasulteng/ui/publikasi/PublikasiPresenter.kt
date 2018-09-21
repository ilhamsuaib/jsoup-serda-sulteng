package id.ilhamsuaib.serdasulteng.ui.publikasi

import id.ilhamsuaib.serdasulteng.base.BasePresenter
import id.ilhamsuaib.serdasulteng.common.Cons
import id.ilhamsuaib.serdasulteng.common.logD
import id.ilhamsuaib.serdasulteng.common.toJson
import id.ilhamsuaib.serdasulteng.model.Publikasi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by @ilhamsuaib on 8/19/18.
 * github.com/ilhamsuaib
 */

class PublikasiPresenter : BasePresenter<PublikasiView>() {

    private val tag = "DetailMenuPres"

    fun getDocument(url: String?) {
        view?.showProgress(show = true)
        val obs = Observable
                .fromCallable {
                    Jsoup.connect(url).get()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getListData(doc = it)
                }, Throwable::printStackTrace)
        disposables.add(obs)
    }

    private fun getListData(doc: Document) {
        val obs = Observable.just(doc)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .map {
                    val tableRow = it.select("table > tbody > tr")
                    logD(tag, "tableRow : $tableRow")
                    return@map tableRow
                }
                .flatMapIterable { it.reversed() }
                .map {
                    val url = Cons.BASE_URL + it.select("td.list-title a").attr("href")
                    val data = Publikasi(
                            judul = it.select("td.list-title a").text(),
                            penulis = it.select("td.list-author").text().replace("Ditulis oleh ", ""),
                            hits = it.select("td.list-hits").text(),
                            url = url.replace("//", "/")
                    )
                    logD(tag, "listData : ${data.toJson()}")
                    return@map data
                }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logD(tag, "listOfData : ${it.toJson()}")
                    view?.showProgress(show = false)
                    view?.displayData(listOfData = it)
                }, Throwable::printStackTrace)
        disposables.add(obs)
    }
}