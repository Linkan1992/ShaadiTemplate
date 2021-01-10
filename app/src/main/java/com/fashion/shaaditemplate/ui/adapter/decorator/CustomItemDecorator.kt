package com.fashion.shaaditemplate.ui.adapter.decorator

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CustomItemDecorator(
    private val verticalSpacing: Int,
    private val horizontalSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val childPosition = parent.getChildAdapterPosition(view)

        with(outRect) {
            when (parent.layoutManager) {
                is GridLayoutManager -> {

                }
                is LinearLayoutManager -> {
                    if ((parent.layoutManager as LinearLayoutManager).orientation == LinearLayoutManager.HORIZONTAL) {
                        // horizontal scroll
                        /**
                         * only horizontal end margin apply dynamically to last item
                         * if margin is given in xml file
                         */
                    } else {
                        // vertical scroll
                        /**
                         * only vertical bottom margin apply dynamically to last item
                         *  if margin is given in xml file
                         */

                        val viewType = if(childPosition >= 0 ) parent.adapter?.getItemViewType(childPosition) else -1


                            if (childPosition == 0)
                                top = verticalSpacing

                            left = horizontalSpacing
                            right = horizontalSpacing
                            bottom = verticalSpacing

                    }
                }
            }

            /**
             * represents first item child
             */
            if (parent.getChildAdapterPosition(view) == 0) {

            }
            /**
             * represents last item child
             */
            parent.adapter?.apply {
                if (parent.getChildAdapterPosition(view) == itemCount - 1) {

                }
            }

        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

    }
}