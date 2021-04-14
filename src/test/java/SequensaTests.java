import net.darktree.sequensa.Compiler;
import net.darktree.sequensa.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class SequensaTests {

    @Test
    public void basicReturn() {
        Compiler compiler = new Compiler();
        Executor executor = new Executor();

        ByteBuffer byteBuffer = compiler.compile("#exit << 123");
        executor.execute( byteBuffer );
        Stream results = executor.getResults();

        assertEquals(1, results.getSize());
        Generic generic = results.get(0);
        assertSame(generic.getType(), DataType.NUMBER);
        assertEquals(123, generic.queryLong());
    }

    @Test
    public void basicDecompiler() {
        Compiler compiler = new Compiler();
        Decompiler decompiler = new Decompiler();

        // space + new line at the end to match decompiler's output
        String source = "#exit << \"Hello World!\" \n";

        ByteBuffer byteBuffer = compiler.compile(source);
        assertEquals(decompiler.decompile(byteBuffer), source);
    }

    @Test
    public void nativeCallback() {
        Compiler compiler = new Compiler();
        Executor executor = new Executor();

        executor.addNative("testA", stream -> {
            double value = stream.get(0).queryDouble() * 2;
            stream.clear();
            stream.add( Generic.ofNumber(value, false) );
        } );

        executor.addNative("testB", stream -> {
            double value = stream.get(0).queryDouble() * 13;
            stream.clear();
            stream.add( Generic.ofNumber(value, false) );
        } );

        ByteBuffer byteBuffer = compiler.compile("#exit << #testA << #testB << 100");
        executor.execute( byteBuffer );
        Stream results = executor.getResults();

        assertEquals(1, results.getSize());
        Generic generic = results.get(0);
        assertSame(generic.getType(), DataType.NUMBER);
        assertEquals(100 * 2 * 13, generic.queryLong());
    }

    @Test
    public void compilerErrorCallback() {
        Compiler compiler = new Compiler();
        AtomicBoolean flag = new AtomicBoolean(false);

        compiler.setErrorHandle( issue -> {
            flag.set(true);
            return true;
        } );

        compiler.compile("set var <<");
        assertTrue( flag.get() );
    }

    @Test
    public void byteBufferCopy() {
        Compiler compiler = new Compiler();

        ByteBuffer bb1 = compiler.compile("#exit << (2 + 4 * 8)");
        ByteBuffer bb2 = new ByteBuffer( bb1.getBytes() );

        assertArrayEquals(bb1.getBytes(), bb2.getBytes());
    }

    @Test
    public void compilerOptimizations() {
        Compiler compiler = new Compiler();

        compiler.setOptimizations( Optimizations.empty().pureExpr().getFlags() );
        int size1 = compiler.compile("#exit << (2 + 4 * 8)").getSize();

        compiler.setOptimizations( Optimizations.empty().getFlags() );
        int size2 = compiler.compile("#exit << (2 + 4 * 8)").getSize();

        assertTrue( size1 < size2 );
    }

}
