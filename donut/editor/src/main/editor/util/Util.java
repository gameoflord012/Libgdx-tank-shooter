package editor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {
    public static String readeResourceContent(String resourceName) {
        InputStream in = getResourceInputStream(resourceName);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuilder sb = new StringBuilder();
        String line;
        while (true) {
            try {
                line = br.readLine();
                if(line == null) break;
            } catch (IOException e) {
                break;
            }
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public static InputStream getResourceInputStream(String resourceName)
    {
        return Util.class.getClassLoader().getResourceAsStream(resourceName);
    }
}
