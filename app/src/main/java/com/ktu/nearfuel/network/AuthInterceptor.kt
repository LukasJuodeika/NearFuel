package com.ktu.nearfuel.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.String

class AuthInterceptor(
    private val authRepository: AuthRepository
) : Interceptor {
    private val HEADER_AUTHORIZATION = "Authorization"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        val requestWithAuth = withAuthHeader(request.newBuilder())
            .build()
        val response = chain.proceed(requestWithAuth)
        return if (response.code() == 401) {
            response.close()
            authRepository.setAccessToken("")
            response
//            if (tokenManager.refreshAccessToken()) {
//                val newRequest = chain.request()
//                val newRequestWithAuth = withAuthHeader(newRequest.newBuilder())
//                    .build()
//                chain.proceed(newRequestWithAuth)
//            } else {
//                androidBus.postOnMain(LogoutEvent())
//                response
//            }
        } else {
            response
        }
    }

    private fun withAuthHeader(builder: Request.Builder): Request.Builder {
        return builder.header(
            HEADER_AUTHORIZATION,
            String.format("Bearer %s", authRepository.getAccessToken())
        )
    }
}