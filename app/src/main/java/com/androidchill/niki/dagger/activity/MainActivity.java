package com.androidchill.niki.dagger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        injectDependencies();

        //Butterknife bind
        ButterKnife.bind(this);

        //Set CardView/RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set Adapter CardPetsAdapter
        final CardPetsAdapter mCardAdapter = new CardPetsAdapter();
        mRecyclerView.setAdapter(mCardAdapter);

        //Set Button Clear
        bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCardAdapter.clear();
            }
        });

        //Set Button Fetch
        bFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceFactory.getPets()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResponsePets>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponsePets responsePets) {
                                mCardAdapter.addData(responsePets);
                            }
                        });
            }
        });
    }

    private void injectDependencies() {
        ((ServiceApplication) getApplication()).getApiComponent().inject(this);
    }
}

