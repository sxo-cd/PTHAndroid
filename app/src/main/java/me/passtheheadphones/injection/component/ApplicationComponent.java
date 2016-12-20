package me.passtheheadphones.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.passtheheadphones.data.DataManager;
import me.passtheheadphones.data.local.PreferencesHelper;
import me.passtheheadphones.data.remote.ApiService;
import me.passtheheadphones.injection.ApplicationContext;
import me.passtheheadphones.injection.module.ApplicationModule;
import me.passtheheadphones.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    ApiService apiService();
    PreferencesHelper preferencesHelper();
    DataManager dataManager();
    RxEventBus eventBus();
}
