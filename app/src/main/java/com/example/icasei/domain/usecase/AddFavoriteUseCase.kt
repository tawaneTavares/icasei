package com.example.icasei.domain.usecase

import com.example.icasei.domain.model.Favorite
import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFavoriteUseCase
@Inject
constructor(
    private val repository: IYoutubeRepository,
) {

    operator fun invoke(favorite: Favorite) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addFavorite(favorite)
        }
    }
}
