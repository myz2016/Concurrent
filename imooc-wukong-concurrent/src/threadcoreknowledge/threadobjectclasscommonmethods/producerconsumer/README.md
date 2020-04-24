##简介
实现生产者消费者设计模式。Container 是容器接口类，代表了容器具有的功能，Box 与 Basket 实现了 Container 接口，说明它们都具有容器的功能。
Producer 与 Consumer 都持有容器引用，它们必须使用同一个容器对象才有意义。