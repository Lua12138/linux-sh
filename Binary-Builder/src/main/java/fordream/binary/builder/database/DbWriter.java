package fordream.binary.builder.database;

import fordream.binary.builder.database.struts.Record;

import java.io.File;

/**
 * Created by forDream on 2016-08-09.
 */
public class DbWriter {
    private File dbBase;

    public DbWriter(File dbBase) {
        this.dbBase = dbBase;
    }

    public void writeRecord(Record record) {

    }
}
