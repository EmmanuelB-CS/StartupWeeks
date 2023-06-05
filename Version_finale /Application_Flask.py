import matplotlib
matplotlib.use('Agg')  # Utiliser le backend "agg" de Matplotlib (nécessaire sinon rien ne fonctionne, pourquoi? No idée)

from flask import Flask, jsonify, request
import mysql.connector
import matplotlib.pyplot as plt
import io
from flask import send_file


app = Flask(__name__)

conn = mysql.connector.connect(
    host='localhost',
    user='root',
    password='WalpurgiS2001',
    database='ChuckNorris'
)

@app.route('/enregistrer/<table>/<colonne>', methods=['POST'])
def enregistrer_donnees(table, colonne):
    data = request.get_data()  # get data récupère les données de l'app
    print('enregistrer table = ', table, 'colonne = ', colonne, 'data = ', data)
    cursor = conn.cursor()
    insert_query = f"INSERT INTO {table} ({colonne}) VALUES (%s)"
    cursor.execute(insert_query, (data,))
    conn.commit()

    return 'Données enregistrées avec succès'

@app.route('/enregistrer_sport', methods=['POST'])
def enregistrer_sport():
    """
    Route Flask pour traiter les données liées à DeuxièmeQuoiDeNeuf.
    Cette route permet de recevoir des données via une requête POST
    et de les enregistrer dans une table SQL spécifiée.

    Paramètres de la requête POST:
    - intitule: Le champ "intitule" contenant la valeur à enregistrer.
    - duree: Le champ "duree" contenant la valeur à enregistrer.
    - effet: Le champ "effet" contenant la valeur à enregistrer.

    Returns:
    - Une réponse indiquant que les données ont été enregistrées avec succès.

    Notes: 
    - Cette route est adaptable à n'importe quelle page où l'on récupèrerait 
    plusieurs data contenues dans des edit text, il suffit pour l'adapter de modifier 
    les lignes 52-53-54 et 60
    """
    # Récupérer les données des champs distincts dans la requête POST
    intitule = request.form.get('intitule') # les '' sont les clés du dataMap
    duree = request.form.get('duree')
    effet = request.form.get('effet')

    # Création de l'objet cursor
    cursor = conn.cursor()

    # Insérer les données dans la table SQL
    insert_query = "INSERT INTO sport (intitule, duree, effet) VALUES (%s, %s, %s)"
    data = (intitule, duree, effet)
    cursor.execute(insert_query, data)

    # Valider la transaction et fermer la connexion à la base de données
    conn.commit()
    cursor.close()

    # Retourner une réponse indiquant que les données ont été enregistrées avec succès
    return 'Les données ont été enregistrées avec succès.'


@app.route('/create_graph', methods=['GET'])
def graphique_activites():
    """
    Route Flask pour créer un graphique des activités sportives.

    La route exécute une requête SQL pour récupérer les données des activités sportives
    des 3 derniers jours, regroupées par date d'entrée. Ensuite, elle traite les résultats
    de la requête en extrayant les durées et les dates. Ces données sont utilisées pour créer
    un graphique représentant la durée totale des activités par jour.

    Returns:
    - Un fichier image (format PNG) représentant le graphique des durées d'activité sportive
      des 3 derniers jours.
    
    Notes:
    - Il suffit de modifier la ligne 91 pour modifier les intervalles de temps considérés
    - Bien penser à convertir en int les données récupérées des tables SQL qui seront affichées en ordonnées
    """
    # Exécution de la requête SQL pour récupérer les données
    cursor = conn.cursor()
    query = "SELECT SUM(duree), DATE(date_entree) FROM sport WHERE date_entree >= NOW() - INTERVAL 3 DAY GROUP BY DATE(date_entree)"
    cursor.execute(query)
    results = cursor.fetchall() # récupère tous les résultats de la requête sous la forme d'une liste de tuples

    # Traitement des résultats de la requête
    durees = []
    dates = []

    for result in results:
        duree = result[0] # je prends le premiers élément du tuple result (duree, date_entree) soit duree
        date_entree = result[1].strftime('%Y-%m-%d')

        if duree is not None:
            duree_minutes = int(duree)
            durees.append(duree_minutes)
            dates.append(date_entree)

    # Création du graphique
    plt.plot(dates, durees)
    plt.xlabel('Date')
    plt.ylabel('Durée (minutes)')
    plt.title('Durées d\'activité sportive des 3 derniers jours')
    plt.xticks(rotation=45) # Permet de tourner de 45 degrés les étiquettes en abscisses, améliore leur lisibilité

    # Convertir le graphique en image
    image_stream = io.BytesIO()

    plt.savefig(image_stream, format='png')
    plt.close()

    image_stream.seek(0)
    return send_file(image_stream, mimetype='image/png')


if __name__ == '__main__':
    app.run(host='138.195.52.137', port='5000')
