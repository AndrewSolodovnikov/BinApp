package com.sol.binapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiService {
    @GET("{binNumber}")
    fun getBin(@Path("binNumber") binNumber: String): Call<BIN>
}
