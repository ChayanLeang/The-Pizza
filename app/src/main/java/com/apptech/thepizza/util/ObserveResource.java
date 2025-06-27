package com.apptech.thepizza.util;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import java.util.function.Consumer;

public class ObserveResource {

    public static <T> void setup(LifecycleOwner lifecycleOwner, LiveData<Resource<T>> liveData,
                                 Runnable onVisibility, Runnable onLoading, Consumer<T> onSuccess,
                                 Runnable onFailure){
        liveData.observe(lifecycleOwner,resource -> {
            onVisibility.run();
            switch (resource.getStatus()){
                case LOADING:
                    onLoading.run();
                    break;

                case SUCCESS:
                    onSuccess.accept(resource.getData());
                    break;

                case FAILURE:
                    onFailure.run();
                    break;
            }
        });
    }
}
