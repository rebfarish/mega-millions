package deepdive.cnm.edu.megamillions;

import android.app.Application;
import com.facebook.stetho.Stetho;
import deepdive.cnm.edu.megamillions.model.db.PickDatabase;

public class PickApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);

  }

}
