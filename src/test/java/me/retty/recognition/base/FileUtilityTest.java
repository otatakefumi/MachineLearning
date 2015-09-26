package me.retty.recognition.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import junit.framework.TestCase;

/**
 * Created by takefumiota on 2015/09/25.
 */
public class FileUtilityTest extends TestCase {
    String DATA_BASE_DIR = "src/test/data/dimension";

    public void testGetPatternFilePathMap() throws Exception {
        Optional<Map<String, List<String>>> patternFilePathMapOpt = FileUtility.getPatternFilePathMap(DATA_BASE_DIR);
        assertTrue(patternFilePathMapOpt.isPresent());
        assertEquals(patternFilePathMapOpt.get().size(), 1);
        assertEquals(patternFilePathMapOpt.get().get("1").size(), 2);
    }
}