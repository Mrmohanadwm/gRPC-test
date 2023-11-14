def run():
    # Устанавливаем соединение с сервером
    channel = grpc.insecure_channel('localhost:8080')

    # Создаем stub для вызова методов сервиса
    stub = GreetingServiceStub(channel)

    # Создаем объект запроса
    request = HelloRequest(name='Igor')

    # Вызываем удаленный метод и получаем поток ответов
    response_stream = stub.greeting(request)

    # Читаем и печатаем ответы из потока
    for response in response_stream:
        print(response.greeting)

    # Закрываем соединение
    channel.close()


if name == 'main':
    run()