package fordream.binary.builder.database;

/**
 * Created by forDream on 2016-08-09.
 */
public class DbHelper {
    public static int[] splitToInt(byte[] bytes) {
        int size = bytes.length / 4;
        int bound = 4;
        boolean flag = true;
        if (size * 4 < bytes.length) size++;

        int[] result = new int[size];
        for (int i = 0; i < size && flag; i++) {
            for (int j = 0; j < bound; j++) {
                if (i >= size - 1) {
                    size = Integer.MAX_VALUE;
                    bound = size % 4 - 1;
                    j = -1;
                    flag = false;
                    continue;
                }
                int tmp = bytes[i * 4 + j] & 0xFF;
                tmp <<= (j * 8);
                result[i] |= tmp;
            }
        }
        return result;
    }

    public static byte[] splitToBytes(int ints) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((ints >> (i * 8)) & 0xFF);
        }
        return bytes;
    }
}
