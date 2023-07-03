import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import donut.core.editor.assets.AssetWatcher;
import donut.core.editor.util.Util;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Assets {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void AssetWatcherSerialization() throws IOException {
        AssetWatcher assetWatcher = new AssetWatcher();

        BufferedReader br = Util.getResourceBufferedReader("AssetWatcherSerialization.i");


        assetWatcher.registerWatchFile(folder.newFolder("AssetWatcherSerialization/"));
        assetWatcher.registerWatchFile(folder.newFolder("AssetWatcherSerialization2/"));

        folder.newFile("AssetWatcherSerialization/text1.txt");
        folder.newFile("AssetWatcherSerialization/text2.txt");
        folder.newFile("AssetWatcherSerialization2/text1.txt");

        Gson gson = new Gson();
        String serializedOutput = gson.toJson(assetWatcher.readObject(), TypeToken.getParameterized(Map.class, String.class, Object.class).getType());
        serializedOutput = Util.makeJsonPretty(serializedOutput);

        Assert.assertEquals(
                Util.getResourceContent("AssetWatcherSerialization.o").replaceAll("[\n ]", ""),
                serializedOutput.replaceAll("(\n| |junit\\d+)", ""));
    }

    @Test
    public void AssetWatcherEventListener() throws InterruptedException, IOException {
        AssetWatcher assetWatcher = new AssetWatcher();
        File dir = folder.newFolder("AssetWatcherEventListener/");

        assetWatcher.registerWatchFile(dir);

        Thread thread = new Thread(assetWatcher);
        thread.start();

        assetWatcher.eventListeners.add(new AssetWatcher.EventListener()
        {
            @Override
            protected void onWatchFileModified(AssetWatcher.EventType eventType, File modifiedFile) {
                Assert.assertEquals("FILE_CREATED", eventType.name());
                Assert.assertEquals("hello.txt", modifiedFile.getName());
                thread.interrupt();
            }
        });

        folder.newFile("AssetWatcherEventListener/hello.txt");
        thread.join();
    }
}
