package ir.hamidbazargan.daresayassignment.presentation.pages.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.usecase.UseCaseWithParam
import ir.hamidbazargan.daresayassignment.domain.usecase.UseCaseWithParamReturn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewmodel(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val getMovie: UseCaseWithParamReturn<Int, List<MovieEntity>>,
    private val saveMovie: UseCaseWithParam<MovieEntity>
) : ViewModel() {

    private var _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel> get() = _uiModel

    fun loadMovie(id: Int) {
        _uiModel.postValue(UiModel.Loading)
        viewModelScope.launch(coroutineDispatcher) {
            try {
                _uiModel.postValue(UiModel.Success(getMovie.execute(id).first()))
            } catch (e: Exception) {
                _uiModel.postValue(UiModel.Error(e.message.toString()))
            }
        }
    }

    fun saveMovie() {
        if(uiModel.value is UiModel.Success) {
            viewModelScope.launch(coroutineDispatcher) {
                try {
                    saveMovie.execute((uiModel.value as UiModel.Success).movie)
                    _uiModel.postValue(UiModel.Bookmark)
                }catch (e: java.lang.Exception) {
                    _uiModel.postValue(UiModel.Error(e.message.toString()))
                }
            }
        }
    }
}