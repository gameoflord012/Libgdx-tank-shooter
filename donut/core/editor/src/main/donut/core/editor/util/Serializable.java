package donut.core.editor.util;

import org.json.JSONObject;

import java.util.Map;

public interface Serializable  {
    void writeObject(Map<String, Object> map);
    Map<String, Object> readObject();
}
