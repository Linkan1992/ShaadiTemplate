package com.fashion.shaaditemplate.util.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile
import com.fashion.shaaditemplate.ui.adapter.profileAdapter.CandidateProfileAdapter


@BindingAdapter("profileAdapter", "position")
fun bindProfileAdapter(
    recyclerView: RecyclerView, dataList : List<Profile>, position : Int
) {
    val adapter = recyclerView.adapter as CandidateProfileAdapter?
    adapter?.let {

        if(it.getData().isEmpty()) it.addItems(dataList) else if(position > -1){
            val apperantChangedModel = it.getData()[position]
            dataList.filter { it.cell == apperantChangedModel?.cell }.apply {
                it.dataSetChangedAt(position, this[0])
            }

        }

       // if(it.getData().isNotEmpty()) it.updateByDiffUtil(dataList) else it.addItems(dataList)
    }
}