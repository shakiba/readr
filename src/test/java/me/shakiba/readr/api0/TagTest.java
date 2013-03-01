package me.shakiba.readr.api0;

import me.shakiba.readr.ItemIdShort;
import me.shakiba.readr.StreamId;
import me.shakiba.readr.StreamIdUserLabel;
import me.shakiba.readr.api0.model.Api0Stream;
import me.shakiba.readr.api0.model.Api0Tag;
import me.shakiba.readr.api0.model.Api0TagList;
import me.shakiba.readr.api0.req.read.ItemContent;
import me.shakiba.readr.api0.req.read.TagList;
import me.shakiba.readr.api0.req.write.DisableTag;
import me.shakiba.readr.api0.req.write.EditTag;
import me.shakiba.readr.test.GenericRequestTest;
import me.shakiba.readr.test.Sample;

import org.testng.Assert;

//@Test(singleThreaded = true)
public class TagTest extends GenericRequestTest {

    ItemIdShort itemId;
    String tagName;
    StreamIdUserLabel tagId;

    public TagTest prepare() throws Exception {
        itemId = Sample._98.toShort();
        tagName = "readr-" + System.currentTimeMillis();
        tagId = StreamId.label(tagName);
        return this;
    }

    public TagTest before() throws Exception {

        new EditTag(itemId, actionToken()).add(tagId).execute(oauth);

        Api0Stream content = new ItemContent(false, itemId).execute(oauth);
        Assert.assertTrue(content.items.get(0).categories.contains(tagId));

        Api0TagList tags = new TagList().execute(oauth);
        boolean founded = false;
        for (Api0Tag tag : tags.tags) {
            if (tag.id.equals(tagId)) {
                founded = true;
            }
        }
        Assert.assertTrue(founded);
        return this;
    }

    public TagTest after() {
        new EditTag(itemId, actionToken()).remove(tagId).execute(oauth);

        Api0Stream content = new ItemContent(false, itemId).execute(oauth);
        Assert.assertFalse(content.items.get(0).categories.contains(tagId));

        new DisableTag(tagId, actionToken()).execute(oauth);

        Api0TagList tags = new TagList().execute(oauth);
        boolean founded = false;
        for (Api0Tag tag : tags.tags) {
            if (tag.id.equals(tagId)) {
                founded = true;
            }
        }
        Assert.assertFalse(founded);
        return this;
    }
}