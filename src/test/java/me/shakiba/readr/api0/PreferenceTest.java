package me.shakiba.readr.api0;

import me.shakiba.readr.GenericRequestTest;
import me.shakiba.readr.api0.model.Api0Prefs;
import me.shakiba.readr.api0.req.read.PreferenceList;

import org.testng.annotations.Test;

@Test
public class PreferenceTest extends GenericRequestTest {

    public void list() throws Exception {
        Api0Prefs execute = new PreferenceList().execute(oauth);
        gson.toJson(execute, System.out);
    }
}