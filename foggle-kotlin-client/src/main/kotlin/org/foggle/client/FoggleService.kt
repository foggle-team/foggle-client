package org.foggle.client

import org.foggle.core.dto.FeatureDto
import retrofit2.Call
import retrofit2.http.GET

interface FoggleService {
    @GET("api/client/features/enabled")
    fun enabledFeatures(): Call<List<FeatureDto>>
}
