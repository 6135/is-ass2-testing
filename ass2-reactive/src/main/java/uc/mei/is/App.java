package uc.mei.is;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
/**
 * Hello world!
 *
 */
public class App 
{
    static Integer[] i = {10,1,2,3,4,5,6,7,8,9,11};
    static Integer[] i2 = {10,1,2,3,4,5,6,7,8,9};
    static Integer[] i3 = {10,1,2,3,4,5,6,7,8,9};
    static Integer[] avg = {10,9,7,8,8,9,8,7,10,10,66,12,4,6,7,12,1,83,9,7,8,8,9,8,7,9,7,8,8,9,8,7};
    public static void main( String[] args ) throws Exception
    {
        // part1();
        // part2();
        part3();
    }

    public static void part2() throws InterruptedException{
        //ex4
        // Flux<Integer> flux = Flux.range(1,10);
        // flux.map(n->{System.out.println("map: " + n); return n;})
        //     .filter(n->{System.out.println("filter: "+(n>5)); return n>5;})
        //     .subscribe(a->System.out.print(a+"\n"));
        // //ex5
        // flux = Flux.range(1,10);
        // flux.map(n->n)
        //     .filter(n->n>5)
        //     .subscribe(a->System.out.println("Number: "+a));

        // //ex6

        // Flux<Integer> sums = Flux.fromArray(avg);
        // sums.window(10, 1)
        // .flatMap(window -> window.reduce(Integer::sum))
        // .subscribe(n->System.out.println(n/7.0));

        // //ex8
        
        // Flux<Integer> pair = Flux.fromArray(avg);
        // pair.buffer(2,1)
        // .filter(b->b.size()>1 && b.get(0)>b.get(1))
        // .subscribe(System.out::println);

        // //ex7
        // Mono<List<Integer>> ls = Flux.range(1, 10).collectList();
        // ls.subscribe(System.out::println);

        //ex 9a
        // SimpleSubscriber<Integer> ss = new SimpleSubscriber<>();

        // Flux<Integer> ints = Flux.range(1,100);
        // ints.subscribe(new BaseSubscriber<Integer>() {
        //     @Override
        //     public void hookOnSubscribe(Subscription subscription){
        //         request(1);
        //     }

        //     @Override
        //     public void hookOnNext(Integer integer){
        //         System.out.println(integer);
        //         request(1); //Comment to do ex a
                
        //     }

        //     @Override
        //     public void hookOnComplete(){
        //         System.out.println("Done");
        //     }

        // });

        //ex10

    }

    public static void part3(){

        WebClient webClient = WebClient.create("https://www.dei.uc.pt/lei/");

        Mono<String> rsp = webClient.get()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
        rsp.subscribe(System.out::println,System.out::println);

        rsp.block(); //It's like adding sleep but we force to wait for mono to retrieve data

    }

    public static void part1(){
        //ex1
        Arrays.sort(i,(a,b) -> a-b );
        System.out.println(Arrays.toString(i));
        //ex2
        Sort<Integer> sort = (a,b) -> a - b;

        Arrays.sort(i2,sort::compare);

        System.out.println(Arrays.toString(i2));
        //ex3
        System.out.println(compareInt((a,b)->a-b, 1, 2));
    }

    public static int compareInt(Sort<Integer> s, int a, int b) {
        return s.compare(a,b);
    }
}
@FunctionalInterface
interface Sort<T>{
    int compare(T a, T b);
}