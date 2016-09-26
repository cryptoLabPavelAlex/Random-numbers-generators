package com.pavel.alex.lab.first.generator;

import java.security.SecureRandom;
/*
A cryptographically strong random number minimally complies with the statistical random number generator tests specified in FIPS 140-2, Security Requirements for Cryptographic Modules, section 4.9.1. Additionally, SecureRandom must produce non-deterministic output. Therefore any seed material passed to a SecureRandom object must be unpredictable, and all SecureRandom output sequences must be cryptographically strong, as described in RFC 1750: Randomness Recommendations for Security.
*/
public class SecureJavaGenerator implements Generator {

    private SecureRandom random;
    private byte bytes[];
    private int current = 0;

    public SecureJavaGenerator(int count) {
        random = new SecureRandom();
        bytes = new byte[count];
        byte seed[] = random.generateSeed((int) System.currentTimeMillis());
        random.nextBytes(bytes);
    }


    @Override
    public long generateNext() {
        return bytes[current++];
    }

    @Override
    public Type getType() {
        return Type.BYTE;
    }

    @Override
    public String getName() {
        return "SecureJavaGenerator";
    }
}
