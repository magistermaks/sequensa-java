package net.darktree.sequensa.issue;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class CompilerIssue extends Issue {

   public enum ErrorType {
        WARNING,
        ERROR,
        FATAL;

        public boolean isError() {
            return this != WARNING;
        }
    }

    public CompilerIssue(Pointer pointer) {
        super(pointer);
    }

    public ErrorType getLevel() {
        return ErrorType.values()[ Binding.LIBRARY.seq_compiler_error_level(pointer) ];
    }

}
