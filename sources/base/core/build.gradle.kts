import com.egoriku.dependencies.Libs
import com.egoriku.ext.withLibraries

plugins {
    id("com.egoriku.library")
}

withLibraries(
        Libs.cicerone,
        Libs.coroutinesAndroid,
        Libs.dagger,
        Libs.firestore
)