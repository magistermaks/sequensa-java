package net.darktree.sequensa;

import net.darktree.sequensa.binding.Binding;

public class Version {

    private final byte major;
    private final byte minor;
    private final byte patch;
    private final String standard;

    public Version() {
        major = (byte) Binding.LIBRARY.seq_version_major();
        minor = (byte) Binding.LIBRARY.seq_version_minor();
        patch = (byte) Binding.LIBRARY.seq_version_patch();
        standard = Binding.LIBRARY.seq_standard();
    }

    public String getVersion() {
        return major() + "." + minor() + "." + patch();
    }

    @Override
    public String toString() {
        return getVersion() + " (" + standard() + ")";
    }

    public String standard() {
        return standard;
    }

    public int major() {
        return major;
    }

    public int minor() {
        return minor;
    }

    public int patch() {
        return patch;
    }

}
