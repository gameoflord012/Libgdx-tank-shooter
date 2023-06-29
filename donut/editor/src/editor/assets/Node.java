package editor.assets;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public String name;
    public List<Node> childNodes = new ArrayList<>();

    public List<Node> getChildNodes()
    {
        return childNodes;
    }

    public String getName()
    {
        return name;
    }

    public Node(String name)
    {
        this.name = name;
    }
}
