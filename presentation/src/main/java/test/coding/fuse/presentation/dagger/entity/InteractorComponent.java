package test.coding.fuse.presentation.dagger.entity;


import dagger.Component;
import test.coding.fuse.presentation.dagger.PerActivity;
import test.coding.fuse.presentation.dagger.activity.ActivityComponent;
import test.coding.fuse.presentation.dagger.activity.ActivityModule;
import test.coding.fuse.presentation.dagger.application.ApplicationComponent;
import test.coding.fuse.presentation.view.activity.CompanyActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, InteractorModule.class})
public interface InteractorComponent extends ActivityComponent {
    void inject(CompanyActivity companyActivity);
}