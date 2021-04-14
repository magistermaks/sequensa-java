package net.darktree.sequensa;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class ByteBuffer {

    protected final Pointer pointer;
    protected final int size;

    public ByteBuffer( Pointer pointer, int size ) {
        this.pointer = pointer;
        this.size = size;
    }

    public ByteBuffer( byte[] bytes ) {
        Memory memory = new Memory(bytes.length);
        memory.write( 0, bytes, 0, bytes.length );
        this.pointer = Binding.LIBRARY.seq_buffer_new( memory, bytes.length );
        this.size = bytes.length;
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
