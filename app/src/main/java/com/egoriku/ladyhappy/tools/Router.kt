package com.egoriku.ladyhappy.tools

import com.egoriku.core.di.utils.IRouter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class Router
@Inject constructor(private val router: Router) : IRouter {

    override fun navigateTo(screen: String?) = router.navigateTo(screen)

    override fun newRootScreen(screen: String?) = router.newRootScreen(screen)

    override fun exit() = router.exit()
}