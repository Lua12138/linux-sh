package fordream.binary.builder.hash;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.io.*;

/**
 * Created by forDream on 2016-08-09.
 */
public class SHA256Calculator extends AbsHashCalculator {
    private static SHA256Calculator instance;

    public static SHA256Calculator instance() {
        if (instance == null)
            synchronized (System.out) {
                if (instance == null)
                    instance = new SHA256Calculator();
            }
        return instance;
    }

    private SecurityHelper helper;
    private byte[] hashResult;

    private SHA256Calculator() {
        this.helper = new SecurityHelper();
        this.helper.reset();
        this.hashResult = null;
    }

    @Override
    public void reset() {
        this.helper.reset();
        this.hashResult = null;
    }

    @Override
    public byte[] update(byte[] buffer, int startIndex, int offset) {
        return this.helper.hash(SecurityHelper.Algorithms.SHA256, buffer, startIndex, offset, false);
    }

    @Override
    public byte[] update(String str) {
        byte[] bytes = str.getBytes();
        return this.update(bytes, 0, bytes.length);
    }

    @Override
    public byte[] update(File file) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        byte[] result = this.helper.hash(SecurityHelper.Algorithms.SHA256, is, false);
        is.close();
        return result;
    }

    @Override
    public byte[] getValue() {
        if (this.hashResult == null)
            this.hashResult = this.helper.digest();
        return this.hashResult;
    }

    @Override
    public String getHexValue() {
        return this.helper.toHexStr(this.getValue());
    }
}
