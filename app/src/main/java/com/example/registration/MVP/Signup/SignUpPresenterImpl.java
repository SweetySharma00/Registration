package com.example.registration.MVP.Signup;

import android.widget.Toast;

import com.example.registration.Activity.BaseActivity;
import com.example.registration.RetrofitAPI.api.RetrofitFactory;
import com.example.registration.RetrofitAPI.interfaces.IRetrofitContract;
import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.example.registration.Utils.RetroUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SignUpPresenterImpl implements ISignUpPresenter{
    private ISignUpView iSignUpView;

    @Override
    public void hitSignUp()  {
        SignupRequest signupRequest=iSignUpView.getRequest();
//        String PlatformType=((BaseActivity)iSignUpView).getPlatformType();
//        String ClientType=((BaseActivity)iSignUpView).getClientType();
        RetrofitFactory retrofitFactory = RetrofitFactory.getInstance();
        IRetrofitContract iRetrofitContract = retrofitFactory.getRetrofitContract(RetroUtils.APP_ENV);
        Observable<SignUpResponse> signUpResponseObservable = iRetrofitContract.SignUp(signupRequest);
        signUpResponseObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<SignUpResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SignUpResponse signUpResponse) {
//                if (signUpResponse.errorBody()==null){
//                    String digest = signUpResponse.headers().get("Access-Medium");
////                    iSignUpView.setDigest(digest);
//                    iSignUpView.setResponse(signUpResponse.body());
//                }else{iSignUpView.setError(new Throwable());
//                }
                iSignUpView.setResponse(signUpResponse);
            }

            @Override
            public void onError(Throwable e) {
                iSignUpView.setError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    @Override
    public void setView(ISignUpView iSignUpView) {
        this.iSignUpView = iSignUpView;
    }

}

