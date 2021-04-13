package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class ByteBuffer {

    protected final Pointer pointer;
    protected final int size;

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
        if( size != 0 ) Binding.LIBRARY.seq_compiler_build_free(pointer);
    }

}
