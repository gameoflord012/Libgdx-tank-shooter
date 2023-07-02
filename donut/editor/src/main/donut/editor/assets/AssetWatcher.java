package donut.editor.assets;

import donut.editor.util.Serializable;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class AssetWatcher implements Runnable, Serializable {
    private volatile Map<WatchKey, Path> getDirectoryByKey = new HashMap<>();
    private volatile WatchService watcher;
    private List<File> registeredWatchFiles = new ArrayList<>();

    public void registerWatchFile(File file)
    {
        try {
            Path path = file.toPath();
            WatchKey watchKey = path.register(watcher,
                    ENTRY_CREATE,
                    ENTRY_DELETE);

            getDirectoryByKey.put(watchKey, path);
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
            WatchKey key;

            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();

                try {
                    Path child = getDirectoryByKey.get(key).resolve(filename);
                    if (!Files.probeContentType(child).equals("text/plain")) {
                        System.err.format("New file '%s'" +
                                " is not a plain text file.%n", filename);
                        continue;
                    }
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
                }

                System.out.format("Emailing file %s%n", filename);
            }

            boolean valid = key.reset();
            if (!valid) {
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
