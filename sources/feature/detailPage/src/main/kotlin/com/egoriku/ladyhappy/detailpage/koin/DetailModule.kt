package com.egoriku.ladyhappy.detailpage.koin

import com.egoriku.ladyhappy.detailpage.data.DetailDataSource
import com.egoriku.ladyhappy.detailpage.data.DetailPaginateRepository
import com.egoriku.ladyhappy.detailpage.domain.usecase.DetailUseCase
import com.egoriku.ladyhappy.detailpage.presentation.DetailPageFragment
import com.egoriku.ladyhappy.detailpage.presentation.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    scope<DetailPageFragment> {
        scoped { DetailDataSource(firebase = get()) }

        scoped { DetailPaginateRepository(dataSource = get()) }

        scoped { DetailUseCase(repository = get()) }

        viewModel { DetailViewModel(detailUseCase = get()) }
    }
}