package com.farhanrv.submission2githubuser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farhanrv.submission2githubuser.Model.ModelSearchItem;
import com.farhanrv.submission2githubuser.UI.DetailActivity;
import com.farhanrv.submission2githubuser.databinding.ItemGithubUserBinding;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final ArrayList<ModelSearchItem> modelSearchItemArrayList = new ArrayList<>();
    private final Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setModelSearchItemArrayList(ArrayList<ModelSearchItem> items) {
        modelSearchItemArrayList.clear();
        modelSearchItemArrayList.addAll(items);
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
        ModelSearchItem item = modelSearchItemArrayList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.binding.imgAvatar);
        holder.binding.tvGithubName.setText(item.getLogin());
        holder.binding.getRoot().setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DETAIL_USER, modelSearchItemArrayList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return modelSearchItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemGithubUserBinding binding;
        public ViewHolder(@NonNull ItemGithubUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
