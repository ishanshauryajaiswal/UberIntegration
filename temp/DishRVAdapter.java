package shaurya.ngenassignment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ishan Shaurya Jaiswal.
 */

public class DishRVAdapter extends RecyclerView.Adapter<DishRVAdapter.DishViewHolder> {
private List<Dish> dataSource;
public DishRVAdapter(List<Dish> dataArgs){
        dataSource = dataArgs;
        }

@Override
public DishViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item_row, parent, false);
        final DishViewHolder viewHolder = new DishViewHolder(view);
        return viewHolder;
        }

public static class DishViewHolder extends RecyclerView.ViewHolder{
    public TextView textView,q;
    ImageView p,m;
    public DishViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.txtDishName);
        q = (TextView) itemView.findViewById(R.id.quantity);
        p = (ImageView) itemView.findViewById(R.id.plus);
        m = (ImageView) itemView.findViewById(R.id.minus);
    }
}

    @Override
    public void onBindViewHolder(final DishRVAdapter.DishViewHolder holder, int position) {
        holder.textView.setText(dataSource.get(position).getName());
        holder.p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quant = Integer.parseInt(String.valueOf(holder.q.getText().toString().trim()));
                if (quant<=10)
                    quant++;
                else
                    Toast.makeText(view.getContext(),"NOOOOO",Toast.LENGTH_SHORT);
                holder.q.setText(quant+"");
            }
        });

        holder.m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quant = Integer.parseInt(String.valueOf(holder.q.getText().toString().trim()));
                if (quant>0)
                    quant--;
                else
                    Toast.makeText(view.getContext(),"NOOOO Negative",Toast.LENGTH_SHORT);
                holder.q.setText(quant+"");
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}