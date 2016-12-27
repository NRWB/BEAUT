
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
2. Load a properties file
Load a properties file from the file system and retrieved the property value.
*/

public class ConfigRead {

    private String fp;
    private Properties prop;

    public ConfigRead(String file) {
        this.fp = file;
        this.prop = new Properties();
    }

    public void load() {
        try (InputStream in = new FileInputStream(this.fp)) {
            this.prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get the value, given key
    public Object get(String key) {
        return prop.getProperty(key);
    }
}
