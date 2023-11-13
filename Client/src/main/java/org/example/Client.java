package org.example;
import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.lang.management.ManagementFactory;
import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        // канал передачи данных
        ManagedChannel channel =  ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();

        // создаем stub - специальный объект для удаленных запросов
        // передаем в него канал который настроен на работу с сервером
        com.example.grpc.GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        com.example.grpc.GreetingServiceOuterClass.HelloRequest request = com.example.grpc.GreetingServiceOuterClass.HelloRequest.newBuilder().setName("Igor").build();

        //метод greeting будет вызван на сервере и придет по сети HelloResponse
      Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);

      while (response.hasNext())
        System.out.println(response.next());

        channel.shutdownNow();
    }
}
