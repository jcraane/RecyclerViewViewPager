package nl.jamiecraane.demo.recyclerviewviewpager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {

    val compositeSubscription: CompositeSubscription = CompositeSubscription()
    lateinit var scrollListener: ScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = PagerAdapter(listOf("Page 1", "Page 2", "Page 3"))
        scrollListener = ScrollListener(recyclerView.layoutManager as LinearLayoutManager)
        recyclerView.addOnScrollListener(scrollListener)
    }

    override fun onResume() {
        super.onResume()
        compositeSubscription.add(scrollListener.positionObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ position -> currentPage.text = position.toString() }))
    }

    override fun onPause() {
        compositeSubscription.unsubscribe()
        super.onPause()
    }
}
