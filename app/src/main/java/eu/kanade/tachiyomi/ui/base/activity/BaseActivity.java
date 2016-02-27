package eu.kanade.tachiyomi.ui.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import eu.kanade.tachiyomi.App;
import eu.kanade.tachiyomi.injection.component.AppComponent;
import icepick.Icepick;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    protected void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    public void setToolbarTitle(int titleResource) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(titleResource));
    }

    public void setToolbarSubtitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setSubtitle(title);
    }

    public void setToolbarSubtitle(int titleResource) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setSubtitle(getString(titleResource));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerForEvents() {
        EventBus.getDefault().register(this);
    }

    public void unregisterForEvents() {
        EventBus.getDefault().unregister(this);
    }

    protected AppComponent getApplicationComponent() {
        return App.get(this).getComponent();
    }

}