/**
 * 
 */
package fi.seco.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fi.seco.util.MediaTypeUtil.MediaType;

/**
 * @author jiemakel
 * 
 */
public class TestMediaTypeUtil {

	@Test
	public void testGetMediaTypesForAcceptHeaderSortedByPreference() {
		MediaType[] tmp = MediaTypeUtil.getMediaTypesForAcceptHeaderSortedByPreference("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		assertThat(tmp[0].getMimeType(), is("text/html"));
		assertThat(tmp[0].getQuality(), is(1.0f));
		assertThat(tmp[1].getMimeType(), is("application/xhtml+xml"));
		assertThat(tmp[1].getQuality(), is(1.0f));
		assertThat(tmp[2].getMimeType(), is("application/xml"));
		assertThat(tmp[2].getQuality(), is(0.9f));
		assertThat(tmp[3].getMimeType(), is("*/*"));
		assertThat(tmp[3].getQuality(), is(0.8f));
	}
}
