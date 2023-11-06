package org.example.arraycounting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();

        int[] arr = new int[1_000_000_000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.println("------------------------");
        long totalSumOneTread = 0;
        long startOneThreadCountingTime = System.currentTimeMillis();
        for (int element : arr) {
            totalSumOneTread += element;
        }

        System.out.println("Time 1 thread = " + (System.currentTimeMillis() - startOneThreadCountingTime));
        System.out.println(totalSumOneTread + " sum total");
        System.out.println("------------------------");

        //leave 1 thread free
        int availableThreads = Runtime.getRuntime().availableProcessors() - 1;

        if (isAvailableThreads(availableThreads)) {
            System.out.println("quantity of available threads - " + availableThreads);

            long startMultithreadingCountingTime = System.currentTimeMillis();
            List<Long> countingElements = main.threadsStart(availableThreads, arr);
            System.out.println("Time multi threads = " + (System.currentTimeMillis() - startMultithreadingCountingTime));

            long totalSumMultiTread = main.countElements(countingElements);
            System.out.println(totalSumMultiTread + " sum all elements from array");
        } else {
            System.out.println("no free threads, further operation of the program is impossible");
        }
    }

    public static boolean isAvailableThreads(int threadsQuantity) {
        return threadsQuantity > 0;
    }

    public List<Long> threadsStart(int threadsNumber, int[] arrayForCalculation) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        //розбиваємо масив на кількість частин, яка дорівнює кількості доступних потоків
        int arrayPartsForCounting = (arrayForCalculation.length / threadsNumber) + 1;
        CopyOnWriteArrayList<Long> elements = new CopyOnWriteArrayList<>();
        for (int i = 0; i < threadsNumber; i++) {
            elements.add(0L);
            int currentThread = i;

            Thread thread = new Thread(() -> {
                int startPositionForCurrentThread = countStartPosition(currentThread, arrayPartsForCounting);
                int endPositionForCurrentThread = countEndPosition(startPositionForCurrentThread, arrayPartsForCounting);

                if (endPositionForCurrentThread > arrayForCalculation.length) {
                    endPositionForCurrentThread = arrayForCalculation.length;
                }

                long sumOfElements = 0;

                for (int j = startPositionForCurrentThread; j < endPositionForCurrentThread; j++) {
                    sumOfElements += arrayForCalculation[j];
                }
                elements.set(currentThread, sumOfElements);

                System.out.println("Thread finished - " + Thread.currentThread().getName());
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return elements;
    }

    public Long countElements(List<Long> elements) {
        long sum = 0;
        for (Long element : elements) {
            sum += element;
        }
        return sum;
    }

    private synchronized int countStartPosition(int currentThread, int arrayPartForCounting) {
        return currentThread * arrayPartForCounting;
    }

    private synchronized int countEndPosition(int startPositionForCurrentThread, int arrayPartForCounting) {
        return startPositionForCurrentThread + arrayPartForCounting;
    }
}