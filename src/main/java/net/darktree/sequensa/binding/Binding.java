package net.darktree.sequensa.binding;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class Binding {

    public static final SequensaLibrary LIBRARY;

    static {
        String location = Platform.isLinux() ? System.getProperty("user.home") + "/sequensa/libseqapi.so" : "C:/sequensa/libseqapi.dll";
        LIBRARY = (SequensaLibrary) Native.loadLibrary(location, SequensaLibrary.class);

        if( !LIBRARY.seq_verify() ) {
            throw new RuntimeException("Failed to verify Sequensa Library!");
        }
    }

}
