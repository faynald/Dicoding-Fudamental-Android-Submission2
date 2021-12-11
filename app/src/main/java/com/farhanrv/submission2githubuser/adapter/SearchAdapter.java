package com.farhanrv.submission2githubuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farhanrv.submission2githubuser.databinding.ItemGithubUserBinding;
import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.ui.detail.DetailActivity;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final ArrayList<ModelUserItem> items = new ArrayList<>();
    Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearchList(ArrayList<ModelUserItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGithubUserBinding binding = ItemGithubUserBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelUserItem item = items.get(position);
        holder.binding.tvGithubUsername.setText(item.getLogin());
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.binding.imgAvatar);
        holder.binding.getRoot().setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DETAIL_USER, items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemGithubUserBinding binding;
        public ViewHolder(@NonNull ItemGithubUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
