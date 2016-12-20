package me.passtheheadphones.ui.login;

import javax.inject.Inject;

import me.passtheheadphones.base.BasePresenter;
import me.passtheheadphones.data.DataManager;
import me.passtheheadphones.data.model.Index;
import me.passtheheadphones.injection.ConfigPersistent;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void login(String username, String password) {
        checkViewAttached();

        mSubscription = mDataManager.login(username, password, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<Index>() {
                    @Override
                    public void onSuccess(Index value) {
                        getMvpView().showLoginSuccess();
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });
    }
}
