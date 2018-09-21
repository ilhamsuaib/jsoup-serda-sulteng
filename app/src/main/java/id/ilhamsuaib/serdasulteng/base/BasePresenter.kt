package id.ilhamsuaib.serdasulteng.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ilham on 11/10/17.
 */
open class BasePresenter<T: BaseView> {

    protected val disposables = CompositeDisposable()
    protected var view: T? = null

    fun bind(view: T) {
        this.view = view
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
        view = null
    }
}