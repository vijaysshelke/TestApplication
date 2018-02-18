package com.helloworld.helloworld;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by VijayVijay on 20-01-2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolderProduct> {
    private List<Product> list;
    private Context context;

    public ProductAdapter(List<Product> list) {
        this.list = list;
    }

    @Override
    public ViewHolderProduct onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_view, parent, false);
        return new ViewHolderProduct(itemView);
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvPrice;

        public ViewHolderProduct(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView textView = view.findViewById(R.id.tvPrice);
                    Log.i(TAG, "onClick: " + textView.getText().toString());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ViewHolderProduct holder, int position) {
        Product product = list.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
