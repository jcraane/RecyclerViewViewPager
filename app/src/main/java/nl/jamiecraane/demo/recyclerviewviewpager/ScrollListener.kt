package nl.jamiecraane.demo.recyclerviewviewpager

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import rx.Observable
import rx.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

/**
 * Created by jamiecraane on 02/02/2018.
 */
class ScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private val positionSubject = BehaviorSubject.create<Int>()

    val positionObservable: Observable<Int>
        get() = positionSubject
                .distinctUntilChanged()
                .filter { position -> position >= 0 }
                .debounce(350, TimeUnit.MILLISECONDS)

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        positionSubject.onNext(layoutManager.findFirstCompletelyVisibleItemPosition())
    }
}