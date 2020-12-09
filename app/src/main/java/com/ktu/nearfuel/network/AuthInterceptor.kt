package com.ktu.nearfuel.network

import android.content.Context
import android.content.Intent
import com.ktu.nearfuel.login.views.AuthenticationActivity
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.String

class AuthInterceptor(
    private val authRepository: AuthRepository,
    private val context: Context
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
            val intent = Intent(context, AuthenticationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
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
        val token = authRepository.getAccessToken()
        return if (token.isNotEmpty())
            builder.header(
                HEADER_AUTHORIZATION,
                String.format("Bearer %s", authRepository.getAccessToken())
            )
        else
            builder
    }
}