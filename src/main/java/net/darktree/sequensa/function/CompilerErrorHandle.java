package net.darktree.sequensa.function;

import net.darktree.sequensa.issue.CompilerIssue;

public interface CompilerErrorHandle {
    boolean call(CompilerIssue issue);
}
