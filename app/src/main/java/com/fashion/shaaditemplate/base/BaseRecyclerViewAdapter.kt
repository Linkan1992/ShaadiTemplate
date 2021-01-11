package com.fashion.shaaditemplate.base


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fashion.shaaditemplate.R
import com.fashion.shaaditemplate.databinding.ErrorLayoutBinding
import com.fashion.shaaditemplate.util.constUtil.ViewType


abstract class BaseRecyclerViewAdapter<T, K : BaseRecyclerViewAdapter<T, K>.BaseViewHolder>(private val data: MutableList<T>) :
    RecyclerView.Adapter<K>() {

    var mEmptyBinding : ErrorLayoutBinding? = null

    protected abstract fun mOnCreateViewHolder(parent: ViewGroup, viewType: Int): K

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K =
        when (viewType) {

            ViewType.VIEW_TYPE_NORMAL -> mOnCreateViewHolder(parent, viewType)

            ViewType.VIEW_TYPE_EMPTY -> {

                val emptyRowLayoutBinding = ErrorLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                 EmptyLeadViewHolder(emptyRowLayoutBinding) as K
            }

            else ->  mOnCreateViewHolder(parent, viewType)
        }



    override fun onBindViewHolder(holder: K, position: Int) {
        if (holder != null && data.isNotEmpty())
            holder.onBind(data[position])
        else if (data.isEmpty())
            holder.onBind(null)

    }

    override fun getItemCount(): Int = if (data.isNotEmpty()) data.size else 0

    override fun getItemViewType(position: Int): Int = if (data.isNotEmpty()) ViewType.VIEW_TYPE_NORMAL else  ViewType.VIEW_TYPE_EMPTY

    open fun addItems(repoList: List<T>) {
        data.addAll(repoList)
        notifyDataSetChanged()
    }

    fun dataSetChangedAt(position: Int, model: T) {
        data.set(position, model)
        notifyItemChanged(position)
    }

    fun itemRangeChanged(
        itemCountChanged: Int,
        child: List<T>
    ) {
        val start = itemCount
        data.addAll(child)
        notifyItemRangeChanged(start, itemCountChanged)
    }


    fun itemInsertedAtIndex(
        insertionIndex: Int,
        child: List<T>
    ) {

   /*     data.set(insertionIndex, child[0])
       // notifyItemInserted(insertionIndex)
        notifyItemChanged(insertionIndex)*/

        val product = data[insertionIndex]
        val indexOfItem = data.indexOf(child[0])

        data.set(insertionIndex, child[0])
        data.set(indexOfItem, product)

        notifyItemChanged(insertionIndex)
        notifyItemChanged(indexOfItem)
    }


    fun clearItems() {
        data.clear()
    }

    fun getData(): List<T?> {
        return data
    }


    fun updateByDiffUtil(newList: List<T>){
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(this.data, newList))
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onViewDetachedFromWindow(holder: K) {
        holder.viewDetachedFromWindow()
        super.onViewDetachedFromWindow(holder)
    }


    // Empty ViewHolder
     inner class EmptyLeadViewHolder(private val mBinding: ErrorLayoutBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(model: T?) {
            mEmptyBinding = mBinding
            mBinding.errorText.apply {
                visibility = View.VISIBLE
                text = itemView.context.resources.getString(R.string.content_not_found)
            }
            mBinding.executePendingBindings()
        }

        override fun viewDetachedFromWindow() {}

    }


    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun onBind(model: T?)

        abstract fun viewDetachedFromWindow()

    }

    inner class MyDiffCallback(private val newList: List<T>, private val oldList: List<T>) :
        DiffUtil.Callback() {

         override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

         override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
             customContentsTheSame(oldItemPosition, newItemPosition, oldList, newList)

         override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
             customItemsTheSame(oldItemPosition, newItemPosition, oldList, newList)

     }

     abstract fun customContentsTheSame(oldItemPosition: Int, newItemPosition: Int, oldList: List<T>, newList: List<T>): Boolean

     abstract  fun customItemsTheSame(oldItemPosition: Int, newItemPosition: Int, oldList: List<T>, newList: List<T>): Boolean

}