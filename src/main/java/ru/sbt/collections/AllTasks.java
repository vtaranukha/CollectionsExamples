package ru.sbt.collections;


import org.apache.commons.io.IOUtils;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Consumer;

/**
 * Подсчитайте количество различных слов в файле.
 */
class Comp implements Comparator<String> {
    @Override
    public int compare(String obj1, String obj2) {
        return obj1.length()-obj2.length();
    }
}
public class AllTasks {
    public static String getText() throws IOException {
        InputStream resourceAsStream = AllTasks.class.getResourceAsStream("/ru/sbt/collections/VeryBigText.txt");
        return IOUtils.toString(resourceAsStream, "UTF8");
    }
    public static void task1() throws IOException {
        //Задание 1: Подсчитайте количество различных слов в файле.
        String lines = getText();
        String[] words = lines.split("[\\s\\.\\,\\(\\):;-]");
        Set<String> wordsSet = new HashSet<>();
        wordsSet.addAll(Arrays.asList(words));
        System.out.println(wordsSet.size());
    }
    public static void task2() throws IOException {
        //Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины.
        String lines = getText();
        String[] words = lines.split("[\\s\\.\\,\\(\\):;-]");
        Set<String> wordsSet = new HashSet<>();
        wordsSet.addAll(Arrays.asList(words));
        String[] diffWords = wordsSet.toArray(new String[wordsSet.size()]);
        Comp comp = new Comp();
        Arrays.sort(diffWords, comp);
        for (String st : diffWords) {
            System.out.println(st);
        }
    }
    public static void task3() throws IOException {
        //Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
        String lines = getText();
        String[] words = lines.split("[\\s\\.\\,\\(\\):;-]");
        Map<String, Integer> map = new HashMap<>();
        for (String st: words) {
            if(map.containsKey(st))
                map.put(st, map.get(st)+1);
            else
                map.put(st,1);
        }
        int sum=0;
        for (String st : map.keySet()) {
            System.out.println(st + " : " + map.get(st));
        }
        System.out.println(sum);
    }
    public static void task4() throws IOException {
        //Задание 4: Выведите на экран все строки файла в обратном порядке.
        String lines = getText();
        String[] strings = lines.split("\n");
        for (int i=strings.length-1;i >=0;i--){
            System.out.println(strings[i]);
        }
    }
    public static void task5() throws IOException {
        //Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
        class MyArray implements Iterable<String> {
            class MyIterator implements Iterator {
                private int index = array.length - 1;

                @Override
                public boolean hasNext() {
                    return index >= 0;
                }

                @Override
                public String next() {
                    return array[index--];
                }
            }

            String[] array;

            public Iterator<String> iterator() {
                return new MyIterator();
            }
        }
        String lines = getText();
        String[] strings = lines.split("\n");
        MyArray myArray = new MyArray();
        myArray.array = strings;
        for (String st: myArray) {
            System.out.println(st);
        }
    }
    public static void task6() throws IOException {
        //Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
        String lines = getText();
        String[] strings = lines.split("\n");
        Scanner in = new Scanner(System.in);
        System.out.println("Вводите номера строки, которые вам необходимы. Для завершения введите 'end'");
        String input;
        int i;
        while(true){
            input = in.next();
            if(input.equals("end")) break;
            try {
                i=Integer.parseInt(input);
                if(i>=0 & i<strings.length)
                    System.out.println(strings[i]);
                else
                    System.out.println("Некорректный ввод! Введите число от 0 до " + Integer.toString(strings.length-1));
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Введите целое число или end...");
            }
        }
        System.out.println("Пока");
    }
    public static void main(String[] args) throws IOException, URISyntaxException {
        //task1();
        //task2();
        //task3();
        //task4();
        //task5();
        task6();
    }
}