package editor.core;

import java.nio.file.Paths;

public class DonutEditorLauncher {
    public static void main(String[] args)
    {
        System.out.print("hello world");

        AssetManager assetManager = new AssetManager();
        Thread thread = new Thread(assetManager);
        thread.start();

        assetManager.registerPath(Paths.get("/home/manh/Desktop/New Folder/"));

        while(true);
    }
}
