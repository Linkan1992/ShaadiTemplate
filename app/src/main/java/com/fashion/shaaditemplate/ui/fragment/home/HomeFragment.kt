package com.fashion.shaaditemplate.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fashion.shaaditemplate.BR
import com.fashion.shaaditemplate.R
import com.fashion.shaaditemplate.ViewModelProviderFactory
import com.fashion.shaaditemplate.base.BaseFragment
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile
import com.fashion.shaaditemplate.databinding.FragmentHomeLayoutBinding
import com.fashion.shaaditemplate.ui.activity.main.MainViewModel
import com.fashion.shaaditemplate.ui.adapter.decorator.CustomItemDecorator
import com.fashion.shaaditemplate.ui.adapter.profileAdapter.CandidateProfileAdapter
import com.fashion.shaaditemplate.util.constUtil.AppConstants
import com.fashion.shaaditemplate.util.extension.Result
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, MainViewModel>(),
    CandidateProfileAdapter.StatusCallback {

    companion object {

        fun newInstance(): HomeFragment = HomeFragment().apply {
            arguments = Bundle().apply {

            }
        }

        val TAG = HomeFragment::class.java.simpleName
    }

    @Inject lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject lateinit var layoutManager: LinearLayoutManager

    @Inject lateinit var profileAdapter: CandidateProfileAdapter

    private val mainViewModel : MainViewModel by lazy {
        ViewModelProvider(mActivity, viewModelProviderFactory).get(MainViewModel::class.java)
    }

    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    override val viewModel: MainViewModel
        get() = mainViewModel

    override val bindingVariable: Int
        get() = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.loadCandidatesProfile()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.includedAppBar.title.text = resources.getString(R.string.choose_profile)
        subscribeToLiveData()
        setUpRecyclerView()
    }

    private fun subscribeToLiveData() {
        mainViewModel.mProfileLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Success -> {
                     mainViewModel.setDataList(it.data.results)
                     mActivity.showCustomSnackBar("API Success ${it.data.results}", true)
                }
                is Result.Error -> if(it.errorCode == AppConstants.HttpResCodes.STATUS_NO_INTERNET) makeSubscription(isLoading = true)
                else setResponseCode(responseCode = it.errorCode)
            }
        })

    }

    private fun makeSubscription(isLoading: Boolean) {
        mainViewModel.loadFromOfflineStorage(isLoading).observe(viewLifecycleOwner, Observer { it1 ->
            if (it1.isNullOrEmpty()) setResponseCode(responseCode = AppConstants.HttpResCodes.STATUS_NO_ITEMS_FOUND)
            else mainViewModel.setDataList(it1)
            mainViewModel.setLoading(false)
        })
    }

    private fun setUpRecyclerView() {

        layoutManager.orientation = RecyclerView.VERTICAL
        viewDataBinding.profileRecylerview.layoutManager = layoutManager
        viewDataBinding.profileRecylerview.itemAnimator = DefaultItemAnimator()
        viewDataBinding.profileRecylerview.addItemDecoration(
            CustomItemDecorator(
                resources.getDimension(R.dimen.dimes_8dp).toInt(),
                resources.getDimension(R.dimen.dimes_8dp).toInt()
            )
        )
        viewDataBinding.profileRecylerview.adapter = profileAdapter

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.button_retry){
            // make api retry call
            hideErrorPage()
            mainViewModel.loadCandidatesProfile()
        }
        super.onClick(v)
    }

    override fun updateStatus(model: Profile) {
        if(mainViewModel.loadFromOfflineStorage(isLoading = false).hasActiveObservers()) {}
        else makeSubscription(isLoading = false)

        mainViewModel.updateProfileInStore(model)
    }




}