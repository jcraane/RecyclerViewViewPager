package nl.jamiecraane.demo.recyclerviewviewpager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import rx.Observable;
import rx.subjects.BehaviorSubject;

import java.util.concurrent.TimeUnit;

/**
 * Created by jamiecraane on 31/07/2017.
 */

public class ScrollListener extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager layoutManager;
    private final BehaviorSubject<Integer> positionSubject = BehaviorSubject.create();

    public ScrollListener(final LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);
        positionSubject.onNext(layoutManager.findFirstCompletelyVisibleItemPosition());
    }

    public Observable<Integer> getPositionObservable() {
        return positionSubject
                .distinctUntilChanged()
                .filter(position -> position >= 0)
                .debounce(350, TimeUnit.MILLISECONDS);
    }
}
