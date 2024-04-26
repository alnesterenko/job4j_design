package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(new File(argsName.get("path"))).useDelimiter(System.lineSeparator());
        String delimiter = argsName.get("delimiter");
        List<Integer> numbersOfColumn = getNumbersOfColumn(argsName, scanner.nextLine());
        List<String> resultList = new ArrayList<>();
        resultList.add(argsName.get("filter").replaceAll("[,;]", delimiter));
        while (scanner.hasNextLine()) {
            StringJoiner joiner = new StringJoiner(delimiter);
            List<String> tempStringList = Arrays.asList(scanner.nextLine().split(delimiter));
            for (Integer oneNumber : numbersOfColumn) {
                joiner.add(tempStringList.get(oneNumber));
            }
            resultList.add(joiner.toString());
        }
        if ("stdout".equals(argsName.get("out"))) {
            printResult(resultList);
        } else {
            saveResult(resultList, argsName.get("out"));
        }
        scanner.close();
    }

    private static void printResult(List<String> resultList) {
        for (String oneLine : resultList) {
            System.out.println(oneLine);
        }
    }

    private static void saveResult(List<String> resultList, String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), false))) {
            resultList.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> getNumbersOfColumn(ArgsName argsName, String header) throws Exception {
        List<String> namesOfColumn = Arrays.asList(argsName.get("filter").split(","));
        List<String> allNamesOfColumn = Arrays.asList(header.split(argsName.get("delimiter")));
        List<Integer> listOfColumnNumbers = new ArrayList<>();
        for (String oneNameOfColumn : namesOfColumn) {
            listOfColumnNumbers.add(allNamesOfColumn.indexOf(oneNameOfColumn));
        }
        return listOfColumnNumbers;
    }

    private static void parametersValidation(ArgsName argsName) {
        if (argsName.getSize() != 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File: '%s' does not exist", file.getAbsolutePath()));
        }
        if (!"stdout".equals(argsName.get("out"))) {
           file = new File(argsName.get("out"));
            if (!file.exists()) {
                throw new IllegalArgumentException(String.format("File: '%s' does not exist", file.getAbsolutePath()));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        parametersValidation(argsName);
        handle(argsName);
    }

    /*
     -path=file.csv -delimiter=;  -out=stdout -filter=name,age
     */
}