package shaurya.uberintegration.REST;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;
import shaurya.uberintegration.Model.RequestPOJO;

/**
 * Created by maver_000 on 4/5/2016.
 */
public interface RestAPI {
    @GET("/v1/products")
    Call<ProductsResponse> load_products(@Header("Authorization") String authToken,
                                         @Query("latitude") double latitude,
                                         @Query("longitude") double longitude);

    @GET("/v1/estimates/price")
    Call<EstimateResponse> load_estimate(@Header("Authorization") String authToken,
                                         @Query("start_latitude") double start_latitude,
                                         @Query("start_longitude") double start_longitude,
                                         @Query("end_latitude") double end_latitude,
                                         @Query("end_longitude")  double end_logitude);

    @POST("/v1/requests")
    Call<RequestPOJO> getRequest(@Header("Authorization") String authToken,
                    @Body HashMap<String, String> obj);
}
