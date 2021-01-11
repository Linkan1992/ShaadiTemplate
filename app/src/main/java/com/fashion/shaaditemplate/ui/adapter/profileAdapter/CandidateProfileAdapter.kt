package com.fashion.shaaditemplate.ui.adapter.profileAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fashion.shaaditemplate.R
import com.fashion.shaaditemplate.base.BaseRecyclerViewAdapter
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile
import com.fashion.shaaditemplate.databinding.AdapterProfileCardBinding

class CandidateProfileAdapter(
    private val dataList : MutableList<Profile>, private val statusCallback: StatusCallback
) : BaseRecyclerViewAdapter<Profile, CandidateProfileAdapter.ProfileHolder>(dataList){

    private val adapterLiveData : MutableLiveData<Profile> by lazy { MutableLiveData<Profile>() }

    val mAdapterLiveData : LiveData<Profile>
        get() = adapterLiveData

    override fun mOnCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateProfileAdapter.ProfileHolder
            =  ProfileHolder(
        AdapterProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

     inner class ProfileHolder(private val binding: AdapterProfileCardBinding) : BaseViewHolder(binding.root) {

        override fun onBind(model: Profile?) {
            // implement common code like click action
            binding.executePendingBindings()
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.

            model?.apply {
                binding.imgPhoto.setImageURI(this.pictureModel?.large, itemView.context)
                binding.tvName.text = "${this.nameModel?.first ?: ""} ${this.nameModel?.last ?: ""}"
                binding.tvAge.text = "${this.dobModel?.age ?: ""}, ${this.locationModel?.city ?: ""}"
                binding.tvState.text = "${this.locationModel?.state ?: ""}, ${this.locationModel?.country ?: ""}"
                if(this.idModel?.value.isNullOrEmpty()) binding.tvId.visibility = View.GONE else binding.tvId.text = "${this.idModel?.value}"

                when{
                    acceptanceStatus.isNullOrEmpty() -> {
                        binding.grpButton.visibility = View.VISIBLE
                        binding.tvMessage.visibility = View.GONE
                    }
                    acceptanceStatus == "ACCEPT" -> {
                        binding.grpButton.visibility = View.GONE
                        binding.tvMessage.visibility = View.VISIBLE
                        binding.tvMessage.text = itemView.context.resources.getString(R.string.profile_accepted)
                    }
                    acceptanceStatus == "REJECT" -> {
                        binding.grpButton.visibility = View.GONE
                        binding.tvMessage.visibility = View.VISIBLE
                        binding.tvMessage.text = itemView.context.resources.getString(R.string.profile_rejected)
                    }
                }

            }

            binding.apply {
                buttonAccept.setOnClickListener {
                    model?.apply {
                        statusCallback.updateStatus(this.run {
                        acceptanceStatus = "ACCEPT"
                        this
                    }, adapterPosition) }
                }

                buttonReject.setOnClickListener {
                    model?.apply {
                        statusCallback.updateStatus(this.run {
                            acceptanceStatus = "REJECT"
                            this
                        }, adapterPosition) }
                }
            }
        }

        override fun viewDetachedFromWindow() {}

    }


    interface StatusCallback{

        fun updateStatus(model: Profile, position : Int)

    }

    override fun customItemsTheSame(oldItemPosition: Int, newItemPosition: Int, oldList: List<Profile>, newList: List<Profile>): Boolean =
    oldList[oldItemPosition].cell == newList[newItemPosition].cell


    override fun customContentsTheSame(oldItemPosition: Int, newItemPosition: Int, oldList: List<Profile>, newList: List<Profile>): Boolean =
        oldList[oldItemPosition].acceptanceStatus == newList[newItemPosition].acceptanceStatus




}