package org.example;

import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends com.example.grpc.GreetingServiceGrpc.GreetingServiceImplBase{
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver){
// в StreamObserver можем поместить любое кол-во респонсов.
// на нем вызываем методы. каждый вызов передает респонс на клиента

        for (int i = 0; i < 10 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // ответ клиенту
// паттерн "билдер" для составления респонса
            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.
                    HelloResponse.newBuilder().setGreeting("hello from server, " + request.getName()).
                    build();

            // отсылаем клиенту
            responseObserver.onNext(response);
        }

// означает что больше не пересылаем данные
        responseObserver.onCompleted();
    }
}
