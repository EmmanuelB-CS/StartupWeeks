package backend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

public class CreateGraph {

    private static final String GRAPH_URL = "http://138.195.52.137:5000/create_graph";

    public static void displayGraph(ImageView imageView) {
        // Appel de la route Flask pour récupérer le chemin du fichier graphique
        new LoadGraphTask(imageView).execute(GRAPH_URL);
    }

    private static class LoadGraphTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        public LoadGraphTask(ImageView imageView) {
            imageViewReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                // Récupérer l'URL du fichier graphique depuis le résultat JSON
                String graphFileUrl = urls[0];

                // Télécharger l'image depuis l'URL
                InputStream inputStream = new URL(graphFileUrl).openStream();

                // Décoder l'input stream en une image bitmap
                return BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                Log.e("CreateGraph", "Erreur lors du chargement du graphique : " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    // Afficher l'image bitmap dans l'ImageView
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
