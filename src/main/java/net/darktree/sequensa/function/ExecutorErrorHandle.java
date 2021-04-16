package net.darktree.sequensa.function;

import net.darktree.sequensa.issue.Issue;

public interface ExecutorErrorHandle {
    boolean call(Issue issue);
}
