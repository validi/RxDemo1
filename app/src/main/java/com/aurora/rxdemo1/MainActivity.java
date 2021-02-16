package com.aurora.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "MyTag";
    private String greeting = "Hello From RxJava";
    private Observable<String> myObservable;
    // private Observer<String>myObserver;
    private DisposableObserver<String> myDisposableObserver;
    TextView txtGreeting;
    // private Disposable disposable;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGreeting = (TextView) findViewById(R.id.txtGreeting);

        myObservable = Observable.just(greeting);

        //  myObservable.subscribeOn(Schedulers.io());
        //  myObservable.observeOn(AndroidSchedulers.mainThread());

//        myObserver=new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.i(TAG,"onSubscribe");
//                disposable=d;
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//                Log.i(TAG,"onNext");
//                txtGreeting.setText(s);
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.i(TAG,"onError");
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        myDisposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "onNext");
                txtGreeting.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");

            }
        };
        // compositeDisposable.add(myDisposableObserver);
        //myObservable.subscribe(myDisposableObserver);

        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(myDisposableObserver));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // myDisposableObserver.dispose();
        compositeDisposable.clear();
    }
}