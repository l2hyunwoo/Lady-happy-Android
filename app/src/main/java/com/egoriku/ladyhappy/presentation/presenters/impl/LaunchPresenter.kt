package com.egoriku.ladyhappy.presentation.presenters.impl

import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.ladyhappy.common.Screens
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants
import com.egoriku.ladyhappy.presentation.presenters.LaunchContract
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LaunchPresenter
@Inject constructor(private val router: Router, private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<LaunchContract.View>(), LaunchContract.Presenter {

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        analyticsInterface.trackPageView(TrackingConstants.ACTIVITY_LAUNCH)
    }

    override fun processOpeningApp() {
        router.newRootScreen(Screens.MAIN_ACTIVITY)
    }

    override fun onBackPressed() {
        router.exit()
    }
}
