package com.example.eldarwallet.ui.presentation.home

import com.example.eldarwallet.domain.Card


data class HomeUIState(
    val isLoading: Boolean = false,
    val cards: List<Card> = emptyList(),
    val isError: String = "",
)




