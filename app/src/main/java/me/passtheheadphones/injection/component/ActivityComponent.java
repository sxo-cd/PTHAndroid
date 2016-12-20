package me.passtheheadphones.injection.component;

import dagger.Subcomponent;
import me.passtheheadphones.injection.PerActivity;
import me.passtheheadphones.injection.module.ActivityModule;
import me.passtheheadphones.ui.login.LoginActivity;
import me.passtheheadphones.ui.profile.ProfileActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(ProfileActivity profileActivity);
    void inject(LoginActivity loginActivity);

}
