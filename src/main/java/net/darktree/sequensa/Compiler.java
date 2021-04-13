package net.darktree.sequensa;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;
import net.darktree.sequensa.function.CompilerErrorHandle;
import net.darktree.sequensa.issue.CompilerIssue;

public class Compiler {

    protected final Pointer pointer;

    public Compiler() {
        pointer = Binding.LIBRARY.seq_compiler_new();
    }

    @Override
    protected void finalize() {
        Binding.LIBRARY.seq_compiler_free(pointer);
    }

    public void setOptimizations( int flags ) {
        Binding.LIBRARY.seq_compiler_optimizations(pointer, flags);
    }

    public void setErrorHandle( CompilerErrorHandle handle ) {
        Binding.LIBRARY.seq_compiler_error_handle( pointer, error -> handle.call( new CompilerIssue(error) ) );
    }

    public ByteBuffer compile( String code ) {
        Memory memory = new Memory(8);
        Pointer buffer = Binding.LIBRARY.seq_compiler_build_new( pointer, code, memory );
        return new ByteBuffer( buffer, memory.getInt(0) );
    }

}
