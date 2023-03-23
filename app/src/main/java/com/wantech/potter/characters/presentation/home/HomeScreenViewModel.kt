package com.wantech.potter.characters.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.potter.characters.domain.repository.PotterCharactersRepository
import com.wantech.potter.characters.presentation.home.components.CharactersState
import com.wantech.potter.characters.presentation.home.components.PotterEvents
import com.wantech.potter.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: PotterCharactersRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CharactersState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), CharactersState())

    init {
        getCharacters()
    }

    fun onEvent(event: PotterEvents) {
        when (event) {
            is PotterEvents.GetCharacterInfor ->{

            }
            is PotterEvents.GetCharacters -> {
                getCharacters()
            }
            is PotterEvents.GetCharactersByHouses -> {
                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    repository.getCharactersByHouses(house = event.house)
                        .collectLatest { charResource ->
                            when (charResource) {
                                is Resource.Error -> {
                                    _state.update { it.copy(error = charResource.uiText) }
                                }
                                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                                is Resource.Success -> {
                                    charResource.data?.let { charactersItems ->
                                        _state.update { it.copy(charactersByHouses = charactersItems) }

                                    }
                                }
                            }

                        }
                }
                _state.update { it.copy(isLoading = false) }
            }
            is PotterEvents.GetCharactersByName -> {
                getCharsByName(filter = event.filter)
            }
            is PotterEvents.GetCharactersBySearch -> {

            }
        }
    }


    private fun getCharsByName(filter: String) {
        viewModelScope.launch {
            _state.value.characters.map {
                it.name.contains(filter) || it.name.lowercase().contains(filter.lowercase())
                        || it.name.contains(filter.lowercase())
            }
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getCharacters().collectLatest { characterResource ->
                when (characterResource) {
                    is Resource.Error -> {
                        _state.update { it.copy(error = characterResource.uiText) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        characterResource.data?.let { charactersItems ->
                            _state.update { it.copy(characters = charactersItems) }
                        }
                    }
                }

            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}