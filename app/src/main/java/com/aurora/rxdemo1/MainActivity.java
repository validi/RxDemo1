package com.aurora.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    public static String TAG="MyTag";
    private String greeting="Hello From RxJava";
    private Observable<String> myObservable;
    private Observer<String>myObserver;
    TextView txtGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGreeting= (TextView) findViewById(R.id.txtGreeting);

        myObservable=Observable.just(greeting);

        myObserver=new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"onNext");
                txtGreeting.setText(s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"onError");

            }

            @Override
            public void onComplete() {

            }
        };
        myObservable.subscribe(myObserver);
    }
}