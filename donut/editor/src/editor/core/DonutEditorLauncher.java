package editor.core;

import editor.assets.AssetWatcher;
import editor.assets.AssetsBuilder;
import editor.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;


public class DonutEditorLauncher {
    public static void main(String[] args)
    {
        AssetWatcher assetManager = new AssetWatcher();
        Thread thread = new Thread(assetManager);
        thread.start();

        assetManager.registerPath(Paths.get("/home/manh/Desktop/New Folder/"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String header = null;
        header = Util.readeResourceContent(Util.SHELL_HEADER_ASSET);
        System.out.println(header);

        new AssetsBuilder().test();

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
