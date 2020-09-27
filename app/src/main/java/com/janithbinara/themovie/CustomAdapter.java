package com.janithbinara.themovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.janithbinara.themovie.models.Result;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Result> dataList;
    private Context context;
    private int value;

    public CustomAdapter(Context placeholderFragment, List<Result> data, int i) {
        this.context = placeholderFragment;
        this.dataList = data;
        this.value = i;

    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            coverImage = mView.findViewById(R.id.coverImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {

                if (value==-111)
                {
                    Intent intent = new Intent(context, SecondActivity.class);
                    intent.putExtra("backdropPath", dataList.get(position).
                            getBackdropPath());
                    intent.putExtra("title", dataList.get(position).getTitle());
                    intent.putExtra("overview", dataList.get(position).getOverview());
                    intent.putExtra("voteAverage", String.valueOf(dataList.get(position).
                            getVoteAverage()));
                    intent.putExtra("releaseDate", dataList.get(position).getReleaseDate());
                    intent.putExtra("originalLanguage", dataList.get(position).
                            getOriginalLanguage());
                    intent.putExtra("originalTitle", dataList.get(position).
                            getOriginalTitle());
                    intent.putExtra("popularity", String.valueOf(dataList.get(position).
                            getPopularity()));
                    intent.putExtra("voteCount", String.valueOf(dataList.get(position).
                            getVoteCount()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            }
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = null;

        if (value==-111)
        {
            view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        }

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        if(value==-111)
        {
            String imagePath = "https://image.tmdb.org/t/p/w500"+dataList.get(position).
                    getPosterPath();
            Glide.with(context)
                    .load(imagePath)
                    .placeholder(R.drawable.main_image)
                    .error(R.drawable.main_image)
                    .into(holder.coverImage);
        }
    }

    @Override
    public int getItemCount() {

        int val = 0;

        if (value == -111) {
            val=dataList.size();
        }

        return val;
    }

}