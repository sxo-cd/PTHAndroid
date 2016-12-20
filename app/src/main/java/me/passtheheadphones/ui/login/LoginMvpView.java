package me.passtheheadphones.ui.login;

import me.passtheheadphones.base.MvpView;

public interface LoginMvpView extends MvpView {

    void showLoginSuccess();

    void showError();
}
