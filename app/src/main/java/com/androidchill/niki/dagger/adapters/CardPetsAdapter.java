package com.androidchill.niki.dagger.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidchill.niki.dagger.R;
import com.androidchill.niki.dagger.model.ResponsePets;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardPetsAdapter extends RecyclerView.Adapter<CardPetsAdapter.ViewHolder> {
    List<ResponsePets> mPets;

    public CardPetsAdapter() {
        super();
        mPets = new ArrayList<ResponsePets>();
    }

    public void addData(ResponsePets pets) {
        mPets.add(pets);
        notifyDataSetChanged();
    }

    public void clear() {
        mPets.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mPets.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final ResponsePets blizzard = mPets.get(i);
        String itemId = String.valueOf(blizzard.getPets().get(i).getTypeId());
        String creatureId = String.valueOf(blizzard.getPets().get(i).getCreatureId());
        String items = viewHolder.repos.getResources().getString(R.string.item_id);
        String creatures = viewHolder.repos.getResources().getString(R.string.creature_id);
        viewHolder.login.setText(blizzard.getPets().get(i).getName());
        viewHolder.repos.setText(items + itemId);
        viewHolder.blog.setText(creatures + creatureId);
        loadPetImageProfile(viewHolder.pic, blizzard.getIcon());
        loadPetImageProfile(viewHolder.pics, blizzard.getPetPic());
    }
    
    private void loadPetImageProfile(ImageView imageView, String uri) {
        final Uri imageUri = Uri.parse(uri);

        Picasso.with(imageView.getContext()).setIndicatorsEnabled(true);
        Picasso.with(imageView.getContext()).load(imageUri).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(imageView.getContext()).load(imageUri).into(imageView);
            }
        });
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
        @BindView(R.id.imageView4)
        ImageView pics;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
