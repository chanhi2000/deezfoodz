# DeezFoodz
Ray Wendelich Dagger Tutorial


## Dagger dependency injection with FoodzActivity:
1. Add `inject()` in `AppComponent` with `FoodzActivity` argument.
```java
@Singleton @Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(FoodzActivity target);
}
```
2. Add `provideFoodzPresenter()` in `PresenterModule`.
```java
@Module
public class PresenterModule {
    @Provides @Singleton
    FoodzPresenter provideFoodzPresenter() {  return new FoodzPresenterImpl();  }
}
```
3. Add `@Inject` annotation to `FoodzPresenter presenter` in `FoodzActivity`.
```java
public class FoodzActivity extends LifecycleLoggingActivity implements FoodzView {
    // FoodzPresenter presenter;
    @Inject FoodzPresenter presenter
    ...
   @Override protected void onCreate(Bundle savedInstanceState) {
      ...
      // presenter = new FoodzPresenterImpl();
```
4. Add `DeezFoodzApplication.getAppComponent().inject(this)` in `onCreate()` in `FoodzActivity`
```java
public class FoodzActivity extends LifecycleLoggingActivity implements FoodzView {
    @Override protected void onCreate(Bundle savedInstanceState) {
        ...
        setContentView(R.layout.activity_foodz);

        ((DeezFoodzApplication)getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);
        ....
```    

