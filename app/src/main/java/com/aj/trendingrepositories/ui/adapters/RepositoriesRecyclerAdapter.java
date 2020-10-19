package com.aj.trendingrepositories.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositoriesRecyclerAdapter extends RecyclerView.Adapter<RepositoriesRecyclerAdapter.ViewHolder> implements Filterable {

    private List<RepositoriesTable> repositoriesTableList;
    private List<RepositoriesTable> repositoriesListAll;
    private Context context;

    public RepositoriesRecyclerAdapter(List<RepositoriesTable> repositoriesTableList, Context context) {
        this.repositoriesTableList = repositoriesTableList;
        this.context = context;
        this.repositoriesListAll = new ArrayList<>(repositoriesTableList);
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

//        holder.ll_languageColor.setBackgroundColor(Color.parseColor(repositoriesTableList.get(position).getLanguageColor()));

        GradientDrawable bgShape = (GradientDrawable)holder.ll_languageColor.getBackground();
        bgShape.setColor(Color.parseColor(repositoriesTableList.get(position).getLanguageColor()));

//        holder.ll_languageColor.setBackground(getR.drawable.circle_shape);


        Glide.with(context)
                .load(repositoriesTableList.get(position).getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());

    }

    @Override
    public int getItemCount() {
        return repositoriesTableList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<RepositoriesTable> filteredData = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredData.addAll(repositoriesListAll);
            } else {
                for (RepositoriesTable obj : repositoriesListAll) {
                    if (obj.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredData.add(obj);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredData;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            repositoriesTableList.clear();
            repositoriesTableList.addAll((Collection<? extends RepositoriesTable>) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
