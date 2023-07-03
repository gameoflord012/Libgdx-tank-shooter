package donut.core.api.utility;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {
    private Utility() {}

    private static Utility instance;
    public static Utility getInstance()
    {
        if(instance == null)
        {
            instance = new Utility();
        }

        return instance;
    }

    public int randomInt(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
