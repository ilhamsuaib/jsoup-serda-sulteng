package id.ilhamsuaib.serdasulteng.ui.detail_publikasi

import id.ilhamsuaib.serdasulteng.base.BasePresenter
import id.ilhamsuaib.serdasulteng.common.Cons
import id.ilhamsuaib.serdasulteng.common.logD
import id.ilhamsuaib.serdasulteng.common.toJson
import id.ilhamsuaib.serdasulteng.model.DetailPublikasi
import id.ilhamsuaib.serdasulteng.model.Pdf
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by @ilhamsuaib on 8/20/18.
 * github.com/ilhamsuaib
 */

class DetailPublikasiPresenter : BasePresenter<DetailPublikasiView>() {

    private val tag = "DetailPublikasiPres"

    fun getDocument(url: String?) {
        val obs = Observable
                .fromCallable {
                    Jsoup.connect(url).get()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getContent(document = it)
                }, Throwable::printStackTrace)
        disposables.add(obs)
    }

    private fun getContent(document: Document) {
        val obs = Observable.just(document)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .map {
                    val title: String? = it.select("div.post__header").select("h2.post__header_title").text()
                    val date: String? = it.select("ul.post__info > li").select("time").text()
                    val category: String? = it.select("ul.post__info").select("li.post__info_item post__info_item--category").text()

                    return@map DetailPublikasi(
                            title = title,
                            date = date,
                            category = category,
                            documents = getPdfDocuments(it.select("div.post__content"))
                    )
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logD(tag, "content : ${it.toJson()}")
                    view?.displayContent(content = it)
                }, Throwable::printStackTrace)
        disposables.add(obs)
    }

    private fun getPdfDocuments(elements: Elements): List<Pdf> {
        val pdfList: List<Pdf> = elements
                .select("p")
                .map {
                    var imageUrl = it.select("a img").attr("src")
                    imageUrl = if (!imageUrl.isNullOrBlank()) Cons.BASE_URL+imageUrl else ""

                    var url = it.select("iframe").attr("src")
                    if (url.isNullOrBlank())
                        url = it.select("a").attr("href")

                    return@map Pdf(
                            description = it.text(),
                            title = it.select("a").attr("title"),
                            url = url,
                            imgUrl = imageUrl.replace("//","/")
                    )
                }
                .filter { pdf -> !pdf.url.isNullOrBlank() }
                .toList()
        return pdfList
    }
}