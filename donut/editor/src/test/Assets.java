import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import donut.editor.assets.AssetWatcher;
import donut.editor.util.Util;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Assets {
    @Test
    public void AssetWatcherSerialization() throws IOException {
        AssetWatcher assetWatcher = new AssetWatcher();

        BufferedReader br = Util.getResourceBufferedReader("AssetWatcherSerialization.i");

        while(true)
        {
            String line = br.readLine();
            if(line == null) break;
            assetWatcher.registerWatchFile(new File(line));
        }

        Gson gson = new Gson();
        String serializedOutput = gson.toJson(assetWatcher.readObject(), TypeToken.getParameterized(Map.class, String.class, Object.class).getType());
        serializedOutput = Util.makeJsonPretty(serializedOutput);

        Assert.assertEquals(
                Util.getResourceContent("AssetWatcherSerialization.o").replaceAll("[\n, ]", ""),
                serializedOutput.replaceAll("[\n, ]", ""));
    }
}
