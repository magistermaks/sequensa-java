package net.darktree.sequensa;

import com.sun.jna.Pointer;
import net.darktree.sequensa.binding.Binding;

public class Generic {

    protected final Pointer pointer;

    Generic( Pointer pointer ) {
        this.pointer = pointer;
    }

    public Pointer getPointer() {
        return pointer;
    }

    public boolean getAnchor() {
        return Binding.LIBRARY.seq_generic_anchor(pointer) != 0;
    }

    public DataType getType() {
        return DataType.values()[ Binding.LIBRARY.seq_generic_type(pointer) - 1 ];
    }

    public long queryLong() {
        return Binding.LIBRARY.seq_generic_number_long(pointer);
    }

    public double queryDouble() {
        return Binding.LIBRARY.seq_generic_number_double(pointer);
    }

    public String queryString() {
        return Binding.LIBRARY.seq_generic_string_string(pointer);
    }

    public boolean queryBoolean() {
        return Binding.LIBRARY.seq_generic_bool_bool(pointer);
    }

    public static Generic ofNumber( double value, boolean anchor ) {
        return new Generic( Binding.LIBRARY.seq_generic_number_create( anchor, value ) );
    }

    public static Generic ofString( String value, boolean anchor ) {
        return new Generic( Binding.LIBRARY.seq_generic_string_create( anchor, value ) );
    }

    public static Generic ofBoolean( boolean value, boolean anchor ) {
        return new Generic( Binding.LIBRARY.seq_generic_bool_create( anchor, value ) );
    }

    public static Generic ofNull( boolean anchor ) {
        return new Generic( Binding.LIBRARY.seq_generic_null_create( anchor ) );
    }

}
