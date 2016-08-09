package fordream.binary.builder;

import fordream.binary.builder.hash.SHA256Calculator;
import fordream.binary.builder.scanner.DirectoryScanner;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by forDream on 2016-08-09.
 */
public class Startup {
    protected static boolean stdPrintlnAndReturn(boolean bool, String message) {
        if (bool)
            System.out.println(message);
        return bool;
    }

    public static void main(String[] args) {
        final String message_no_args = "Requires a string to specify a directory that needs to be scanned";
        final String message_no_dir = "The specified directory does not exist or has no permissions. You must specify a directory, not a file.";

        if (stdPrintlnAndReturn(args.length < 1, message_no_args)) return;

        File scanDir = new File(args[0]);
        if (stdPrintlnAndReturn((!scanDir.exists()) || scanDir.isFile(), message_no_dir)) return;

        Map<String, String> map = new DirectoryScanner(scanDir, SHA256Calculator.instance()).runWithHex();
    }
}
