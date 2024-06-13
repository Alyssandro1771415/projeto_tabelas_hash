package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Main {        public static void main(String[] args) {
        int uniqueNumbersSize = 100000;
        int repeatedNumbersSize = 300000;
        int bound = 1000000;
        int tableSize = uniqueNumbersSize / 10;

        // Benchmark
        int size = 1000;
       
        int[] array = generateRandomArray(size);

        // Executar o benchmark e obter o tempo de execução médio
        double totalTime = 0;
        for (int i = 0; i < 10; i++) {
            totalTime += benchmark(array);
        }

        double averageTime = totalTime / 10;
        System.out.println("Tempo médio de execução: " + averageTime + " segundos\n\n");



        // Gerar listas de números aleatórios
        List<Integer> uniqueNumbers = RandomNumbersGenerator.generateUniqueRandomNumbers(uniqueNumbersSize, bound);
        List<Integer> repeatedNumbers = RandomNumbersGenerator.generateRandomNumbers(repeatedNumbersSize, bound);

        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Dataset,HashTable,AddressingType,InsertTime,SearchTime,Collisions,Rehashes\n");
        System.out.println("Dataset,HashTable,AddressingType,InsertTime,SearchTime,Collisions,Rehashes");

        // Hash Table com Endereçamento Fechado
        HashTableClosedAddressing closedAddressing = new HashTableClosedAddressing(tableSize);

        // Inserção e busca com números únicos
        long startTime = System.currentTimeMillis();
        for (int number : uniqueNumbers) {
            closedAddressing.insert(number);
        }
        long endTime = System.currentTimeMillis();
        double closedInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            closedAddressing.search(number);
        }
        endTime = System.currentTimeMillis();
        double closedSearchTime = (endTime - startTime) / 1000.0;
        int closedCollisions = closedAddressing.getCollisions();
        String closedUniqueLine = String.format(Locale.US, "100000 Unique,HashTableClosedAddressing,Closed,%.2f,%.2f,%d,0\n",
                closedInsertTime/averageTime, closedSearchTime/averageTime, closedCollisions);
        csvContent.append(closedUniqueLine);
        System.out.print(closedUniqueLine);

        // Inserção e busca com números repetidos
        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            closedAddressing.insert(number);
        }
        endTime = System.currentTimeMillis();
        closedInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            closedAddressing.search(number);
        }
        endTime = System.currentTimeMillis();
        closedSearchTime = (endTime - startTime) / 1000.0;
        closedCollisions = closedAddressing.getCollisions();
        String closedRepeatedLine = String.format(Locale.US, "300000 Repeated,HashTableClosedAddressing,Closed,%.2f,%.2f,%d,0\n",
                closedInsertTime/averageTime, closedSearchTime/averageTime, closedCollisions);
        csvContent.append(closedRepeatedLine);
        System.out.print(closedRepeatedLine);

        // Hash Table com Sondagem Linear
        HashTableOpenAddressingLinearProbing linearProbing = new HashTableOpenAddressingLinearProbing(tableSize);

        // Inserção e busca com números únicos
        startTime = System.currentTimeMillis();
        for (int number : uniqueNumbers) {
            linearProbing.insert(number);
        }
        endTime = System.currentTimeMillis();
        double linearInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            linearProbing.search(number);
        }
        endTime = System.currentTimeMillis();
        double linearSearchTime = (endTime - startTime) / 1000.0;
        int linearCollisions = linearProbing.getCollisions();
        int linearRehashes = linearProbing.getRehashCount();
        String linearUniqueLine = String.format(Locale.US, "100000 Unique,HashTableOpenAddressingLinearProbing,Linear,%.2f,%.2f,%d,%d\n",
                linearInsertTime/averageTime, linearSearchTime/averageTime, linearCollisions, linearRehashes);
        csvContent.append(linearUniqueLine);
        System.out.print(linearUniqueLine);

        // Inserção e busca com números repetidos
        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            linearProbing.insert(number);
        }
        endTime = System.currentTimeMillis();
        linearInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            linearProbing.search(number);
        }
        endTime = System.currentTimeMillis();
        linearSearchTime = (endTime - startTime) / 1000.0;
        linearCollisions = linearProbing.getCollisions();
        linearRehashes = linearProbing.getRehashCount();
        String linearRepeatedLine = String.format(Locale.US, "300000 Repeated,HashTableOpenAddressingLinearProbing,Linear,%.2f,%.2f,%d,%d\n",
                linearInsertTime/averageTime, linearSearchTime/averageTime, linearCollisions, linearRehashes);
        csvContent.append(linearRepeatedLine);
        System.out.print(linearRepeatedLine);

        // Hash Table com Sondagem Quadrática
        HashTableOpenAddressingQuadraticProbing quadraticProbing = new HashTableOpenAddressingQuadraticProbing(tableSize);

        // Inserção e busca com números únicos
        startTime = System.currentTimeMillis();
        for (int number : uniqueNumbers) {
            quadraticProbing.insert(number);
        }
        endTime = System.currentTimeMillis();
        double quadraticInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            quadraticProbing.search(number);
        }
        endTime = System.currentTimeMillis();
        double quadraticSearchTime = (endTime - startTime) / 1000.0;
        int quadraticCollisions = quadraticProbing.getCollisions();
        int quadraticRehashes = quadraticProbing.getRehashCount();
        String quadraticUniqueLine = String.format(Locale.US, "100000 Unique,HashTableOpenAddressingQuadraticProbing,Quadratic,%.2f,%.2f,%d,%d\n",
                quadraticInsertTime/averageTime, quadraticSearchTime/averageTime, quadraticCollisions, quadraticRehashes);
        csvContent.append(quadraticUniqueLine);
        System.out.print(quadraticUniqueLine);

        // Inserção e busca com números repetidos
        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            quadraticProbing.insert(number);
        }
        endTime = System.currentTimeMillis();
        quadraticInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            quadraticProbing.search(number);
        }
        endTime = System.currentTimeMillis();
        quadraticSearchTime = (endTime - startTime) / 1000.0;
        quadraticCollisions = quadraticProbing.getCollisions();
        quadraticRehashes = quadraticProbing.getRehashCount();
        String quadraticRepeatedLine = String.format(Locale.US, "300000 Repeated,HashTableOpenAddressingQuadraticProbing,Quadratic,%.2f,%.2f,%d,%d\n",
                quadraticInsertTime/averageTime, quadraticSearchTime/averageTime, quadraticCollisions, quadraticRehashes);
        csvContent.append(quadraticRepeatedLine);
        System.out.print(quadraticRepeatedLine);

        // Hash Table com Hashing Duplo
        HashTableOpenAddressingDoubleHashing doubleHashing = new HashTableOpenAddressingDoubleHashing(tableSize);

        // Inserção e busca com números únicos
        startTime = System.currentTimeMillis();
        for (int number : uniqueNumbers) {
            doubleHashing.insert(number);
        }
        endTime = System.currentTimeMillis();
        double doubleInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            doubleHashing.search(number);
        }
        endTime = System.currentTimeMillis();
        double doubleSearchTime = (endTime - startTime) / 1000.0;
        int doubleCollisions = doubleHashing.getCollisions();
        int doubleRehashes = doubleHashing.getRehashCount();
        String doubleUniqueLine = String.format(Locale.US, "100000 Unique,HashTableOpenAddressingDoubleHashing,Double,%.2f,%.2f,%d,%d\n",
                doubleInsertTime/averageTime, doubleSearchTime/averageTime, doubleCollisions, doubleRehashes);
        csvContent.append(doubleUniqueLine);
        System.out.print(doubleUniqueLine);

        // Inserção e busca com números repetidos
        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            doubleHashing.insert(number);
        }
        endTime = System.currentTimeMillis();
        doubleInsertTime = (endTime - startTime) / 1000.0;

        startTime = System.currentTimeMillis();
        for (int number : repeatedNumbers) {
            doubleHashing.search(number);
        }
        endTime = System.currentTimeMillis();
        doubleSearchTime = (endTime - startTime) / 1000.0;
        doubleCollisions = doubleHashing.getCollisions();
        doubleRehashes = doubleHashing.getRehashCount();
        String doubleRepeatedLine = String.format(Locale.US, "300000 Repeated,HashTableOpenAddressingDoubleHashing,Double,%.2f,%.2f,%d,%d\n",
                doubleInsertTime/averageTime, doubleSearchTime/averageTime, doubleCollisions, doubleRehashes);
        csvContent.append(doubleRepeatedLine);
        System.out.print(doubleRepeatedLine);

        // Write CSV to file
        try (FileWriter writer = new FileWriter("./hash_table_results.csv")) {
            writer.write(csvContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int sumArray(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }
        return array[index] + sumArray(array, index + 1);
    }

    public static double benchmark(int[] array) {
        long startTime = System.nanoTime();
        int sum = sumArray(array, 0);
        long endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime) / 1000000000.0; // Converter para segundos
        System.out.println("Soma dos elementos do array: " + sum);
        return elapsedTime;
    }
}
