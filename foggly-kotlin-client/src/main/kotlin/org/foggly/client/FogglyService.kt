package org.foggly.client

import org.foggly.core.dto.FeatureDto
import retrofit2.Call
import retrofit2.http.GET

interface FogglyService {
    @GET("api/client/features/enabled/flat")
    fun enabledFeatures(): Call<List<FeatureDto>>
}
