package com.example.icasei.domain.usecase

import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteFavoriteUseCase
@Inject
constructor(
    private val repository: IYoutubeRepository,
) {

    operator fun invoke(videoId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(videoId)
        }
    }
}
