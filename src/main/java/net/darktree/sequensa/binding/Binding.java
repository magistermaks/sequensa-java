package net.darktree.sequensa.binding;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class Binding {

    private static final int SEQ = 0x534551;
    public static final SequensaLibrary LIBRARY;

    static {
        String location = Platform.isLinux() ? System.getProperty("user.home") + "/sequensa/libseqapi.so" : "C:/sequensa/libseqapi.dll";
        LIBRARY = (SequensaLibrary) Native.loadLibrary(location, SequensaLibrary.class);

        if( LIBRARY.seq_verify() != SEQ ) {
            throw new RuntimeException("Failed to verify Sequensa Library!");
        }
    }

}
