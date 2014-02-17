/**
 * 
 */
package fi.seco.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * @author jiemakel
 * 
 */
public class TestByteArrayLongUtil {

	@Test
	public void testMapping() {
		long t = 143514513451345l;
		byte[] bt = new byte[8];
		ByteArrayLongUtil.longToByteArray(t, bt, 0);
		ByteBuffer bb = ByteBuffer.allocate(8);
		bb.putLong(t);
		assertThat(bt, equalTo(bb.array()));
		assertThat(ByteArrayLongUtil.byteArrayToLong(bb.array()), is(t));
		assertThat(ByteArrayLongUtil.byteArrayToLong(bt), is(t));
	}
}
