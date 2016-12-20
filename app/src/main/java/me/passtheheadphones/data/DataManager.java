package me.passtheheadphones.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.passtheheadphones.data.local.PreferencesHelper;
import me.passtheheadphones.data.model.Index;
import me.passtheheadphones.data.model.Profile;
import me.passtheheadphones.data.remote.ApiService;
import okhttp3.ResponseBody;
import rx.Single;
import rx.functions.Action1;
import rx.functions.Func1;

@Singleton
public class DataManager {

    private final ApiService mApiService;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(ApiService apiService, PreferencesHelper preferencesHelper) {
        mApiService = apiService;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Single<Profile> getProfile() {
        return mApiService.profile(mPreferencesHelper.getUserId(), mPreferencesHelper.getAuth());
        //TODO flat map a call to recents
    }

    public Single<Index> login(String username, String password, int stayLoggedIn){
        return mApiService.login(username, password, stayLoggedIn).flatMap(new Func1<ResponseBody, Single<? extends Index>>() {
            @Override
            public Single<? extends Index> call(ResponseBody responseBody) {
                return loadIndex();
            }
        });
    }

    public Single<Index> loadIndex(){
        return mApiService.index().doOnSuccess(new Action1<Index>() {
            @Override
            public void call(Index index) {
                mPreferencesHelper.setUserId(index.response.id);
                mPreferencesHelper.setAuth(index.response.authkey);
            }
        });
    }
}
