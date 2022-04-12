

### 集合类不安全

#### list
```java
List<Integer> list = new ArrayList<>();

for (int i = 0; i < 30; i++) {
    new Thread(() -> {
        for (int j = 0; j < 10; j++) {
            list.add(new Random(System.currentTimeMillis()).nextInt()); // java.util.ConcurrentModificationException
            System.out.println(list);
        }
    }).start();
}
```

1. 多线程操作list，线程安全，`ConcurrentModificationException`
2. 使用 Vector
3. 使用 Collections.synchronizedList();
4. 使用 CopyOnWriteList<>();

使用**写时复制，读写分离的思想**，同一个时刻只能有一个人写，可供多个人同时读
```java
private E get(Object[] a, int index) {
    return (E) a[index];
}

public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);  // 写回
        return true;
    } finally {
        lock.unlock();
    }
}
```

#### set

```java
public static void test(Set<String> set) {

    for (int i = 0; i < 30; i++) {
        new Thread(() -> {
            set.add(UUID.randomUUID().toString().substring(0, 8));  // ConcurrentModificationException ??
            System.out.println(set);
        }).start();
    }

    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

}
```

1. Collections.synchronizedSet(new HashSet<>())
2. new CopyOnWriteArraySet<>()

#### map

```java
public static void test(Map<String, String> map) {

    for (int i = 0; i < 30; i++) {
        new Thread(() -> {
            map.put(UUID.randomUUID().toString().substring(0, 8), "PRESENT");  // ConcurrentModificationException ??
            System.out.println(map);
        }).start();
    }

    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

}
```

1. Collections.synchronizedMap(new HashMap<String, String>());
2. new ConcurrentHashMap<>(); **原理**
