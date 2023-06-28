package editor.core;

import editor.core.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;


public class DonutEditorLauncher {
    public static void main(String[] args)
    {
        AssetManager assetManager = new AssetManager();
        Thread thread = new Thread(assetManager);
        thread.start();

        assetManager.registerPath(Paths.get("/home/manh/Desktop/New Folder/"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String header = null;
        try {
            header = Utils.getTextFileAsset(Utils.SHELL_HEADER_ASSET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(header);

        while(true)
        {
            String input;
            System.out.print(">");

            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
