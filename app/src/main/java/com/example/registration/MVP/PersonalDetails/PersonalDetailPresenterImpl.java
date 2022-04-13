package com.example.registration.MVP.PersonalDetails;

import com.example.registration.Activity.BaseActivity;
import com.example.registration.MVP.Signup.ISignUpView;
import com.example.registration.RetrofitAPI.api.RetrofitFactory;
import com.example.registration.RetrofitAPI.interfaces.IRetrofitContract;
import com.example.registration.RetrofitAPI.models.response.PersonalDetailResponse;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.example.registration.Utils.RetroUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class PersonalDetailPresenterImpl implements IPersonalDetailPresenter {
    IPersonalDetailView iPersonalDetailView;

    @Override
    public void hitDetails(HashMap<String,RequestBody> map, MultipartBody.Part doc) {
        String digest = iPersonalDetailView.getDigest();
//        OtpRequest otp = iPersonalDetailView.
//        PersonalDetailRequest personalDetailRequest = iPersonalDetailView.getRequest();
        String PlatformType = ((BaseActivity) iPersonalDetailView).getPlatformType();
        String ClientType = ((BaseActivity) iPersonalDetailView).getClientType();
//        String ContentType = ((BaseActivity) iPersonalDetailView).getContentType();
        RetrofitFactory retrofitFactory = RetrofitFactory.getInstance();
        IRetrofitContract iRetrofitContract = retrofitFactory.getRetrofitContract(RetroUtils.APP_ENV);
        Observable<Response<PersonalDetailResponse>> signUpResponseOb = iRetrofitContract.Details(digest,PlatformType, ClientType,map,doc);
        signUpResponseOb.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Response<PersonalDetailResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<PersonalDetailResponse> personalDetailResponseResponse) {
//                iPersonalDetailView.setResponse(signUpResponse.body());
                if (personalDetailResponseResponse.errorBody()==null){
                    String digest = personalDetailResponseResponse.headers().get("Access-Medium");
                    iPersonalDetailView.setDigest(digest);
                    iPersonalDetailView.setResponse(personalDetailResponseResponse.body());
                }else{iPersonalDetailView.setError(new Throwable());
                }
            }

            @Override
            public void onError(Throwable e) {
                iPersonalDetailView.setError(e);
            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    public void setView(IPersonalDetailView iPersonalDetailView) {
        this.iPersonalDetailView=iPersonalDetailView;
    }

}
