package fordream.binary.builder.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by forDream on 2016-08-09.
 */
public interface HashCalculator {
    /**
     * reset the result
     */
    void reset();

    /**
     * calc the hash of buffer
     *
     * @param buffer
     * @param startIndex
     * @param offset
     * @return
     */
    byte[] update(byte[] buffer, int startIndex, int offset);

    /**
     * calc the hash of String
     *
     * @param str
     * @return
     */
    byte[] update(String str);

    /**
     * calc the hash of File
     *
     * @param file
     * @return
     */
    byte[] update(File file) throws IOException;

    /**
     * @return get the result of hash
     */
    byte[] getValue();

    /**
     * @return get the result of hash(HEX)
     */
    String getHexValue();
}
