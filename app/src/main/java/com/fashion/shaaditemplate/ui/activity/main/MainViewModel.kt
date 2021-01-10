package com.fashion.shaaditemplate.ui.activity.main

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import com.fashion.shaaditemplate.base.BaseViewModel
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.Profile
import com.fashion.shaaditemplate.data.entiity.api.candidateProfile.ResultResponse
import com.fashion.shaaditemplate.data.network.ApiHelper
import com.fashion.shaaditemplate.data.persistence.db.DbHelper
import com.fashion.shaaditemplate.di.annotation.CoroutineScopeIO
import com.fashion.shaaditemplate.util.constUtil.AppConstants
import com.fashion.shaaditemplate.util.extension.StateLiveData
import com.fashion.shaaditemplate.util.extension.applyResultTransformation
import kotlinx.coroutines.CoroutineScope
import com.fashion.shaaditemplate.util.extension.Result
import kotlinx.coroutines.launch

class MainViewModel(
    private val dbHelper: DbHelper,
    private val apiHelper: ApiHelper,
    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : BaseViewModel() {

    private val profileLiveData: StateLiveData<ResultResponse> by lazy { StateLiveData<ResultResponse>() }

    val mProfileLiveData: LiveData<Result<ResultResponse>>
        get() = profileLiveData.applyResultTransformation {  createResultData(result = it) }

    private val observableProfileList : ObservableArrayList<Profile> = ObservableArrayList<Profile>()

    val mObservableProfileList : ObservableArrayList<Profile>
        get() = observableProfileList

    fun loadCandidatesProfile() {
        ioCoroutineScope.launch {
            profileLiveData.postLoading()
            apiHelper.fetchProfileDataList().also {
                when(it){
                    is Result.Success -> {
                        if (it.data.results.isNullOrEmpty()) profileLiveData.postError( errorCode = AppConstants.HttpResCodes.STATUS_NO_ITEMS_FOUND)
                        else {
                            profileLiveData.postSuccess(it.data)
                            /**
                             * synchronous call to db first clear and then insert
                             */
                            dbHelper.clearAllToStore()
                            dbHelper.insertProfileListInStore(it.data.results)
                        }
                    }
                    is Result.Error -> profileLiveData.postError( errorCode = it.errorCode, errorMessage = it.message)
                }
            }
        }
    }

    fun setDataList(results: List<Profile>?) {
        observableProfileList.clear()
        observableProfileList.addAll(results ?: ArrayList<Profile>())
    }

    fun loadFromOfflineStorage(isLoading: Boolean) = dbHelper.fetchProfileDataList().also {
        setLoading(isLoading)
    }


    fun updateProfileInStore(model: Profile) {
        ioCoroutineScope.launch {
            dbHelper.updateProfileInStore(model)
        }
    }


}