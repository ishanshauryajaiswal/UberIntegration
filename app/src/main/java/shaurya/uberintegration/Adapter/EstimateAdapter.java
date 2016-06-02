package shaurya.uberintegration.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.uber.sdk.android.rides.auth.AccessTokenManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import shaurya.uberintegration.Model.EstimatePOJO;
import shaurya.uberintegration.Model.RequestPOJO;
import shaurya.uberintegration.R;
import shaurya.uberintegration.REST.RestAPI;

/**
 * Created by maver_000 on 4/6/2016.
 */
public class EstimateAdapter extends RecyclerView.Adapter<EstimateAdapter.ViewHolder> {

    private List<EstimatePOJO> mEstimateList=new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    private LatLng pick_latLng,dest_latlang;

    public EstimateAdapter(Context context,LatLng pick_latLng,LatLng dest_latlang) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.pick_latLng = pick_latLng;
        this.dest_latlang = dest_latlang;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.row_layout,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= viewHolder.getAdapterPosition();
                EstimatePOJO temp=mEstimateList.get(position);
                book_ride(temp.getProduct_id());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EstimatePOJO estimatePOJO=mEstimateList.get(position);
        holder.textView_name.setText(estimatePOJO.getDisplay_name());
        holder.textView_fare.setText(estimatePOJO.getEstimate());
        holder.textView_eta.setText(estimatePOJO.getDuration()/60+" Minutes");
    }

    @Override
    public int getItemCount() {

        return (mEstimateList == null) ? 0 : mEstimateList.size();
    }

    public void setEstimateList(List<EstimatePOJO> movieList) {
            this.mEstimateList=new ArrayList<>();
            this.mEstimateList.addAll(movieList);
            notifyDataSetChanged();
    }

    public void book_ride(String ProductId){
        AccessTokenManager accessTokenManager = new AccessTokenManager(mContext);
        String t = "Bearer " + accessTokenManager.getAccessToken().getToken();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.uber.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI restAPI = retrofit.create(RestAPI.class);
        HashMap<String, String> obj=new HashMap<String, String>();
        obj.put("product_id", ProductId);
        obj.put("start_latitude", String.valueOf(pick_latLng.latitude));
        obj.put("start_longitude", String.valueOf(pick_latLng.longitude));
        obj.put("end_latitude", String.valueOf(dest_latlang.latitude));
        obj.put("end_longitude", String.valueOf(dest_latlang.longitude));

        Call<RequestPOJO> call=restAPI.getRequest(t,obj);
        call.enqueue(new Callback<RequestPOJO>() {
            @Override
            public void onResponse(Response<RequestPOJO> response, Retrofit retrofit) {
                Toast.makeText(mContext,response.raw().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView_name;
        public TextView textView_fare;
        public TextView textView_eta;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_name=(TextView) itemView.findViewById(R.id.textView_name);
            textView_fare=(TextView)itemView.findViewById(R.id.textView_fare);
            textView_eta=(TextView)itemView.findViewById(R.id.textView_eta);
        }
    }
}