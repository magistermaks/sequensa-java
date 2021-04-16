package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;
import net.darktree.sequensa.function.ExecutorErrorHandle;
import net.darktree.sequensa.function.Native;
import net.darktree.sequensa.issue.Issue;

public class Executor {

    protected final Pointer pointer;
    private ExecutorErrorHandle errorHandle = error -> false;

    public Executor() {
        pointer = Binding.LIBRARY.seq_executor_new();
    }

    @Override
    protected void finalize() {
        Binding.LIBRARY.seq_executor_free(pointer);
    }

    public void execute( ByteBuffer buffer ) {
        if( buffer.getSize() > 0 ) {
            Binding.LIBRARY.seq_executor_execute(pointer, buffer.getPointer(), buffer.getSize(), error ->
                    errorHandle.call( new Issue(error) )
            );
        }else{
            throw new RuntimeException( "Empty ByteBuffer supplied to Executor!" );
        }
    }

    public void setErrorHandle( ExecutorErrorHandle handle ) {
        errorHandle = handle;
    }

    public void useStrictMath(boolean flag) {
        Binding.LIBRARY.seq_executor_strict_math(pointer, flag);
    }

    public Stream getResults() {
        Pointer results = Binding.LIBRARY.seq_executor_results_stream_ptr(pointer);
        return new Stream(results);
    }

    public synchronized void addNative( String name, Native function ) {
        Binding.LIBRARY.seq_executor_add_native( pointer, name, (pointer) -> {
            function.call( new Stream( pointer ) );
            return Pointer.NULL;
        } );
    }

}
