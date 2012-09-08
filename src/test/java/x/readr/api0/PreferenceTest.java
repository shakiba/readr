package x.readr.api0;

import org.testng.annotations.Test;

import x.readr.api0.model.Api0Prefs;
import x.readr.api0.req.read.PreferenceList;
import x.readr.test.GenericRequestTest;

@Test
public class PreferenceTest extends GenericRequestTest {

    public void list() throws Exception {
        Api0Prefs execute = new PreferenceList().execute(oauth);
        gson.toJson(execute, System.out);
    }
}