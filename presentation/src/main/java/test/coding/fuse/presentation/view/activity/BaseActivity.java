package test.coding.fuse.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.coding.fuse.presentation.AndroidApplication;
import test.coding.fuse.presentation.R;
import test.coding.fuse.presentation.dagger.activity.ActivityModule;
import test.coding.fuse.presentation.dagger.application.ApplicationComponent;


public abstract class BaseActivity  extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        onCreateExtended(savedInstanceState);
    }

    protected abstract int getContentView();

    protected abstract void onCreateExtended(Bundle savedInstanceState);

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
