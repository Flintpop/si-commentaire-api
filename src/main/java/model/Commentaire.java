package model;

import com.mongodb.client.MongoCollection;
import database.MongoDB;
import mongoPojo.CommentairePojo;

import java.util.List;

import static database.DatabaseUtils.convertToList;

public class Commentaire {
  // Simule la récupération des commentaires
  public static List<CommentairePojo> getListeCommentaires() {
    MongoCollection<CommentairePojo> collection = MongoDB.getCollection();

    return convertToList(collection);
  }

  // Simule l'ajout d'un commentaire
  public static void ajouterCommentaire(CommentairePojo commentairePojo) {
    // Remplacez cette implémentation par votre logique d'ajout de commentaire
  }
}
