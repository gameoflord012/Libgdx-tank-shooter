import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import donut.editor.assets.AssetWatcher;
import org.junit.Test;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Assets {
    @Test
    public void AssetWatcherSerialization()
    {
        AssetWatcher assetWatcher = new AssetWatcher();

        assetWatcher.registerPath(FileSystems.getDefault().getPath("/home/manh/Desktop/New Folder"));
        assetWatcher.registerPath(FileSystems.getDefault().getPath("/home/manh/Desktop/New Folder/child1"));

        List<File> paths = new ArrayList<>();

        File file = new File("dfafs");

        paths.add(new File("/home/manh/Desktop/New Folder"));
        paths.add(new File("/home/manh/Desktop/New Folder/child1"));

        Gson gson = new Gson();

        System.out.println(gson.toJson(paths,
                TypeToken.getParameterized(List.class, File.class)
                        .getType()));
    }
}
