package test.coding.fuse.presentation;

import android.app.Application;

import test.coding.fuse.presentation.dagger.application.ApplicationComponent;
import test.coding.fuse.presentation.dagger.application.ApplicationModule;
import test.coding.fuse.presentation.dagger.application.DaggerApplicationComponent;


public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
