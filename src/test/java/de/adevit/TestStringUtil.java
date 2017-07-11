package de.adevit;

import static org.junit.Assert.*;

import org.junit.Test;

import de.vg_soft.StringUtil;

public class TestStringUtil {
	@Test
	public void testReproduceString() {
		String orig = "Hallo";
		String target = "HalloHalloHallo";
		assertTrue("Hallo x 3 must be HalloHalloHallo", StringUtil.repoduceString(orig, 3).equals(target));
				
	}
}
