package matope.simarro.pmdm_t3p9_torres_marcos.otros;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

public class Locales{

    public static void cambiarIdioma(Activity activity, String languagecoder) {
        Locale locale = new Locale(languagecoder);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        activity.getResources().updateConfiguration(config, null);
    }

}