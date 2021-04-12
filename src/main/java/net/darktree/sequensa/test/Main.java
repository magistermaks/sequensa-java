package net.darktree.sequensa.test;

import net.darktree.sequensa.*;
import net.darktree.sequensa.Compiler;

public class Main {

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        Executor executor = new Executor();

        ByteBuffer byteBuffer = compiler.compile("#exit << \"Hello Java!\"");
        executor.execute( byteBuffer );

        Stream results = executor.getResults();
        System.out.println( results.get(0).queryString() );
    }

}
