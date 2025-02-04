package com.example.icasei.domain.usecase

import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(private val repository: IYoutubeRepository) {

    operator fun invoke(text: String) = repository.getSearch(text)
}
