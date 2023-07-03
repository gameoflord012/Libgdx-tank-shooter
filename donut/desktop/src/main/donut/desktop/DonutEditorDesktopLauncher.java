package donut.desktop;

import donut.core.editor.service.DonutConsole;
import donut.core.editor.service.DonutServiceManager;

public class DonutEditorDesktopLauncher {
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
