package com.androidchill.niki.dagger.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidchill.niki.dagger.R;
import com.androidchill.niki.dagger.model.Response;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardMountsAdapter extends RecyclerView.Adapter<CardMountsAdapter.ViewHolder> {
    List<Response> mMounts;

    public CardMountsAdapter() {
        super();
        mMounts = new ArrayList<Response>();
    }

    public void addData(Response mounts) {
        mMounts.add(mounts);
        notifyDataSetChanged();
    }

    public void clear() {
        mMounts.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMounts.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Response blizzard = mMounts.get(i);
        String itemId = String.valueOf(blizzard.getMounts().get(i).getItemId());
        String creatureId = String.valueOf(blizzard.getMounts().get(i).getCreatureId());
        String items = viewHolder.repos.getResources().getString(R.string.item_id);
        String creatures = viewHolder.repos.getResources().getString(R.string.creature_id);
        viewHolder.login.setText(blizzard.getMounts().get(i).getName());
        viewHolder.repos.setText(items + itemId);
        viewHolder.blog.setText(creatures + creatureId);
        final Uri imageUri = Uri.parse(blizzard.getMounts().get(i).getIcon());
        Glide.with(viewHolder.pic.getContext()).load(imageUri).into(viewHolder.pic);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.login)
        TextView login;
        @BindView(R.id.repos)
        TextView repos;
        @BindView(R.id.blog)
        TextView blog;
        @BindView(R.id.imageView)
        ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}