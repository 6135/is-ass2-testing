package uc.mei.is;

import reactor.core.publisher.BaseSubscriber;
import org.reactivestreams.Subscription;

public class SimpleSubscriber<T> extends BaseSubscriber<T>{

    @Override
    public void hookOnSubscribe(Subscription subscription){
        System.out.println("Subbed! Requesting first Item");
        request(1);
    }

    @Override
    public void hookOnNext(T value){
        System.out.println("Value: "+value);
        request(1);
    }
}
