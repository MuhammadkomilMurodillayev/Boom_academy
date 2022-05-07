package team.bahor.enums;

import org.apache.commons.codec.digest.MurmurHash2;

public enum Role {
    SUPER_ADMIN(100),
    ADMIN(60),
    ACCOUNTANT(50),
    MANAGER(30),
    SUPPORT(20),
    USER(10);

    private final int code;

    Role(int code) {
        this.code = code;
    }
}
