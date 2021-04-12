package net.darktree.sequensa.test;

import net.darktree.sequensa.*;
import net.darktree.sequensa.Compiler;

public class Main {

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        Executor executor = new Executor();

        executor.addNative( "test", stream -> {
            stream.clear();
            stream.add( Generic.newString( "Hello Native Java Function!", false ) );
        } );

        ByteBuffer byteBuffer = compiler.compile("#exit << #test << \"Hello Java!\"");
        executor.execute( byteBuffer );

        Stream results = executor.getResults();
        System.out.println( results.get(0).queryString() );
    }

}
