package me.passtheheadphones.ui.profile;

import javax.inject.Inject;

import me.passtheheadphones.base.BasePresenter;
import me.passtheheadphones.data.DataManager;
import me.passtheheadphones.data.model.Profile;
import me.passtheheadphones.injection.ConfigPersistent;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@ConfigPersistent
public class ProfilePresenter extends BasePresenter<ProfileMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public ProfilePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ProfileMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadProfile() {
        checkViewAttached();
        mSubscription = mDataManager.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<Profile>() {
                               @Override
                               public void onSuccess(Profile profile) {
                                   getMvpView().showProfile(profile);
                               }

                               @Override
                               public void onError(Throwable error) {
                                   getMvpView().showError();
                                   error.printStackTrace();
                               }
                           }
                );
    }
}
