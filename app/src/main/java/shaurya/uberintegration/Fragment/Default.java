package shaurya.uberintegration.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uber.sdk.android.rides.RideRequestView;

import shaurya.uberintegration.R;

/**
 * Created by Ishan Shaurya Jaiswal.
 */
public class Default extends Fragment {

    public Default(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.default_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RideRequestView rideRequestView=(RideRequestView)view.findViewById(R.id.ub__ride_request_view);
        rideRequestView.load();
    }
}
