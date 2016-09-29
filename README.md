### Dagger 2.0 Sample App

#####Introduction
RxJavata showcases the use of Square's [Retrofit](http://square.github.io/retrofit/) and [RxJava](https://github.com/ReactiveX/RxJava/wiki) to make asynchronous HTTP Requests in Android Application. The App makes HTTP GET requests to the [Blizzard API](https://dev.battle.net/) to retrieve **Mounts** and **mounts URL** and
added Dagger2 for Dependency Injection Application.

You can see how all of these libraries will save you time and money and a lot boilerplate.

The FETCH button kicks off a series of HTTP requests to Blizzard API. The HTTP requests are built via Retrofit 2.0. The calls are made asynchronously through RxJava. Notice that the cards are laid out in different order each time the button is pressed. You are seeing async threading at work! Each card is rendered when the result comes back from a GET request.

The Project contains all the latest libraries which have to be included in most projects depending on the architecture and the design which
it will use libraries RxJava, RxAndroid to the latest version, ASL to the latest version, Retrofit 2.0, Butterknife, Glide latest version to load the images

In order to use this project and understand it correctly i suggest going over the web and makeing a couple of days of reading here is a little something to get you started.

![Alt text](http://i.imgur.com/Z8BJLlp.jpg "Dagger 2")

#####The Setup
Let's take care of the depency injection for retrofit and RxJava/RxAndroid:
```java
dependencies {
 compile 'com.android.support:appcompat-v7:24.1.1'
    compile 'com.android.support:cardview-v7:24.1.1'
    compile 'com.android.support:recyclerview-v7:24.1.1'

    compile 'io.reactivex:rxandroid:1.2.1'
    compile 'io.reactivex:rxjava:1.1.6'

    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.0'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'

    compile 'com.jakewharton:butterknife:8.4.0'

    compile 'com.github.bumptech.glide:glide:3.7.0'

    apt 'com.google.dagger:dagger-compiler:2.5'
    compile 'com.google.dagger:dagger:2.5'

    provided 'org.glassfish:javax.annotation:10.0-b28'
    apt 'com.jakewharton:butterknife-compiler:8.4.0'
}
```

Don't forget Android App Permissions in AndroidManifest:
```java
<uses-permission android:name="android.permission.INTERNET" />
```

#####ServiceFactory Class
Retrofit uses a Java interface as proxy for the REST API calls. All we have to do is to define the @GET method and the url/path. The following code makes a GET request to the Github URL and returns an Observable. The Observable object is used by RxJava to do stream processing.
```java
public interface ServiceFactory {
    String BASE_URL = "https://eu.api.battle.net/wow/";

    @GET("mount/?locale=en_GB&apikey=amd7ujwtgawpy97kswhhk38xsxtbdn2m")
    Observable<Response> getMounts();

    //https://us.api.battle.net/wow/pet/?locale=en_US&apikey=amd7ujwtgawpy97kswhhk38xsxtbdn2m
    @GET("pet/?locale=en_GB&apikey=amd7ujwtgawpy97kswhhk38xsxtbdn2m")
    Observable<ResponsePets> getPets();
}
```

Here it is Dagger coming to the resque you can see how i added a custom scope and used the library to create the ApiModule here:
```java
@Module
public class ApiModule {

    @Provides
    @CustomScope
    ServiceFactory provideMountApiService(Retrofit retrofit){
        return retrofit.create(ServiceFactory.class);
    }
}
```

Here it is the Component you can check the figure on top of the project to see the relation between them.
```java
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity mainActivity);
}

```
Data Model: The field variables in the model are automatically parsed from the JSON response. So you don't need to worry about writing the parsing code. Make sure that the variable names are exactly the same as API definition: 
```java
public class Response {

    /**
     * name : Grey Riding Yak
     * spellId : 127216
     * creatureId : 65017
     * itemId : 87788
     * qualityId : 4
     * icon : ability_mount_yakmountgrey
     * isGround : true
     * isFlying : false
     * isAquatic : true
     * isJumping : true
     */

    private List<MountsEntity> mounts;

    public void setMounts(List<MountsEntity> mounts) {
        this.mounts = mounts;
    }

    public List<MountsEntity> getMounts() {
        return mounts;
    }

    public static class MountsEntity {
        private String name;
        private int spellId;
        private int creatureId;
        private int itemId;
        private int qualityId;
        private String icon;
        private boolean isGround;
        private boolean isFlying;
        private boolean isAquatic;
        private boolean isJumping;

        public void setName(String name) {
            this.name = name;
        }

        public void setSpellId(int spellId) {
            this.spellId = spellId;
        }

        public void setCreatureId(int creatureId) {
            this.creatureId = creatureId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public void setQualityId(int qualityId) {
            this.qualityId = qualityId;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setIsGround(boolean isGround) {
            this.isGround = isGround;
        }

        public void setIsFlying(boolean isFlying) {
            this.isFlying = isFlying;
        }

        public void setIsAquatic(boolean isAquatic) {
            this.isAquatic = isAquatic;
        }

        public void setIsJumping(boolean isJumping) {
            this.isJumping = isJumping;
        }

        public String getName() {
            return name;
        }

        public int getSpellId() {
            return spellId;
        }

        public int getCreatureId() {
            return creatureId;
        }

        public int getItemId() {
            return itemId;
        }

        public int getQualityId() {
            return qualityId;
        }

        public String getIcon() {
            return "http://eu.media.blizzard.com/wow/icons/56/" + icon + ".jpg";
        }

        public String getMountPic() {
            return "http://media.blizzard.com/wow/renders/npcs/zoom/creature" + getCreatureId() + ".jpg";
        }

        public boolean isIsGround() {
            return isGround;
        }

        public boolean isIsFlying() {
            return isFlying;
        }

        public boolean isIsAquatic() {
            return isAquatic;
        }

        public boolean isIsJumping() {
            return isJumping;
        }
    }
}
```
And you are done! Other than Java's boilerplate stuff (boo), the code is very concise and readable. If you have more than one endpoint you want to access, simply add it to your service interface at little additional cost!

#####RxJava Async Stream
The [Observable](http://reactivex.io/documentation/observable.html) object from our Response streams data when it becomes available. We need to have an Subscriber (sometimes called Observer) to watch for the data stream changes. Conceptually, the Subscriber subscribes to an Observable. The following block of code performs the entire process described.

```java
serviceFactory.getPets()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<ResponsePets>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {

                  }

                  @Override
                  public void onNext(ResponsePets responsePets) {
                      mCardAdapter.addData(responsePets);
                  }
});
```
That was probably a bit confusing. Let's break the code down line by line here you can use the FactoryService which we created earlier
with the APIModule, NetworkModule and see the true power of Dagger.
```java
serviceFactory.getPets()
//or
serviceFactory.getMounts();
//or
serviceFactory.getWheels();
```
GithubService Interface has the getUser method which returns an Observable. We are chaining method calls on this Observable to get the REST call response.
```java
.subscribeOn(Schedulers.newThread())
.observeOn(AndroidSchedulers.mainThread())
```
These two lines specify that the REST call will be made in a new thread (YES!). And when the call response returns, it call the onNext, onError, and onComplete methods on the mainThread. The reason we need to call them on the mainThread is that only the mainThread can update the UI. If you have data that do not need to be displayed immediately, you would not need to observe on main thread. The difference between observeOn and subscribeOn is best explained in this [stackoverflow](http://stackoverflow.com/questions/20451939/observeon-and-subscribeon-where-the-work-is-being-done).
This Subscriber responds to the Observable's stream. onNext is called when the REST call receives data. In this Github example, there is only 1 item, so it is only called once. When the REST response is a list, the code can be called each time an item is received. onComplete and onError behave exactly as the name implies.


#####We are done
We have just made our non-blocking HTTP calls on Android. Special thanks to the folks at Square and ReactiveX for making our lives easier!

Don't forget to thanks the dagger team!

#### Author

- Niki Izvorski (nikiizvorski@gmail.com)

<br>
#####Reference:
Square Retrofit Doc: [http://goo.gl/UwksBu] <br>
RxJava Doc: [https://goo.gl/5AqMNi] <br>
CardView/RecycleView UI Reference: [http://goo.gl/stNj2J]
