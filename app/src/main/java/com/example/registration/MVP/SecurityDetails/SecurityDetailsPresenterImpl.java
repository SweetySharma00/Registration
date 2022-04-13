package com.example.registration.MVP.SecurityDetails;

import com.example.registration.Activity.BaseActivity;
import com.example.registration.RetrofitAPI.api.RetrofitFactory;
import com.example.registration.RetrofitAPI.interfaces.IRetrofitContract;
import com.example.registration.RetrofitAPI.models.request.Miscellaneous;
import com.example.registration.RetrofitAPI.models.request.SecDetails;
import com.example.registration.RetrofitAPI.models.request.SecurityDetailsRequest;
import com.example.registration.RetrofitAPI.models.response.SecurityDetailsResponse;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.example.registration.Utils.RetroUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SecurityDetailsPresenterImpl implements ISecurityDetailsPresenter{
    ISecurityDetailsView iSecurityDetailsView;
    @Override
    public void hitDetails(SecurityDetailsRequest securityDetailsRequest) {
        String digest = iSecurityDetailsView.getDigest();
        String PlatformType=((BaseActivity)iSecurityDetailsView).getPlatformType();
        String ClientType=((BaseActivity)iSecurityDetailsView).getClientType();
        RetrofitFactory retrofitFactory = RetrofitFactory.getInstance();
        IRetrofitContract iRetrofitContract = retrofitFactory.getRetrofitContract(RetroUtils.APP_ENV);
        Observable<Response<SecurityDetailsResponse>> signUpResponseOb = iRetrofitContract.SecurityDetails(digest,PlatformType,ClientType,securityDetailsRequest);
        signUpResponseOb.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Response<SecurityDetailsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<SecurityDetailsResponse> Response) {
                if (Response.errorBody()==null){
                    String digest = Response.headers().get("Access-Medium");
                    iSecurityDetailsView.setDigest(digest);
                    iSecurityDetailsView.setResponse(Response.body());
                }else{iSecurityDetailsView.setError(new Throwable());
                }
            }

            @Override
            public void onError(Throwable e) {

                iSecurityDetailsView.setError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setView(ISecurityDetailsView iSecurityDetailsView) {
        this.iSecurityDetailsView=iSecurityDetailsView;

    }
}
