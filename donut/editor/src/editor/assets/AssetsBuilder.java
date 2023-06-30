package editor.assets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class AssetsBuilder
{
    public void buildAssets(List<File> assetFiles)
    {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("data", new JSONObject());
        jsonObject.put("package", "hokage");

        for(File asset : assetFiles)
        {
            String path = asset.getAbsolutePath();

            path = "data" + path.replaceAll("[ ,.]", "_");

            String[] pathElements = path.split("[^a-z,A-Z,0-9,_]");

            JSONObject node = jsonObject;

            for(String element : pathElements)
            {
                if(!node.has(element))
                {
                    node.put(element, new JSONObject());
                }

                node = node.getJSONObject(element);
            }
        }

        Map data = new Gson().fromJson(jsonObject.toString(), Map.class);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template;
        try {
            Writer out = new OutputStreamWriter(System.out);

            template = cfg.getTemplate("free.ftl");
            template.process(data, out);
            out.flush();

        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
