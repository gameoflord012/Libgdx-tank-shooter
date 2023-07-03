package donut.core.editor.service;

import java.util.HashMap;
import java.util.Map;

public class DonutServiceManager {
    Map<Runnable, Thread> threadMap = new HashMap<>();

    public void addService(Runnable service)
    {
        threadMap.put(service, new Thread(service));
    }

    public void startServices()
    {
        for(Thread value : threadMap.values())
        {
            value.start();
        }
    }

    public void joins() throws InterruptedException {
        for(Thread value : threadMap.values())
        {
            value.join();
        }
    }
}
