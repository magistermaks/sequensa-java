package net.darktree.sequensa;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class Compiler {

    private final Pointer pointer;

    public Compiler() {
        pointer = Binding.LIBRARY.seq_compiler_new();
    }

    @Override
    protected void finalize() {
        Binding.LIBRARY.seq_compiler_free(pointer);
    }

    public ByteBuffer compile( String code ) {
        Memory memory = new Memory(8);
        Pointer buffer = Binding.LIBRARY.seq_compiler_build_new( pointer, code, memory );
        return new ByteBuffer( buffer, memory.getInt(0) );
    }

}
