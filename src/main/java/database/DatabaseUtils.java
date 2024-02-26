package database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;

public class DatabaseUtils {
  public static <E> ArrayList<E> convertToList(MongoCollection<E> collection) {
    if (collection == null) {
      return null;
    }
    FindIterable<E> findIterable = collection.find();
    ArrayList<E> list = new ArrayList<>();
    for (E item : findIterable) {
      list.add(item);
    }
    return list;
  }

  public static <E> ArrayList<E> convertToList(FindIterable<E> findIterable) {
    if (findIterable == null) {
      return null;
    }
    ArrayList<E> list = new ArrayList<>();
    for (E item : findIterable) {
      list.add(item);
    }
    return list;
  }
}
