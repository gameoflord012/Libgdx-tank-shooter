package donut.editor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {
    public static String getResourceContent(String resourceName) {
        BufferedReader br = getResourceBufferedReader(getResourceInputStream(resourceName));

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

    public static BufferedReader getResourceBufferedReader(InputStream is)
    {
        return new BufferedReader(new InputStreamReader(is));
    }

    public static BufferedReader getResourceBufferedReader(String resourceName)
    {
        return getResourceBufferedReader(getResourceInputStream(resourceName));
    }
}
