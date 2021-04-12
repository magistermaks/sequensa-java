package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class Stream {

    private final Pointer pointer;

    public Stream( Pointer pointer ) {
        this.pointer = pointer;
    }

    public int getSize() {
        return Binding.LIBRARY.seq_stream_size(pointer);
    }

    public void clear() {
        Binding.LIBRARY.seq_stream_clear(pointer);
    }

    public void add( Generic generic ) {
        Binding.LIBRARY.seq_stream_add( pointer, generic.getPointer() );
    }

    public Generic get( int index ) {
        return new Generic( Binding.LIBRARY.seq_stream_generic_ptr( pointer, index ) );
    }

}
