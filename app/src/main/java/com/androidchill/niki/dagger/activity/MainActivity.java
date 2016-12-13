package com.androidchill.niki.dagger.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.androidchill.niki.dagger.R;
import com.androidchill.niki.dagger.adapters.CardPetsAdapter;
import com.androidchill.niki.dagger.dagger.application.ServiceApplication;
import com.androidchill.niki.dagger.model.ResponsePets;
import com.androidchill.niki.dagger.service.ServiceFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.this.getClass().getSimpleName();
    private Subscription subscription;

    @Inject
    ServiceFactory serviceFactory;
    @BindView(R.id.button_clear)
    Button bClear;
    @BindView(R.id.button_fetch)
    Button bFetch;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dagger2 Dependencies
        injectDependencies(this);

        //Butterknife bind
        ButterKnife.bind(this);

        //Set CardView/RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set Adapter CardPetsAdapter
        final CardPetsAdapter mCardAdapter = new CardPetsAdapter();
        mRecyclerView.setAdapter(mCardAdapter);

        //Set Button CLear Lambda and Method references
        bClear.setOnClickListener(v -> mCardAdapter.clear());

        //Set Button Fetch Lambda and Method references
        bFetch.setOnClickListener(v -> getSubscribe());
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    private void injectDependencies(Context context) {
        ((ServiceApplication) getApplication()).getApiComponent().inject((MainActivity) context);
    }
    
    private Subscription getSubscribe() {
        return subscription = serviceFactory.getPets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCardAdapter::addData);
    }
}

