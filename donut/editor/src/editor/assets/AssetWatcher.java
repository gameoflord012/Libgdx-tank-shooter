package editor.assets;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class AssetWatcher implements Runnable {
    private volatile Map<WatchKey, Path> getDirectoryByKey = new HashMap<>();
    private volatile WatchService watcher;

    public void registerPath(Path unregistedPath)
    {
        try {
            WatchKey key = unregistedPath.register(watcher,
                    ENTRY_CREATE,
                    ENTRY_DELETE,
                    ENTRY_MODIFY);

            getDirectoryByKey.put(key, unregistedPath);
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
}
