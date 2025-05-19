package fableforge.app;

import fableforge.core.StoreGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter you Prompt");
        String topic = sc.nextLine();
        if (topic == "") {
            System.out.println("Enter prompt first");
        } else {
            String story = StoreGenerator.generateStory(topic);
            System.out.println("Generated Story:");
            System.out.println(story);
        }

    }
}
