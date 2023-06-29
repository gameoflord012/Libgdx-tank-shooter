package editor.assets;

import com.google.gson.Gson;
import editor.util.Util;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Map;

public class AssetsBuilder
{
    private JSONObject loadJsonAssetModel()
    {
        return new JSONObject(Util.readeResourceContent("json.json"));
    }
    public void buildAssetPath(Path assetPath)
    {

    }

    public void test()
    {

        Map<String, Object> data = new
                Gson().fromJson(loadJsonAssetModel().toString(), Map.class);

        Configuration cfg = new Configuration();

        cfg.setClassForTemplateLoading(AssetsBuilder.class, "/");

        BeansWrapper wrapper = new BeansWrapper();
        wrapper.setExposureLevel(BeansWrapper.EXPOSE_ALL);

        cfg.setObjectWrapper(wrapper);



        Template template = null;
        try {
            template = cfg.getTemplate("free.ftl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Writer out = new OutputStreamWriter(System.out);

        try {
            template.process(data, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
