package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class Stream {

    protected final Pointer pointer;
    protected boolean allocated = false;

    public Stream( Pointer pointer ) {
        this.pointer = pointer;
    }

    public Stream() {
        this.pointer = Binding.LIBRARY.seq_stream_create();
        this.allocated = true;
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

    public Pointer claim() {
        this.allocated = false;
        return pointer;
    }

    @Override
    protected void finalize() {
        if( allocated ) {
            Binding.LIBRARY.seq_stream_free(pointer);
        }
    }
}
