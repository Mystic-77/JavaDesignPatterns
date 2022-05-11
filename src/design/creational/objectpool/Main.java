package design.creational.objectpool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

// example of an object pool design pattern using executor service
public class Main {
    private ObjectPool<ExportingProcess> pool;
    private AtomicLong processNo = new AtomicLong(0);

    public void setUp() {
        pool = new ObjectPool<ExportingProcess>(4, 10, 5) {
            @Override
            ExportingProcess createObject() {
                return new ExportingProcess(processNo.getAndIncrement());
            }
        };
    }

    public void tearDown() {
        pool.shutdown();
    }

    public void testObjectPool() {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        executor.execute(new ExportingTask(pool, 1));
        executor.execute(new ExportingTask(pool, 2));
        executor.execute(new ExportingTask(pool, 3));
        executor.execute(new ExportingTask(pool, 4));
        executor.execute(new ExportingTask(pool, 5));
        executor.execute(new ExportingTask(pool, 6));
        executor.execute(new ExportingTask(pool, 7));
        executor.execute(new ExportingTask(pool, 8));

    }
    public static void main(String[] args) {
        Main main = new Main();
        main.setUp();
        main.testObjectPool();
        main.tearDown();
    }
}

abstract class ObjectPool<T> {
    private ConcurrentLinkedQueue<T> pool;
    private ScheduledExecutorService executorService;

    public ObjectPool(final int minObjects) {
        initialize(minObjects);
    }

    public ObjectPool(final int minObjects, final int maxObjects, final long validationInterval) {
        initialize(minObjects);

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                int size = pool.size();
                if (size < minObjects) {
                    int sizeToAdd = minObjects - size;
                    for (int i = 0; i < sizeToAdd; i++) {
                        pool.add(createObject());
                    }
                }
                else
                {
                    int sizeToRemove = size - maxObjects;
                    for (int i = 0; i < sizeToRemove; i++) {
                        pool.poll();
                    }
                }
            }
        }, validationInterval, validationInterval, java.util.concurrent.TimeUnit.SECONDS);
    }

    // borrow an object from the pool
    public T borrowObject() {
        if (pool.isEmpty()) {
            return createObject();
        }
        return pool.poll();
    }

    // return an object to the pool
    public void returnObject(T object) {
        if (object != null) {
            pool.add(object);
        }
    }

    //shutdown the pool
    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    // create object method to be implemented by subclasses
    abstract T createObject();

    // initialize the pool
    private void initialize(int minObjects) {
        pool = new ConcurrentLinkedQueue<T>();
        for (int i = 0; i < minObjects; i++) {
            pool.add(createObject());
        }
    }
}

class ExportingProcess {
    private long processNo;

    public ExportingProcess(long processNo) {
        this.processNo = processNo;
        System.out.println("Exporting process " + processNo + " created");
    }

    public long getProcessNo() {
        return processNo;
    }
}

class ExportingTask implements Runnable {
    private ObjectPool<ExportingProcess> pool;
    private int threadNo;

    public ExportingTask(ObjectPool<ExportingProcess> pool, int threadNo) {
        this.pool = pool;
        this.threadNo = threadNo;
    }

    public void run() {
        ExportingProcess process = pool.borrowObject();
        System.out.println("Exporting process " + process.getProcessNo() + " started by thread " + threadNo);

        pool.returnObject(process);
        System.out.println("Exporting process " + process.getProcessNo() + " ended by thread " + threadNo);
    }
}


