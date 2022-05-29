package com.codinginflow.mvvm.api

import com.codinginflow.mvvm.data.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
) {
}