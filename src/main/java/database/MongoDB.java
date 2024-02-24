package database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mongoPojo.CommentairePojo;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB {
  private static volatile MongoClient mongoClient = null;
  private static MongoDatabase database = null;
  private static MongoCollection<CommentairePojo> collection = null;
  private static final String DATABASE_NAME = "commentaires";
  private static final String CONNECTION_URL = "mongodb://mongodb:27017";

  private MongoDB() {
    // Constructeur privé pour éviter l'instanciation
  }

  public static MongoCollection<CommentairePojo> getCollection() {
    if (collection != null) {
      return collection;
    }

    synchronized (MongoDB.class) {
      if (mongoClient == null) {
        // Configuration du codec pour POJO
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        // Configuration de la connexion
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_URL))
                .codecRegistry(pojoCodecRegistry)
                .build();

        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);
        collection = database.getCollection("commentaires", CommentairePojo.class);
        System.out.println("Connexion à MongoDB établie.");
      }
    }
    return collection;
  }
}
