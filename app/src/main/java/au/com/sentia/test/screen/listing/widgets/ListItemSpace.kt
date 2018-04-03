package au.com.sentia.test.screen.listing.widgets

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class ListItemSpace(private val left: Int,
                    private val top: Int,
                    private val right: Int,
                    private val bottom: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View?,
                                parent: RecyclerView, state: RecyclerView.State?) {
        var position = parent.getChildAdapterPosition(view);
        if (position == RecyclerView.NO_POSITION)
            return
        //this is for the bottom one where we want extra spacing to the bottom
        if (position == parent.adapter.itemCount.minus(1)) {
            outRect.set(left, top, right, bottom);
        } else {
            //for the rest, we don't want extra spacing at the bottom
            //the spacing of top would be enough
            outRect.set(left, top, right, 0);
        }
    }
}