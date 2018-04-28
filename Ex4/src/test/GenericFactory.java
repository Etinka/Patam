package test;


import java.util.HashMap;
import java.util.Map;

public class GenericFactory<Product> {

	private interface Creator<Product>{
		public Product create(); // no unhandled exceptions
	}
	
	Map<String, Creator<Product>> map;
	
	public GenericFactory(){
		map=new HashMap<>();
	}
	
	public void insertProduct(String key, Class c) {
        if(!map.containsKey(key)){
            map.put(key, () -> {
                try {
                    return (Product) c.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
	}
	
	public Product getNewProduct(String key){
	    if(map.containsKey(key)) {
            return map.get(key).create();
        }
        return null;
	}
}
