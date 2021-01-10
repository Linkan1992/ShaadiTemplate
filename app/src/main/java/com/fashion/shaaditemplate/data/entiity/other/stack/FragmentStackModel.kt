package com.fashion.shaaditemplate.data.entiity.other.stack

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes

data class FragmentStackModel (

    var TAG : String,

    @get:IdRes var container_view : Int,

    @get:AnimRes @get:AnimatorRes var enterAnimation : Int,

    @get:AnimRes @get:AnimatorRes var exitAnimation : Int

)