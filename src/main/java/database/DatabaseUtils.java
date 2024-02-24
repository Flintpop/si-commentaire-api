package database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
  public static <E> ArrayList<E> convertToList(MongoCollection<E> collection) {
    FindIterable<E> findIterable = collection.find();
    ArrayList<E> list = new ArrayList<>();
    for (E item : findIterable) {
      list.add(item);
    }
    return list;
  }
}
