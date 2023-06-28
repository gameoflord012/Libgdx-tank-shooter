package editor.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    public static final String SHELL_HEADER_ASSET = "header.txt";

    public static String getTextFileAsset(String resourceName) throws IOException {
        InputStream in = Utils.class.getClassLoader().getResourceAsStream(resourceName);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }

        return sb.toString();
    }
}
