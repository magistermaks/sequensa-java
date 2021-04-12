package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class ByteBuffer {

    private final Pointer pointer;
    private final int size;

    public ByteBuffer( Pointer pointer, int size ) {
        this.pointer = pointer;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Pointer getPointer() {
        return pointer;
    }

    public byte[] getBytes() {
        return pointer.getByteArray(0, size);
    }

    @Override
    protected void finalize() {
        Binding.LIBRARY.seq_compiler_build_free(pointer);
    }

}
