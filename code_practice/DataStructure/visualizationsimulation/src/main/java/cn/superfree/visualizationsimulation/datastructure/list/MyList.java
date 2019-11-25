package cn.superfree.visualizationsimulation.datastructure.list;

import java.util.Arrays;
import java.util.Map;

public abstract class MyList {

    protected String type;

    private static Map<String, Class<?>> options;

    static {
        options.put("SingleLinkedList", SingleLinkedList.class);
    }

    public static MyList create(String type) {
        try {
            return (MyList) options.get(type).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get length of the list
     * @return
     */
    public abstract int length();

    /**
     * rearrange the list into an array
     * @return
     */
    public abstract int[] toArray();

    /**
     * insert data at the end of the list
     * @param data
     * @return index | -1
     */
    public abstract int insert(int data);

    /**
     * insert data at index of the list
     * @param index
     * @param data
     * @return index | -1
     */
    public abstract int insert(int index, int data);

    /**
     * reset the list
     */
    public abstract void reset();

    public MyListResponse formData() {
        MyListResponse response = new MyListResponse();
        response.setType(type);
        response.setLength(length());
        response.setData(toArray());
        return response;
    }

    @Override
    public String toString() {
        MyListResponse response = formData();
        StringBuilder result = new StringBuilder();
        result.append("type: " + response.getType() + "\n");
        result.append("length: " + response.getLength() + "\n");
        result.append("data: " + Arrays.toString(response.getData()) + "\n");
        return result.toString();
    }
}
