package com.farhanrv.submission2githubuser.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farhanrv.submission2githubuser.Model.ModelFollowItem;
import com.farhanrv.submission2githubuser.databinding.ItemFollowListBinding;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {

    private final ArrayList<ModelFollowItem> modelFollowItemsArrayList = new ArrayList<>();
    private final Context context;

    public FollowAdapter(Context context) {
        this.context = context;
    }

    public void setFollowList(ArrayList<ModelFollowItem> itemList) {
        modelFollowItemsArrayList.clear();
        modelFollowItemsArrayList.addAll(itemList);
        notifyDataSetChanged();
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
        ModelFollowItem item = modelFollowItemsArrayList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.binding.imageUser);

        holder.binding.tvName.setText(item.getLogin());
    }

    @Override
    public int getItemCount() {
        return modelFollowItemsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemFollowListBinding binding;

        public ViewHolder(@NonNull ItemFollowListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
