import fordream.binary.builder.database.DbHelper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by forDream on 2016-08-09.
 */
public class DatabaseTestCase {
    private byte[] bytes = {(byte) 0x80, 0x10, 0x20, 0x30, // 0111 1111 0001 0000 0010 0000 0011 0000
            (byte) 0xFF, (byte) 0xEE, (byte) 0xDD, (byte) 0xCC,
            (byte) 0x99, (byte) 0x88};

    @Test
    public void DbHelperTest() {
        int[] intResult = DbHelper.splitToInt(bytes);

        int bytePos = 0;
        for (int ints : intResult) {
            byte[] by = DbHelper.splitToBytes(ints);
            for (byte b : by)
                if (bytePos >= this.bytes.length)
                    Assert.assertEquals(0x00, b);
                else
                    Assert.assertEquals(this.bytes[bytePos++], b);
        }
    }
}
