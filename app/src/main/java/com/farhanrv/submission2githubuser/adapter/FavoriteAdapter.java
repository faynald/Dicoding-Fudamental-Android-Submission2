package com.farhanrv.submission2githubuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farhanrv.submission2githubuser.databinding.ItemGithubUserBinding;
import com.farhanrv.submission2githubuser.helper.FavoriteDiffCallback;
import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.ui.detail.DetailActivity;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final ArrayList<ModelUserItem> items = new ArrayList<>();
    Context context;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setListUser(ArrayList<ModelUserItem> items) {
        final FavoriteDiffCallback diffCallback = new FavoriteDiffCallback(this.items, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.items.clear();
        this.items.addAll(items);
        diffResult.dispatchUpdatesTo(this);
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
        holder.bind(items.get(position), context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemGithubUserBinding binding;
        public ViewHolder(@NonNull ItemGithubUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ModelUserItem item, Context context) {
            binding.tvGithubUsername.setText(item.getLogin());
            Glide.with(binding.getRoot())
                    .load(item.getAvatarUrl())
                    .into(binding.imgAvatar);
            binding.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DETAIL_USER, item);
                context.startActivity(intent);
            });
        }
    }
}
