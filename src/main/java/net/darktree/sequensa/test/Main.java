package net.darktree.sequensa.test;

import net.darktree.sequensa.*;
import net.darktree.sequensa.Compiler;

public class Main {

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        Executor executor = new Executor();
        Version version = new Version();

        compiler.setErrorHandle( issue -> {
            System.out.println( issue.getMessage() );
            return issue.getLevel().isError();
        } );

        executor.addNative( "test", stream -> {
            stream.clear();
            stream.add( Generic.ofString( "Hello Sequensa " + version + "!", false ) );
        } );

        ByteBuffer byteBuffer = compiler.compile("#exit << #test << \"Hello Java!\"");
        executor.execute( byteBuffer );

        Stream results = executor.getResults();
        System.out.println( results.get(0).queryString() );

        Decompiler decompiler = new Decompiler();
        System.out.println( decompiler.decompile( byteBuffer ) );
    }

}
