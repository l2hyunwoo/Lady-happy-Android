import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.andKapt
import com.egoriku.ext.withKapt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.feature")
    id("kotlin-kapt")
}

withProjects(
        Modules.arch,
        Modules.core,
        Modules.extensions,
        Modules.featureProvider,
        Modules.ui
)

withLibraries(
        Libs.appcompat,
        Libs.cicerone,
        Libs.constraintLayout,
        Libs.material
)

withKapt(Libs.dagger andKapt Libs.daggerCompiler)