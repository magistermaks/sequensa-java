package net.darktree.sequensa.issue;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class Issue {

    protected final Pointer pointer;

    public Issue( Pointer pointer ) {
        this.pointer = pointer;
    }

    public String getMessage() {
        return Binding.LIBRARY.seq_exception_message(pointer);
    }

}
