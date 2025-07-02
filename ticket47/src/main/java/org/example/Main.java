package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        DailyReportMailer run = new DailyReportMailer();
        run.setupDriver();
        run.signin();
        run.outlook();

    }
}