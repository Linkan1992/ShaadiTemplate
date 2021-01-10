package com.fashion.shaaditemplate.util.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile
import com.fashion.shaaditemplate.ui.adapter.profileAdapter.CandidateProfileAdapter


@BindingAdapter("profileAdapter")
fun bindProfileAdapter(
    recyclerView: RecyclerView, dataList : List<Profile>
) {
    val adapter = recyclerView.adapter as CandidateProfileAdapter?
    adapter?.let {
        it.clearItems()
        it.addItems(dataList)
    }
}