package test;

import java.util.Arrays;

public class Q2 {

	public static interface StringReader{
		String readString();
	}
	
	public static class MyWordFilter implements StringReader { // Decorator
        String string;
		public MyWordFilter(StringReader str){
			string = str.readString();
		}
		@Override
		public String readString() {
            Object[] temp = Arrays.stream(string.split(" ")).distinct().toArray();
			StringBuilder builder = new StringBuilder();
			for(Object str: temp){
			    builder.append(str);
			    builder.append(";");
            }
			return builder.toString();
		}


	}
	
	public static interface IntArrayReader{
		int[] readIntArray();
	}

	public static interface Converter {
	    int[] getArr();
    }
	public static class MyIntArrayToStringReader implements StringReader{ // Object Adapter
        int[] arr;
        MyIntArrayToStringReader(MyIntArrayReader reader){
            arr = Arrays.stream(reader.readIntArray()).distinct().toArray();
        }

        MyIntArrayToStringReader(Converter converter){
            arr = Arrays.stream(converter.getArr()).distinct().toArray();
        }


        @Override
        public String readString() {
            StringBuilder builder = new StringBuilder();
            for(int num: arr){
                builder.append(num);
                builder.append(" ");
            }
            return builder.toString();
        }
    }

	// ---------------------- test API ------------------------------
	
	public static class MyStringReader implements StringReader{
		@Override
		public String readString() {
			return "Hello Hello World World Q2";
		}
	}
	
	public static class MyIntArrayReader implements IntArrayReader{
		@Override
		public int[] readIntArray() {
			int[] r={100,100,50,50,13,14};
			return r;
		}
	}
	
	public static void testAPI(){
		StringReader reader=new MyWordFilter(new MyStringReader());
		System.out.println(reader.readString()); // Hello;World;Q2;
		
		reader=new MyWordFilter(new MyIntArrayToStringReader(new MyIntArrayReader()));
		System.out.println(reader.readString()); // 100;50;13;14;
	}
}
