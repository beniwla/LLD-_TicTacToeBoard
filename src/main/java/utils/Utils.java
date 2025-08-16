package utils;

public class Utils {
    
    public Object getIfNull(Object o, Supplier<Object> supplier){
        if( o == null) {
            return supplier.get();
        }
        return o;
    }
}
