package com.example.icasei.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icasei.common.State
import com.example.icasei.domain.model.Favorite
import com.example.icasei.domain.model.SearchItem
import com.example.icasei.domain.usecase.AddFavoriteUseCase
import com.example.icasei.domain.usecase.CheckFavoriteUseCase
import com.example.icasei.domain.usecase.DeleteFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
) : ViewModel() {

    private val _isFavorite = MutableStateFlow<State<Boolean?>>(State.idle())
    val isFavorite = _isFavorite.asStateFlow()

    fun checkFavorite(videoId: String) {
        viewModelScope.launch {
            checkFavoriteUseCase.execute(videoId)
                .collect {
                    _isFavorite.emit(it)
                }
        }
    }

    fun updateFavorite(isFavorite: Boolean, videoItem: SearchItem) = viewModelScope.launch {
        if (isFavorite) {
            addFavoriteUseCase(Favorite(videoItem.snippet.title, videoItem.id.videoId, videoItem.snippet.thumbnails.high.url))
        } else {
            deleteFavoriteUseCase(videoItem.snippet.title)
        }
    }
}
