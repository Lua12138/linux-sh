package fordream.binary.builder.config;

/**
 * Created by forDream on 2016-08-09.
 */
public class Application {
    public static String version() {
        return "20160809.2208";
    }

    public static String currentWorkspace() {
        return System.getProperty("user.dir");
    }
}
