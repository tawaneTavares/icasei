package com.example.icasei.domain.usecases

import com.example.icasei.common.State
import com.example.icasei.data.remote.dto.SearchModel
import com.example.icasei.domain.repository.IYoutubeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetSearchUseCase @Inject constructor(private val repository: IYoutubeRepository) {

    fun execute(text: String): Flow<State<SearchModel>> {
        return getSearch(text)
            .onStart {
                emit(State.loading())
            }
    }

    private fun getSearch(text: String): Flow<State<SearchModel>> {
        return repository.getSearch(text)
            .map { State.data(it) }
            .catch { throwable ->
                emit(
                    State.error(throwable),
                )
            }
    }
}
