package shaurya.ngenassignment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ishan Shaurya Jaiswal.
 */

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.SimpleViewHolder> {
    private List<Cuisine> dataSource;
    public SimpleRVAdapter(List<Cuisine> dataArgs){
        dataSource = dataArgs;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisine_item_row, parent, false);
        final SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),DishActivity.class);
                intent.putExtra("CUISINEID",dataSource.get(viewHolder.getAdapterPosition()).getId());
                intent.putExtra("CUISINENAME",dataSource.get(viewHolder.getAdapterPosition()).getName());
                parent.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtCuisineName);
        }
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(dataSource.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}