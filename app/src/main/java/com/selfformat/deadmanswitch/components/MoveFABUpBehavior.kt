package com.selfformat.deadmanswitch.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Keep
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import kotlin.math.min

@Keep
class MoveFABUpBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = min(0f, dependency.translationY - dependency.height)
        child.animate().cancel()
        child.translationY = translationY
        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        child.animate().translationY(0f).start()
    }
}