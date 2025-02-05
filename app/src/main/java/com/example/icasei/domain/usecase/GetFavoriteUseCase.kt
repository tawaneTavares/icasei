package com.example.icasei.domain.usecase

import com.example.icasei.common.State
import com.example.icasei.domain.model.Favorite
import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetFavoriteUseCase
@Inject
constructor(
    private val repository: IYoutubeRepository,
) {

    fun execute(): Flow<State<List<Favorite>>> {
        return getFavorites().onStart { emit(State.loading()) }
    }

    private fun getFavorites(): Flow<State<List<Favorite>>> {
        return repository.getFavorites()
            .map { State.data(it) }
            .catch { throwable ->
                emit(State.error(throwable))
            }
    }
}
