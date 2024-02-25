package model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.internal.bulk.DeleteRequest;
import database.MongoDB;
import mongoPojo.CommentairePojo;
import org.bson.conversions.Bson;
import validation.CommentaireValidateur;
import validation.ValidateurResultat;

import java.time.Instant;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static database.DatabaseUtils.convertToList;

/**
 * Classe qui
 */
public class Commentaire {
  public static List<CommentairePojo> getListeCommentaires() {
    MongoCollection<CommentairePojo> collection = MongoDB.getCollection();

    return convertToList(collection);
  }

  public static CommentairePojo getCommentaireById(int id) {
    MongoCollection<CommentairePojo> collection = MongoDB.getCollection();
    Bson filter = eq("id", id);
    return collection.find(filter).first();
  }

  /**
   * Méthode pour ajouter un commentaire dans la bdd.
   * @param jsonBody le jsonBody de la requête POST, qui contient le commentaire à ajouter.
   * @return un objet ValidateurResultat qui contient le résultat de la validation du commentaire.
   * @throws JsonSyntaxException si le jsonBody n'est pas valide en termes de syntaxe (c.-à-d. int au lieu de string)
   */
  public static ValidateurResultat ajouterCommentaire(String jsonBody) throws JsonSyntaxException {
    Gson gson = new Gson();
    CommentairePojo commentairePojo = gson.fromJson(jsonBody, CommentairePojo.class);
    commentairePojo.setId(getNextCommentaireId());
    commentairePojo.setDateMessage(Instant.now().toString());
    CommentaireValidateur commentaireValidateur = new CommentaireValidateur();
    ValidateurResultat validationResult = commentaireValidateur.valider(commentairePojo);

    if (validationResult.isValid()) {
      MongoDB.getCollection().insertOne(commentairePojo);
    }

    return validationResult;
  }

  // Méthode pour récupérer le prochain id de commentaire à ajouter dans la bdd
  private static int getNextCommentaireId() {
    List<CommentairePojo> commentaires = getListeCommentaires();
    int maxId = 0;
    for (CommentairePojo commentaire : commentaires) {
      if (commentaire.getId() > maxId) {
        maxId = commentaire.getId();
      }
    }
    return maxId + 1;
  }

  // TODO: À tester
  public static void deleteCommentaire(int id) {
    MongoCollection<CommentairePojo> collection = MongoDB.getCollection();
    Bson filter = eq("id", id);
    DeleteResult deleteResult = collection.deleteOne(filter);

    if (deleteResult.getDeletedCount() == 0) {
      throw new IllegalArgumentException("Commentaire non trouvé.");
    }
  }

  // TODO: À tester
  public static void updateCommentaire(int id, String jsonBody) {
    Gson gson = new Gson();
    CommentairePojo commentairePojo = gson.fromJson(jsonBody, CommentairePojo.class);
    commentairePojo.setId(id);
    CommentaireValidateur commentaireValidateur = new CommentaireValidateur();
    ValidateurResultat validationResult = commentaireValidateur.valider(commentairePojo);

    if (validationResult.isValid()) {
      MongoCollection<CommentairePojo> collection = MongoDB.getCollection();
      Bson filter = eq("id", id);
      collection.replaceOne(filter, commentairePojo);
    }
  }
}
