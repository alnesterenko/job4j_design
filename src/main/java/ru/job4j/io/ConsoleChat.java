package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> answers = readPhrases();
        List<String> logList = new ArrayList<>();
        String userSaid = "";
        boolean shutUp = false;
        String greeting = "Чат запущен: ";
        System.out.println(greeting);
        logList.add(greeting);
        do {
            userSaid = scanner.nextLine();
            logList.add("пользователь: " + userSaid);
            if (OUT.equals(userSaid) || STOP.equals(userSaid)) {
                shutUp = true;
            }
            if (CONTINUE.equals(userSaid)) {
                shutUp = false;
            }
            if (!shutUp) {
                String botAnswer = answerFromBot(answers);
                logList.add("бот: " + botAnswer);
                System.out.println(botAnswer);
            }
        } while (!OUT.equals(userSaid));
        saveLog(logList);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
           answers = reader.lines()
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), false))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String answerFromBot(List<String> answers) {
        Random random = new Random();
        String answer = answers.get(random.nextInt(answers.size()));
        return switch (answer) {
            case "", " ", "\n", "\t" -> "...";
            default -> answer;
        };
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data\\consoleChatLog.txt",
                "data\\botAnswers.txt");
        consoleChat.run();
    }
}
