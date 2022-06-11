package com.arno.domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PaperFletcher {

    public static void main(String[] args) {

        String document = "Осмотр терапевта\n" + "Пациент: %s\n" + "Дата рождения: %s\n" + "Адрес вызова: %s\n" +
                "На дому\n" + "Дата и время посещения: %s\n" + "МО: %s\n" + "Профиль: терапия\n" + "Жалобы: %s\n" +
                "Анамнез заболевания: %s\n" + "Аллергологический анамнез: %s\n" + "Объективно: %s\n" +
                "Общее состояние: %s\n" + "Телосложение: %s\n" + "Кожные прокровы: %s\n" + "Л/узлы, щ/железа: %s\n" +
                "Температура тела: %s\n" + "Зев: %s\n" + "ЧДД: %s\n" + "Артериальное давление: %s рт.ст.\n" +
                "Пульс: %s\n" + "Тоны сердца: %s\n" + "Живот: %s\n" + "Печень: %s\n" + "Диагноз: %s\n" +
                "Обследования: %s\n" + "Медикаментозная терапия: %s\n" + "Должность: %s\n" + "%s";
        String info = String.format(document);
        try(FileOutputStream fos = new FileOutputStream("C://mobdocse.txt");
            PrintStream printStream = new PrintStream(fos))
        {
            printStream.println(info);
            System.out.println("Lucky");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}