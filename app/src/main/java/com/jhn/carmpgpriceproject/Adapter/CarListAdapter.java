package com.jhn.carmpgpriceproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jhn.carmpgpriceproject.R;
import com.jhn.carmpgpriceproject.model.CarListRes;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    Context context;
    List<CarListRes> CarList;

    public CarListAdapter(Context context, List<CarListRes> itemList) {
        this.context = context;
        this.CarList = itemList;
    }

    public interface OnItemClickListener {
        void onItemClick(int index);
    }

    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarListRes item = CarListRes.get(position);
        holder.txtTitle.setText((item.getSnippet()).title);
        if((item.getSnippet()).thumbnails.def.url!=null) {
            Glide.with(context).load((item.getSnippet()).thumbnails.def.url).into(holder.imgPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return CarList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPhoto;
        private TextView txtTitle;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imageView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            cardView = itemView.findViewById(R.id.recyclerView_cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    if(listener!=null) listener.onItemClick(index);
                }
            });
        }
    }
}