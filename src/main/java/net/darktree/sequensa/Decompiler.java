package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class Decompiler {

    String indentation = "\t";
    int base = 0;

    public void setIndentation(String indentation) {
        this.indentation = indentation;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public String decompile( ByteBuffer buffer ) {
        if( buffer.getSize() > 0 ) {
            Pointer pointer = Binding.LIBRARY.seq_decompile( buffer.getPointer(), buffer.getSize(), indentation, base );
            String string = pointer.getString(0);
            Binding.LIBRARY.seq_free( pointer );
            return string;
        }else{
            throw new RuntimeException( "Empty ByteBuffer supplied to Decompiler!" );
        }
    }

}
