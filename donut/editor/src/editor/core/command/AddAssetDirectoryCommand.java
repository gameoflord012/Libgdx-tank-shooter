package editor.core.command;

import editor.core.AssetManager;
import picocli.CommandLine;

import java.nio.file.Path;

@CommandLine.Command(name = "addAssetDir")
public class AddAssetDirectoryCommand implements Runnable {
    private AssetManager assetManager;

    @CommandLine.Parameters(index = "0")
    public String arg = "con cac";
//    private Path path;
    public AddAssetDirectoryCommand(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }

    @Override
    public void run() {
//        assetManager.registerPath(path);
        System.out.println(arg);
    }
}
