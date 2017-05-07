# DeezFoodz
Ray Wendelich Dagger Tutorial


## Dagger dependency injection: Food(z)Presenter:
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

## Dagger dependency injection: NetworkModule
0. Dependency diagram for `UsdaApi` class
![img01][img01]

1. Add `NetworkModule` and provide all the dependencies needed
```java
@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides @Named(NAME_BASE_URL)
    String provideBaseUrlString() {  return Constants.BASE_URL;  }

    @Provides @Singleton
    Converter.Factory provideGsonConverter() {  return GsonConverterFactory.create();  }

    @Provides @Singleton
    Retrofit provideRetroFit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build();
    }

    @Provides @Singleton
    UsdaApi provideUsdaApi(Retrofit retrofit) {  return retrofit.create(UsdaApi.class);  }
}
```
2. Add `NetworkModule` to `AppComponent` with `Food(z)PresenterImpl` arguments: 
```java
@Singleton @Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class})
public interface AppComponent {
    ...
    void inject(FoodzPresenterImpl target);
    void inject(FoodPresenterImpl target);
}
```
3. Modify `PresenterModule` so that it passes context as parameter:
```java
@Module
public class PresenterModule {
    @Provides @Singleton
    FoodzPresenter provideFoodzPresenter(Context context) {  return new FoodzPresenterImpl(context);  }

    FoodPresenter provideFoodPresenter(Context context) {  return new FoodPresenterImpl(context);  }
```
4. Add `@Inject` annotation to `UsdaApi` in `FoodPresenterImpl`. 
```java
public class FoodzPresenterImpl implements FoodzPresenter {
    ...
    @Inject UsdaApi usdaApi;
    public FoodzPresenterImpl(Context context) {  ((DeezFoodzApplication)context).getAppComponent().inject(this);  }
    ...
        @Override public void getFoodz() {
        view.showLoading();

//        Converter.Factory converter = GsonConverterFactory.create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(converter)
//                .build();
//
//        UsdaApi usdaApi = retrofit.create(UsdaApi.class);
    usdaApi.getFoodzList().enqueue(new Callback<FoodzListResponse>() {
    ...
```


[img01]: https://koenig-media.raywenderlich.com/uploads/2017/01/dagger-diagram-1-455x500.png
