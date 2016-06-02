package shaurya.uberintegration.Fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.uber.sdk.android.rides.auth.AccessTokenManager;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import shaurya.uberintegration.Adapter.EstimateAdapter;
import shaurya.uberintegration.REST.EstimateResponse;
import shaurya.uberintegration.Model.EstimatePOJO;
import shaurya.uberintegration.R;
import shaurya.uberintegration.REST.RestAPI;

/**
 * Created by Ishan Shaurya Jaiswal.
 */
public class Custom extends Fragment implements Callback<EstimateResponse>, GoogleApiClient.OnConnectionFailedListener {

    Context context;
    private GoogleApiClient mGoogleApiClient;
    private LatLng latLng = new LatLng(0.0,0.0);
    private LatLng currentlocation = new LatLng(0.0,0.0);
    RecyclerView recyclerView;
    EstimateAdapter estimateAdapter;
    private ArrayList<EstimatePOJO> mlist;
    ProgressBar progressBar;

    public Custom() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_layout, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        mGoogleApiClient = new GoogleApiClient
                .Builder(context)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();
        getCurrentLocation();
        progressBar= (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                latLng = place.getLatLng();
                if(currentlocation.latitude!=0.0) {
                    progressBar.setVisibility(View.VISIBLE);
                    callRetrofit(latLng);
                }
                else {
                    Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.d("TAG", "An error occurred: " + status);
            }
        });
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        estimateAdapter=new EstimateAdapter(context,currentlocation,latLng);
        recyclerView.setAdapter(estimateAdapter);
        LinearLayoutManager linear_layout_manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linear_layout_manager);
    }

    @Override
    public void onResponse(Response<EstimateResponse> response, Retrofit retrofit) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    public void callRetrofit(LatLng lat){
        AccessTokenManager accessTokenManager = new AccessTokenManager(context);
        String t = "Bearer " + accessTokenManager.getAccessToken().getToken();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.uber.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI restAPI = retrofit.create(RestAPI.class);
        final Call<EstimateResponse> call = restAPI.load_estimate(t, currentlocation.latitude, currentlocation.longitude,
                lat.latitude,lat.longitude);
        call.enqueue(new Callback<EstimateResponse>() {
            @Override
            public void onResponse(Response<EstimateResponse> response, Retrofit retrofit) {
                Log.d("message",response.raw().toString());
                if(!response.body().prices.isEmpty()) {
                    mlist = (ArrayList<EstimatePOJO>) response.body().prices;
                    estimateAdapter=new EstimateAdapter(context,currentlocation,latLng);
                    recyclerView.setAdapter(estimateAdapter);
                    estimateAdapter.setEstimateList(mlist);
                    progressBar.setVisibility(View.GONE);
                }
                else{
                 Toast.makeText(context,"Sorry! No Uber Available Right Now",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        else {
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                    .getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                    currentlocation=likelyPlaces.get(0).getPlace().getLatLng();
                    likelyPlaces.release();
                }
            });
            Log.d("main",currentlocation.toString());
        }
    }
}
