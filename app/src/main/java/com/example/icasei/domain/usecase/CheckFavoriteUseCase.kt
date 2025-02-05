package com.example.icasei.domain.usecase

import com.example.icasei.common.State
import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class CheckFavoriteUseCase
@Inject
constructor(
    private val repository: IYoutubeRepository,
) {

    fun execute(videoId: String): Flow<State<Boolean>> {
        return checkFavorite(videoId).onStart { emit(State.loading()) }
    }

    private fun checkFavorite(videoId: String): Flow<State<Boolean>> {
        return repository.checkFavorite(videoId)
            .map { State.data(it) }
            .catch { throwable ->
                emit(State.error(throwable))
            }
    }
}
