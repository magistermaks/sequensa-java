package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class Executor {

    private final Pointer pointer;

    public Executor() {
        pointer = Binding.LIBRARY.seq_executor_new();
    }

    @Override
    protected void finalize() {
        Binding.LIBRARY.seq_executor_free(pointer);
    }

    public void execute( ByteBuffer buffer ) {
        Binding.LIBRARY.seq_executor_execute( pointer, buffer.getPointer(), buffer.getSize() );
    }

    public Stream getResults() {
        Pointer results = Binding.LIBRARY.seq_executor_results_stream_ptr(pointer);
        return new Stream(results);
    }

}
