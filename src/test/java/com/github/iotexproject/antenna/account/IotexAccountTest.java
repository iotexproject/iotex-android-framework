package com.github.iotexproject.antenna.account;

import com.github.iotexproject.antenna.utils.Numeric;
import org.junit.Test;

import java.security.Security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * iotex account test.
 *
 * @author Yang XuePing
 */
public class IotexAccountTest {
    public static final String TEST_PRIVATE = "0806c458b262edd333a191e92f561aff338211ee3e18ab315a074a2d82aa343f";
    public static final String TEST_PUBLIC = "044e18306ae9ef4ec9d07bf6e705442d4d1a75e6cdf750330ca2d880f2cc54607c9c33deb9eae9c06e06e04fe9ce3d43962cc67d5aa34fbeb71270d4bad3d648d9";
    public static final String TEST_ADDRESS = "io187wzp08vnhjjpkydnr97qlh8kh0dpkkytfam8j";

    @Test
    public void testCreateByPrivate() {
        Account account = IotexAccount.create(Numeric.hexStringToByteArray(TEST_PRIVATE));
        assertEquals(TEST_PRIVATE, Numeric.toHexString(account.privateKey()));
        assertEquals(TEST_PUBLIC, Numeric.toHexString(account.publicKey()));
        assertEquals(TEST_ADDRESS, account.address());
    }

    @Test
    public void testCreate() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        for (int i = 0; i < 100; i++) {
            Account account = IotexAccount.create();
            assertNotNull(account);
            assertNotNull(account.privateKey());
            assertEquals(32, account.privateKey().length);
            assertEquals(65, account.publicKey().length);
            assertEquals(41, account.address().length());
            assertEquals("io", account.address().substring(0, 2));
        }
    }

    @Test
    public void testSign() {
        Account account = IotexAccount.create(Numeric.hexStringToByteArray(TEST_PRIVATE));
        byte[] sign = account.sign("IoTeX is the auto-scalable and privacy-centric blockchain.".getBytes());
        assertEquals(
                "99f4ef1005ae6c43548520e08dd11477e9ea59317087f9c6f33bc79eb701b14b043ff0d177bc419e585c0ecae42420fabb837e602c8a3578ea17dd1a8ed862e301",
                Numeric.toHexString(sign)
        );
    }
}
