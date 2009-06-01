package jgit.storage.loose;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class LooseStorageNodeWritableChannelIntegrationTest extends AbstractLooseStorageNodeChannelIntegrationTest {
    @Test
    public void testWrite() throws IOException {
        LooseStorageNodeWritableChannel channel
                = new LooseStorageNodeWritableChannel(new LooseStorageLayoutMock(), objectName);
        Assert.assertTrue("Channel should be open", channel.isOpen());
        Assert.assertEquals(testData.length, channel.write(ByteBuffer.wrap(testData)));
        channel.close();
        File objectDir = new File(tempDir, objectName.substring(0, 2)); // object dir name should be 2 chars length
        Assert.assertTrue("Object directory should exist", objectDir.isDirectory());
        File objectFile = new File(objectDir, objectName.substring(2)); // tail of object name is name for object file
        Assert.assertTrue("Object file should exist", objectFile.isFile());
        Assert.assertTrue(ArrayUtils.isEquals(FileUtils.readFileToByteArray(objectFile), testData));
    }

    @Test
    public void testWriteWithExistingObjectDirectory() throws IOException {
        File objectDir = new File(tempDir, objectName.substring(0, 2));
        Assert.assertTrue(objectDir.mkdir());
        LooseStorageNodeWritableChannel channel
                = new LooseStorageNodeWritableChannel(new LooseStorageLayoutMock(), objectName);
        channel.write(ByteBuffer.wrap(testData));
        channel.close();
    }
}
