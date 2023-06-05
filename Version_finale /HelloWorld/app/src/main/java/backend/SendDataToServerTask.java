package backend;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Classe AsyncTask (effectuant une tâche en arrière-plan ie pas sur le thread principale de
 * l'application afin de ne pas en perturber le fonctionnement) pour envoyer des données à un serveur via une requête POST.
 * Utilisation :
 * 1. Créez une instance de SendDataToServerTask avec le contexte de l'application et l'URL du serveur.
 * 2. Appelez la méthode `sendDataToServer` en passant un Map de données contenant les valeurs à envoyer.
 * 3. La classe exécute la requête POST en arrière-plan et renvoie le résultat dans la méthode `onPostExecute`.
 * 4. Utilisez la méthode `onPostExecute` pour traiter le résultat de la requête, par exemple, afficher un Toast.
 *
 * Notes:
 * - Un Map de données, également appelé tableau associatif, est une structure de données qui permet de stocker
 *   et d'organiser des paires clé-valeur. Chaque élément dans un Map est une association entre une clé unique et
 *   une valeur correspondante.
 * - Découle de ce qui précède que l'on peut envoyer les données d'autant de champs que l'on veut avec cette classe
 * - ATTENTION: bien lire la définition de la route enregistrer_sport elle sert de pattern pour comprendre comment se fera
 *   l'enregistrement des données
 * - Finalement il faut dire que même si créer une route "configurable" ie qui prendrait en argument l'endroit où la donnée
 *   doit être stockée doit être absolument rejetée! Sinon l'application devrait être mise à jour à chaque modification de la BDD
 *   ce qui serait une catastrophe, par ailleurs le code tel qu'il est (en Java) permet de fonctionner avec derrière, n'importe
 *   quel type de BDD pour ce qui est d'envoyer des Maps! (y compris non Sql)
 *
 * Déroulement de l'appel de cette classe:
 *
 * La classe `SendDataToServerTask` est une classe AsyncTask utilisée pour envoyer des données à un serveur via une requête POST. Voici comment une donnée est envoyée en suivant les différentes étapes :
 *
 * 1. Créez une instance de `SendDataToServerTask` avec le contexte de l'application et l'URL du serveur.
 * 2. Appelez la méthode `sendDataToServer` en passant un `Map` de données contenant les valeurs à envoyer.
 * 3. La méthode `sendDataToServer` convertit le `Map` de données en une chaîne de requête ou un format JSON à l'aide de la méthode `convertDataToPostString`.
 * 4. Une instance de `SendDataToServerTask` est créée avec le contexte et l'URL spécifiés.
 * 5. La méthode `execute` est appelée sur la tâche `SendDataToServerTask` avec la chaîne de requête ou le format JSON en tant que paramètre.
 * 6. La méthode `doInBackground` de la tâche est exécutée en arrière-plan.
 * 7. Dans la méthode `doInBackground`, la chaîne de requête ou le format JSON est récupéré en tant que paramètre.
 * 8. Une instance d'URL est créée à partir de l'URL spécifiée.
 * 9. Les objets `HttpURLConnection` et `OutputStream` sont initialisés.
 * 10. Une connexion `HttpURLConnection` est ouverte vers l'URL spécifiée.
 * 11. La méthode de requête est définie sur POST et la sortie des données est activée.
 * 12. La chaîne de requête ou le format JSON est converti en tableau d'octets UTF-8.
 * 13. Le flux de sortie de la connexion est obtenu et les données sont écrites.
 * 14. Le code de réponse HTTP est récupéré.
 * 15. Si le code de réponse est 200 (HTTP OK), un message indiquant que les données ont été enregistrées avec succès est retourné.
 * 16. Sinon, un message d'erreur est retourné.
 * 17. Les ressources ouvertes (connexion et flux de sortie) sont fermées.
 * 18. La méthode `onPostExecute` est appelée avec le résultat de la requête.
 * 19. Dans la méthode `onPostExecute`, un Toast est affiché pour indiquer le résultat de l'opération.
 * 20. La donnée a été envoyée avec succès au serveur et le résultat est affiché dans un Toast.
 *
 * Il est important de noter que la méthode `convertDataToPostString` convertit le `Map` de données en une chaîne
 * de requête au format "clé1=valeur1&clé2=valeur2&clé3=valeur3" en parcourant les paires clé-valeur du `Map`.
 * Cette chaîne de requête est utilisée comme données à envoyer dans la requête POST.
 *
 * En résumé, en utilisant la classe `SendDataToServerTask`, vous pouvez créer une instance, appeler la méthode `sendDataToServer`
 * avec un `Map` de données, qui sera converti en une chaîne de requête ou un format JSON, puis les données seront envoyées au
 * serveur via une requête POST en arrière-plan. Le résultat de la requête sera affiché dans un Toast pour informer l'utilisateur
 * du statut de l'opération.
 */

public class SendDataToServerTask extends AsyncTask<String, Void, String> {

    private Context context;
    private String url;

    public SendDataToServerTask(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // Récupérer la chaîne de requête ou le format JSON des paramètres
            String postData = params[0];

            // Créer une instance d'URL à partir de l'URL spécifiée
            URL url = new URL(this.url);

            // Initialiser les objets HttpURLConnection et OutputStream
            HttpURLConnection connection = null;
            OutputStream outputStream = null;

            try {
                // Ouvrir une connexion HttpURLConnection à l'URL spécifiée
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST"); // Définir la méthode de requête POST
                connection.setDoOutput(true); // Activer la sortie des données

                // Convertir la chaîne de requête ou le format JSON en tableau d'octets UTF-8
                byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

                // Obtenir le flux de sortie de la connexion et écrire les données
                outputStream = connection.getOutputStream();
                outputStream.write(postDataBytes);
                outputStream.flush();

                // Obtenir le code de réponse HTTP de la connexion
                int responseCode = connection.getResponseCode();

                // Vérifier si la réponse est HTTP OK (200)
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    return "Données enregistrées avec succès";
                } else {
                    return "Erreur lors de l'envoi des données";
                }
            } finally {
                // Fermer les ressources ouvertes

                if (outputStream != null) {
                    outputStream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'envoi des données : " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Afficher une notification Toast avec le résultat de l'opération
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    public void sendDataToServer(Map<String, String> dataMap) {
        // Convertir le Map de données en une chaîne de requête ou un format JSON
        String postData = convertDataToPostString(dataMap);

        // Créer une instance de SendDataToServerTask avec le contexte et l'URL spécifiés
        SendDataToServerTask task = new SendDataToServerTask(context, url);

        // Exécuter la tâche AsyncTask avec la chaîne de requête ou le format JSON en tant que paramètre
        task.execute(postData);
    }

    private String convertDataToPostString(Map<String, String> dataMap) { // déclaration d'une variable dataMap de la forme d'une Map (couple clé valeur de la forme <String, String>
        // Convertir le Map de données en une chaîne de requête au format "clé1=valeur1&clé2=valeur2&clé3=valeur3"
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> entry : dataMap.entrySet()) { // Boucle permettant d'effectuer une concaténation
            if (postData.length() != 0) {
                postData.append("&"); // Ajouter un séparateur "&" entre chaque paire clé-valeur
            }
            postData.append(entry.getKey()).append("=").append(entry.getValue()); // Ajouter la paire clé-valeur à la chaîne de requête
        }

        return postData.toString();
    }
}
