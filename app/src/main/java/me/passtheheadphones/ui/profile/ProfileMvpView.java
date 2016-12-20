package me.passtheheadphones.ui.profile;

import me.passtheheadphones.base.MvpView;
import me.passtheheadphones.data.model.Profile;

public interface ProfileMvpView extends MvpView {

    void showProfile(Profile profile);

    void showRecents();

    void showError();
}
