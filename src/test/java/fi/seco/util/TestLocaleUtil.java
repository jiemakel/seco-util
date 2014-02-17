/**
 * 
 */
package fi.seco.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;

/**
 * @author jiemakel
 * 
 */
public class TestLocaleUtil {

	@Test
	public void testParseLocaleString() {
		assertThat(LocaleUtil.parseLocaleString("fi_FI"), is(new Locale("fi", "FI")));
		assertThat(LocaleUtil.parseLocaleString("fi"), is(new Locale("fi")));
		assertThat(LocaleUtil.parseLocaleString("foo_F123_Faa"), is(new Locale("foo", "F123", "Faa")));
		assertThat(LocaleUtil.parseLocaleString("fi_FI_foo"), is(new Locale("fi", "FI", "foo")));
		assertThat(LocaleUtil.parseLocaleString("fin_FI_foo"), is(new Locale("fi", "FI", "foo")));
	}

}
