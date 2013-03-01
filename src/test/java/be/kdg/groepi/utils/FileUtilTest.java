package be.kdg.groepi.utils;

import org.apache.commons.fileupload.FileItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 28-2-13
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */
public class FileUtilTest {
    private MockMultipartFile file;

    @Before
    public void beforeEachTest() {
        file = new MockMultipartFile("Test.txt", "Test".getBytes());
    }

    @After
    public void afterEachTest() {

    }

    @Test
    public void testUpload() throws IOException {
        assertTrue("File has not been uploaded",FileUtil.savePicture(file,1));
    }
}