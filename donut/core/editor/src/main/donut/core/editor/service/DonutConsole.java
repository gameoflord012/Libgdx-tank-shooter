package donut.core.editor.service;

import donut.core.editor.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DonutConsole implements Runnable {

    private BufferedReader br;
    public DonutConsole()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    @Override
    public void run() {
        String header = null;
        header = Util.getResourceContent("header.txt");

        System.out.println(header);
        System.out.print(">");

        try {
            String input;
            input = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
