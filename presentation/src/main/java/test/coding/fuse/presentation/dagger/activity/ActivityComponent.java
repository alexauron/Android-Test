package test.coding.fuse.presentation.dagger.activity;

import android.app.Activity;

import dagger.Component;
import test.coding.fuse.presentation.dagger.PerActivity;
import test.coding.fuse.presentation.dagger.application.ApplicationComponent;

/**
 * Activity scope components
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  //Exposed to sub-graphs.
  Activity activity();
}