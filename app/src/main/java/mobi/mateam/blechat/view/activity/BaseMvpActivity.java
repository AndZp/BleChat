package mobi.mateam.blechat.view.activity;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import mobi.mateam.blechat.App;
import mobi.mateam.blechat.di.component.AppComponent;
import mobi.mateam.blechat.view.interfaces.MvpView;

class BaseMvpActivity extends RxAppCompatActivity implements MvpView {
  @Override public Context getAppContext() {
    return getApplicationContext();
  }

  @Override public Activity getActivityContext() {
    return this;
  }

  @Override public void showError(Throwable error) {
    //TODO: Improve
    Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
  }

  @Override public void showMessage(String text) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
  }

  @Override public AppComponent getAppComponent() {
    return ((App) getApplication()).getAppComponent();
  }
}
