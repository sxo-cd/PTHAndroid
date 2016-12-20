package me.passtheheadphones;

import android.app.Application;
import android.content.Context;


import com.facebook.stetho.Stetho;

import me.passtheheadphones.injection.component.ApplicationComponent;
import me.passtheheadphones.injection.component.DaggerApplicationComponent;
import me.passtheheadphones.injection.module.ApplicationModule;

/**
 * Created by fatih on 11.9.2015.
 * This base application keeps the MySoup session with the site active and takes care of loading
 * images using Glide
 */
public class PTHApplication extends Application {

	//TODO: Developers put your local Gazelle install IP here instead of testing on the live site
	//I recommend setting up with Vagrant: https://github.com/dr4g0nnn/VagrantGazelle
	public static final String DEFAULT_SITE = "https://passtheheadphones.me";
	ApplicationComponent mApplicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		Stetho.initializeWithDefaults(this);
//		initSoup(DEFAULT_SITE);
//		if (SettingsActivity.lightThemeEnabled(getApplicationContext())) {
//			AppCompatDelegate.setDefaultNightMode(
//					AppCompatDelegate.MODE_NIGHT_NO);
//		} else {
//			AppCompatDelegate.setDefaultNightMode(
//					AppCompatDelegate.MODE_NIGHT_YES);
//		}
	}

	public static PTHApplication get(Context context) {
		return (PTHApplication) context.getApplicationContext();
	}

	public ApplicationComponent getComponent() {
		if (mApplicationComponent == null) {
			mApplicationComponent = DaggerApplicationComponent.builder()
					.applicationModule(new ApplicationModule(this))
					.build();
		}
		return mApplicationComponent;
	}

	// Needed to replace the component with a test specific one
	public void setComponent(ApplicationComponent applicationComponent) {
		mApplicationComponent = applicationComponent;
	}

}
