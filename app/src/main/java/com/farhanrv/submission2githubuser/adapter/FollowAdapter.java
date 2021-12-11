package com.farhanrv.submission2githubuser.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farhanrv.submission2githubuser.databinding.ItemFollowListBinding;
import com.farhanrv.submission2githubuser.helper.FollowDiffCallback;
import com.farhanrv.submission2githubuser.model.ModelFollowItem;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {

    private final ArrayList<ModelFollowItem> items = new ArrayList<>();

    public FollowAdapter() {
    }

    public void setFollowList(ArrayList<ModelFollowItem> items) {
        final FollowDiffCallback diffCallback = new FollowDiffCallback(this.items, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.items.clear();
        this.items.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFollowListBinding binding = ItemFollowListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FollowAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelFollowItem item = items.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.binding.imageUser);
        holder.binding.tvName.setText(item.getLogin());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemFollowListBinding binding;

        public ViewHolder(@NonNull ItemFollowListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
