public class Item{
    private ItemType type;
    private int value = 0;

    public ItemType type(){
        return this.type;
    }

    public int value(){
        return this.value;
    }

    public static Item Value(int i) {
        Item myitem = new Item();
        myitem.value=i;
        myitem.type=ItemType.VALUE;
        return myitem;
    }

    public static Item ADD(){
        Item myitem = new Item();
        myitem.value=0;
        myitem.type=ItemType.ADD;
        return myitem;
    }

    public static Item MUL() {
        Item myitem = new Item();
        myitem.value=0;
        myitem.type=ItemType.MUL;
        return myitem;
    }

    public static Item DIV(){
        Item myitem = new Item();
        myitem.value=0;
        myitem.type=ItemType.DIV;
        return myitem;
    }

    public static Item SUB(){
        Item myitem = new Item();
        myitem.value=0;
        myitem.type=ItemType.SUB;
        return myitem;

    }

    public enum ItemType { ADD, SUB, MUL, DIV, VALUE }

}


