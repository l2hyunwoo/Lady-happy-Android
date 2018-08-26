package com.egoriku.landingfragment.presentation

import android.util.Log
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.models.ILandingModel
import com.egoriku.core.usecase.DefaultObserver
import com.egoriku.core.usecase.Params
import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.landingfragment.domain.interactors.LandingUseCase
import javax.inject.Inject

internal class LandingPagePresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper, private val landingUseCase: LandingUseCase)
    : BasePresenter<LandingPageContract.View>(), LandingPageContract.Presenter {

    private var screenModel: ILandingModel? = null

    override fun loadLandingData() {
        if (screenModel != null) {
            view.render(screenModel!!)
        } else {
            if (isViewAttached) {
                view.showLoading()
            }
            getLandingData()
        }
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    private fun getLandingData() {
        landingUseCase.execute(object : DefaultObserver<ILandingModel>() {
            override fun onNext(model: ILandingModel) {
                screenModel = model

                if (isViewAttached) {
                    view.hideLoading()
                    view.render(model)
                }
            }

            override fun onError(exception: Throwable) {
                screenModel = null

                if (isViewAttached) {
                    view.hideLoading()
                }
                Log.e(this@LandingPagePresenter.javaClass.simpleName, "Error", exception)
            }
        }, Params.EMPTY)
    }
}