package jgit.objects.source;

import jgit.AbstractTempFileWithRandomDataIntegrationTest;
import jgit.objects.GitObjectType;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.lang.ArrayUtils;

import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.channels.Channels;

public class FileChannelBlobSourceIntegrationTest extends AbstractTempFileWithRandomDataIntegrationTest {
    private FileChannelBlobSource fileChannelBlobSource;

    @Before
    public void setUp() throws Exception {
        FileInputStream inputStream = new FileInputStream(tempFile);
        fileChannelBlobSource = new FileChannelBlobSource(inputStream.getChannel());
    }

    @Test
    public void testCopyTo() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        fileChannelBlobSource.copyTo(Channels.newChannel(output));
        byte[] buffer = output.toByteArray();
        String expectedHeaderString = String.format("%s %d", GitObjectType.BLOB, testData.length);
        byte[] expectedHeader = ArrayUtils.add(expectedHeaderString.getBytes(), (byte) 0);
        byte[] header = ArrayUtils.subarray(buffer, 0, expectedHeader.length);
        Assert.assertTrue("Header should be of format \"type(space)size(null byte)\"",
                ArrayUtils.isEquals(expectedHeader, header));
        byte[] content = ArrayUtils.subarray(buffer, expectedHeader.length, buffer.length);
        Assert.assertTrue(ArrayUtils.isEquals(testData, content));
    }
}
