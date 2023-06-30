package editor.core;

import editor.assets.AssetWatcher;
import editor.assets.AssetsBuilder;
import editor.util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class DonutEditorLauncher {
    public static void main(String[] args)
    {
        AssetWatcher assetManager = new AssetWatcher();
        Thread thread = new Thread(assetManager);
        thread.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String header = null;
        header = Util.readeResourceContent(Util.SHELL_HEADER_ASSET);
        System.out.println(header);

        List<File> assets = new ArrayList<>();
        assets.add(new File("/home/manh/Desktop/txt1.txt"));
        assets.add(new File("/home/manh/Desktop/txt2.txt"));

        new AssetsBuilder().buildAssets(assets);

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
