package com.cj.bootstrap.util;

import com.google.common.io.BaseEncoding;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public final class HWID {
    public final String value;

    public HWID(String value) {
        this.value = value;
    }

    public static HWID get() {
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null && hardwareAddress.length > 0) {
                    String hwid = BaseEncoding.base16().encode(hardwareAddress);
                    return new HWID(hwid);
                }
            }
        } catch (SocketException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException("Could not create hwid");
    }

    public static void main(String[] args) {
        System.out.println(HWID.get().value);
    }
}
