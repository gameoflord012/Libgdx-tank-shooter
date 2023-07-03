package donut.core.editor.assets;

import donut.core.editor.util.Serializable;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class AssetWatcher implements java.lang.Runnable, Serializable {
    public static enum EventType
    {
        FILE_CREATED,
        FILE_DELETED
    }

    public static abstract class EventListener
    {
        protected void onWatchFileModified(EventType eventType, File modifiedFile) {}
    }


    private volatile Map<WatchKey, Path> watchKeyToPath = new HashMap<>();
    private volatile WatchService watcher;
    private List<File> registeredWatchFiles = new ArrayList<>();

    public final Set<EventListener> eventListeners = new HashSet<>();

    public void registerWatchFile(File file)
    {
        try {
            Path path = file.toPath();
            WatchKey watchKey = path.register(watcher,
                    ENTRY_CREATE,
                    ENTRY_DELETE);

            watchKeyToPath.put(watchKey, path);
            registeredWatchFiles.add(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public AssetWatcher()
    {
        try {
            watcher = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run()
    {
        for (;;) {
            WatchKey watchKey;

            try {
                watchKey = watcher.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (WatchEvent<?> event: watchKey.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                EventType eventType = kind == ENTRY_CREATE ? EventType.FILE_CREATED : EventType.FILE_DELETED;

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path path = watchKeyToPath.get(watchKey).resolve(ev.context());

                for(EventListener eventListener : eventListeners)
                {
                    eventListener.onWatchFileModified(eventType, path.toFile());
                }
            }

            if (!watchKey.reset()) {
                break;
            }
        }
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        registeredWatchFiles = (List<File>)map.get("registeredWatchFiles");
        for(File file : registeredWatchFiles)
        {
            registerWatchFile(file);
        }
    }

    @Override
    public Map<String, Object> readObject() {
        Map<String, Object> map = new HashMap<>();
        map.put("registeredWatchFiles", registeredWatchFiles);
        return map;
    }
}
