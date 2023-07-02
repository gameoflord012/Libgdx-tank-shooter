package donut.editor.assets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;
import java.util.Map;

public class AssetsBuilder
{
    private JSONObject dataModel;
    private File output;
    public AssetsBuilder(String packageName, String className, File out)
    {
        JSONObject dataModel = new JSONObject();

        dataModel.put("data", new JSONObject());
        dataModel.put("packageName", packageName);
        dataModel.put("className", className);
    }

    public void buildAssets(List<File> assetFiles)
    {
        for(File asset : assetFiles)
        {
            String path = asset.getAbsolutePath();

            path = "data" + path.replaceAll("[ ,.]", "_");

            String[] pathElements = path.split("[^a-z,A-Z,0-9,_]");

            JSONObject node = dataModel;

            for(int i = 0; i < pathElements.length - 1; i++)
            {
                String element = pathElements[i];

                if(!node.has(element))
                {
                    node.put(element, new JSONObject());
                }

                node = node.getJSONObject(element);
            }

            node.put(pathElements[pathElements.length - 1], asset.getAbsolutePath());
        }

        Map data = new Gson().fromJson(dataModel.toString(), Map.class);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template;
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream(output));

            template = cfg.getTemplate("AssetsBuilder.ftl");
            template.process(data, out);
            out.flush();

        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
