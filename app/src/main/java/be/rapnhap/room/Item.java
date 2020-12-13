package be.rapnhap.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity(tableName = "itemTable")
public class Item {
    @PrimaryKey
    @NonNull
    // ERROR: You must annotate primary keys with @NonNull.
    // "itemCode" is nullable. SQLite considers this a bug
    // and Room does not allow it.
    // See SQLite docs for details: https://www.sqlite.org/lang_createtable.html
    @ColumnInfo(name = "itemCode")
    public String itemCode;

    @ColumnInfo(name = "itemLabel")
    public String itemLabel;

    @ColumnInfo(name = "itemGroup")
    public String itemGroup;

    @ColumnInfo(name = "itemPrice")
    public Double itemPrice;
    // ERROR on BigDecimal: Cannot figure out how to save this field into database.
    // You can consider adding a type converter for it.

    @ColumnInfo(name = "itemSequence")
    public String itemSequence;

    @ColumnInfo(name = "itemGroupSequence")
    public String itemGroupSequence;

    private String mItem;

    public Item(@NonNull String item) {this.mItem = itemCode;}

    public String getItem() {
        return this.mItem;
    }
}
