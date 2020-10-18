package com.aj.trendingrepositories.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aj.trendingrepositories.R;
import com.aj.trendingrepositories.db.tables.RepositoriesTable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class RepositoriesRecyclerAdapter extends RecyclerView.Adapter<RepositoriesRecyclerAdapter.ViewHolder> {

    private List<RepositoriesTable> repositoriesTableList;
    private Context context;

    public RepositoriesRecyclerAdapter(List<RepositoriesTable> repositoriesTableList, Context context) {
        this.repositoriesTableList = repositoriesTableList;
        this.context = context;
    }

    @NonNull
    @Override
    public RepositoriesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repositories_single_item, parent, false);
        return new RepositoriesRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesRecyclerAdapter.ViewHolder holder, int position) {
        holder.tv_authorName.setText(repositoriesTableList.get(position).getAuthor());
        holder.tv_repoName.setText(repositoriesTableList.get(position).getName());
        holder.tv_description.setText(repositoriesTableList.get(position).getDescription());
        holder.tv_language.setText(repositoriesTableList.get(position).getLanguage());
        holder.tv_stars.setText(String.valueOf(repositoriesTableList.get(position).getStars()));

        holder.ll_languageColor.setBackgroundColor(Color.parseColor(repositoriesTableList.get(position).getLanguageColor()));


        Glide.with(context)
                .load(repositoriesTableList.get(position).getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());

    }

    @Override
    public int getItemCount() {
        return repositoriesTableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_authorName;
        TextView tv_repoName;
        TextView tv_description;
        TextView tv_language;
        TextView tv_stars;
        ImageView iv_avatar;
        LinearLayout ll_languageColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_authorName = itemView.findViewById(R.id.tv_authorName);
            tv_repoName = itemView.findViewById(R.id.tv_repoName);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_language = itemView.findViewById(R.id.tv_language);
            tv_stars = itemView.findViewById(R.id.tv_stars);
            ll_languageColor = itemView.findViewById(R.id.ll_languageColor);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
        }

        public ImageView getImage() {
            return this.iv_avatar;
        }
    }
}
