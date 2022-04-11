package com.example.registration.MVP.AddressIdentification;

import com.example.registration.Activity.BaseActivity;
import com.example.registration.RetrofitAPI.api.RetrofitFactory;
import com.example.registration.RetrofitAPI.interfaces.IRetrofitContract;
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

public class AddressIdentificationPresenterImpl implements IAddressIdentificationPresenter{

    IAddressIdentificationView iAddressIdentificationView;
    @Override
    public void hitDetails(HashMap<String, RequestBody> map, MultipartBody.Part doc) {

        String digest = iAddressIdentificationView.getDigest();
        String PlatformType = ((BaseActivity) iAddressIdentificationView).getPlatformType();
        String ClientType = ((BaseActivity) iAddressIdentificationView).getClientType();
//        String ContentType = ((BaseActivity) iPersonalDetailView).getContentType();
        RetrofitFactory retrofitFactory = RetrofitFactory.getInstance();
        IRetrofitContract iRetrofitContract = retrofitFactory.getRetrofitContract(RetroUtils.APP_ENV);
        Observable<Response<SignUpResponse>> signUpResponseOb = iRetrofitContract.AddressDetails(digest,PlatformType, ClientType,map,doc);
        signUpResponseOb.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Response<SignUpResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<SignUpResponse> signUpResponse) {
                if (signUpResponse.errorBody() == null) {
                    String digest = signUpResponse.headers().get("Access-Medium");
                    iAddressIdentificationView.setDigest(digest);
                    iAddressIdentificationView.setResponse(signUpResponse.body());
                } else {
                    iAddressIdentificationView.setError(new Throwable());
                }
            }
                @Override
                public void onError(Throwable e) {
                    iAddressIdentificationView.setError(e);
                }

                @Override
                public void onComplete() {

                }
            });


        }

    @Override
    public void setView(IAddressIdentificationView iAddressIdentificationView) {
      this.iAddressIdentificationView=iAddressIdentificationView;
    }
}
