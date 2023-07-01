package editor.core;


import editor.assets.AssetWatcher;

public class DonutEditorLauncher {
    public static void main(String[] args)
    {
        DonutServiceManager serviceManager = new DonutServiceManager();
        serviceManager.addService(new DonutConsole());
        serviceManager.startServices();

        try {
            serviceManager.joins();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
