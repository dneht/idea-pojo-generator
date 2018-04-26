package io.cana.intellij.pojogenerator.injections;

/**
 * Created by vadim on 28.09.16.
 */
public class Injector {
    private static AppComponent appComponent;

    public static void initModules() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
