package com.fashion.shaaditemplate.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.fashion.shaaditemplate.R
import com.fashion.shaaditemplate.util.constUtil.AppConstants


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerAndroidxFragment(),
    View.OnClickListener {

    lateinit var mActivity: BaseActivity<*, *>

    var viewSwitcher: ViewSwitcher? = null

    lateinit var viewDataBinding: T
        private set

    var mViewModel: V? = null

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        setHasOptionsMenu(false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity<*, *>){
            mActivity = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewSwitcher = ViewSwitcher(mActivity)
        val view = viewDataBinding.root
        val errorLayout = inflater.inflate(R.layout.error_layout, container, false) as ViewGroup
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        viewSwitcher?.layoutParams = params
        viewSwitcher?.addView(view)
        viewSwitcher?.addView(errorLayout)
        return viewSwitcher
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.setVariable(bindingVariable, mViewModel)
        viewDataBinding.executePendingBindings()
    }


    fun setResponseCode(
        responseCode: Int? = AppConstants.HttpResCodes.STATUS_INTERNAL_ERROR,
        message: String? = null,
        switchFrame: Boolean = false
    ) {
        when (responseCode) {
            AppConstants.HttpResCodes.STATUS_NO_ITEMS_FOUND -> {
                //all normal
            }
            AppConstants.HttpResCodes.STATUS_NOT_FOUND -> {
                showErrorPage(
                    R.drawable.ic_system_empty,
                    message ?: getString(R.string.content_not_found)
                )
            }
            AppConstants.HttpResCodes.STATUS_UNAUTHORIZED -> {
                showErrorPage(
                    R.drawable.ic_system_empty,
                    message ?: getString(R.string.request_unauthorized)
                )
            }
            AppConstants.HttpResCodes.STATUS_SERVER_ERROR or AppConstants.HttpResCodes.STATUS_INTERNAL_ERROR -> {
                showErrorPage(
                    R.drawable.ic_system_empty,
                    message ?: getString(R.string.internal_server_error)
                )
            }
            AppConstants.HttpResCodes.STATUS_NO_INTERNET -> {
                showErrorPage(
                    R.drawable.error_no_internet,
                    message ?: getString(R.string.network_error)
                )
            }

        }
    }


    protected fun showErrorPage(resId: Int, message: String, isFromCart: Boolean = false) {
        viewSwitcher?.displayedChild = 1
        val parentView = viewSwitcher?.getChildAt(1)

        if (null != parentView) {
            if (resId == -1)
                parentView.findViewById<View>(R.id.error_image).visibility = View.GONE
            else
                (parentView.findViewById<View>(R.id.error_image) as ImageView).setImageResource(
                    resId
                )

            val errorTextView = parentView.findViewById<View>(R.id.error_text) as TextView
            val errorSecondaryTextView =
                parentView.findViewById<View>(R.id.error_secondary_text) as TextView
            val progressBar = parentView.findViewById<View>(R.id.progress_bar_error_page)
            val buttonRetry = parentView.findViewById<View>(R.id.button_retry) as Button

            if (null != progressBar) progressBar.visibility = View.GONE

            errorTextView.apply {

                setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))

                text = message

             //   setTextSize(TypedValue.COMPLEX_UNIT_PX, 14F)
                visibility = if (message.isEmpty()) View.GONE else View.VISIBLE
            }

            buttonRetry.apply {

                /*ThemeFunctions.applyRippleDrawabletoView(
                    backgroundView = buttonRetry,
                    applicationContext = activity,
                    buttonType = ThemeTypeConstants.PRIMARY
                )*/

                text = context.getString(R.string.retry)
                val padding = requireActivity().resources.getDimension(R.dimen.dimes_32dp).toInt()

                val params = RelativeLayout.LayoutParams(
                    if (isFromCart) RelativeLayout.LayoutParams.MATCH_PARENT else RelativeLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
                layoutParams = params

                setOnClickListener(this@BaseFragment)
            }

        }
    }


    fun hideErrorPage() {
        viewSwitcher?.displayedChild = 0
    }

    override fun onClick(v: View?) {

    }

}