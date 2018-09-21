package id.ilhamsuaib.serdasulteng.ui.artikel

import android.util.Log.d
import com.google.gson.Gson
import id.ilhamsuaib.serdasulteng.base.BasePresenter
import id.ilhamsuaib.serdasulteng.model.DetailArtikel
import id.ilhamsuaib.serdasulteng.model.HomeItem
import id.ilhamsuaib.serdasulteng.model.Link
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.select.Elements

/**
 * Created by ilham on 11/11/17.
 */
class ArtikelPresenter : BasePresenter<ArtikelView>() {

    private val TAG = this::class.java.simpleName

    fun loadDetailPage(homeItem: HomeItem?) {
        view?.showProgress(true)
        disposables.add(
                Flowable.fromCallable {
                    val detailArtikel = DetailArtikel()
                    val document = Jsoup.connect(homeItem?.url).get()
                    val content = document.select("div.site__content").first()

                    /*post info*/
                    val postInfoElements: Elements = content.select("div.subpage.post > ul > li")
                    val linksList = ArrayList<Link>()
                    postInfoElements.forEach {
                        d(TAG, "$it")
                        val postInfo = Link()
                        postInfo.title = it.text()
                        if (it.text().contains("Ditulis oleh ")) {
                            postInfo.title = it.text().replace("Ditulis oleh ", "")
                        }
                        if (it.text().contains("Kategori: ")) {
                            postInfo.title = it.text().replace("Kategori: ", "")
                        }
                        if (it.text().contains("Dilihat: ")) {
                            postInfo.title = it.text().replace("Dilihat: ", "")
                        }
                        val url = it.select("a").attr("href")
                        if (url.isNotEmpty()) {
                            postInfo.url = "http://serda.sultengprov.go.id$url"
                        }
                        linksList.add(postInfo)
                    }

                    linksList.removeAt(linksList.size.minus(1))
                    linksList.removeAt(linksList.size.minus(1))
                    d(TAG, "post info ${Gson().toJsonTree(linksList)}")
                    detailArtikel.linkList.addAll(linksList)

                    val postContent = content.select("div.post__content p")
                    val pdfArtikelList = ArrayList<HomeItem>()
                    postContent.forEach {
                        val pdfArtikel = HomeItem()
                        pdfArtikel.title = it.select("a").attr("title")
                        val imgUrl = it.select("img").attr("src")
                        if (imgUrl.isNotEmpty()) {
                            pdfArtikel.imgUrl = "http://serda.sultengprov.go.id$imgUrl"
                        }
                        pdfArtikel.url = it.select("a").attr("href")
                        if (pdfArtikel.imgUrl?.isNotEmpty()!!) {
                            pdfArtikelList.add(pdfArtikel)
                        }
                    }
                    d(TAG, "post ${Gson().toJsonTree(pdfArtikelList)}")
                    detailArtikel.pdfArtikel.addAll(pdfArtikelList)
                    view?.displayArtikel(detailArtikel)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach { view?.showProgress(false) }
                .subscribe(
                        {}, {  }
                )
        )
    }
}