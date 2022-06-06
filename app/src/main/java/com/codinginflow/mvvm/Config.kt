package com.codinginflow.mvvm


import android.app.Application
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Config(
    @SerializedName("API_URL")
    val apiUrl: String = "",

    @SerializedName("FILE_URL")
    val fileUrl: String = "",

    @SerializedName("CONNECT_TIME_OUT")
    val connectTimeOut: Long = 10_000L,

    @SerializedName("READ_TIME_OUT")
    val readTimeOut: Long = 10_000L,

    @SerializedName("WRITE_TIME_OUT")
    val writeTimeOut: Long = 10_000L,

    @SerializedName("WEB_VIEW_URL")
    val webUrl: String = ""
){
    companion object {
        lateinit var INSTANCE: Config

        fun init(application: Application, path: String){
            val configJson = try {
                application.assets.open(path).bufferedReader().use {
                    it.readText()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
            INSTANCE = Gson().fromJson(configJson, Config::class.java)
        }
    }
}