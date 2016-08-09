import fordream.binary.builder.scanner.DirectoryScanner;
import fordream.binary.builder.hash.SHA256Calculator;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by forDream on 2016-08-09.
 */
public class DirectoryScannerTestCase {
    private String getFileName(String path) {
        int a = path.lastIndexOf("/");
        int b = path.lastIndexOf("\\");
        a = a > b ? a : b;
        return path.substring(a + 1);
    }

    @Test
    public void scannerAndSHA256CalculatorTest() throws IOException {
        Properties properties = new Properties();
        InputStream is = new BufferedInputStream(new FileInputStream("src/test/resources/ScannerTestDir/TestResult"));
        properties.load(is);
        is.close();

        DirectoryScanner scanner = new DirectoryScanner(new File("src/test/resources/ScannerTestDir/"), SHA256Calculator.instance());
        Map<String, String> map = scanner.runWithHex();

        Assert.assertEquals(Integer.parseInt(properties.getProperty("Total")), map.size());

        int counter = 0;
        // Check sum
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            String fileName = getFileName(key);
            if (!fileName.equalsIgnoreCase("TestResult")) {
                // Windows, the need to "\" replaced by "/""
                Assert.assertEquals(properties.getProperty(key.replace('\\','/')), map.get(key));
                counter++;
            }
        }

        Assert.assertEquals(map.size() - 1, counter); // Check for missing
    }
}
