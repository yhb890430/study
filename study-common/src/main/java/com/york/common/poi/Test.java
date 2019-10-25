package com.york.common.poi;

import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.TempFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @description: 11
 * @author: york
 * @date: 2019-9-17 14:22
 * @version: <1.0>
 */
public class Test {

//    private static final CipherAlgorithm cipherAlgorithm = CipherAlgorithm.aes128;
//
//    private static final String PADDING = "PKCS5Padding";
//
//    private final SecretKeySpec skeySpec;
//
//    private final byte[] ivBytes;
//
//    private final File tempFile;
//
//    static {
//        try{
//            SecureRandom sr = new SecureRandom();
//            ivBytes = new byte[16];
//            byte[] keyBytes = new byte[16];
//            sr.nextBytes(ivBytes);
//            sr.nextBytes(keyBytes);
//            SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, cipherAlgorithm.jceId);
//            File tempFile = TempFile.createTempFile("poi-temp-data", ".tmp");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        Cipher ciEnc = CryptoFunctions.getCipher(skeySpec, cipherAlgorithm, ChainingMode.cbc, ivBytes, Cipher.ENCRYPT_MODE, PADDING);
//        return new CipherOutputStream(new FileOutputStream(tempFile), ciEnc);
//    }

}
