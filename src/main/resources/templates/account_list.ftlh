<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Votre Titre</title>
    <style>
        .accounts-section {
            text-align: center;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            table-layout: fixed;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            color: black;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }

        button:hover {
            background-color: #45a049;
        }

        td button {
            display: block;
            margin: 0 auto;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
        }

        .delete-button {
            background-color: red;
            color: white;
        }
    </style>
</head>
<body>
<section class="accounts-section">
    <div class="container">
        <table>
            <tr>
                <th>Username</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Action</th>
            </tr>
            <#list comptes as compte>
                <tr>
                    <td><input type="text" value="${compte.username}" /></td>
                    <td><input type="text" value="${compte.nom}" /></td>
                    <td><input type="text" value="${compte.prenom}" /></td>
                    <td>
                        <div class="button-container">
                            <button type="button" onclick="mettreAJourCompte('${compte.username}');">Mettre à jour</button>
                            <button type="button" class="delete-button" onclick="supprimerCompte('${compte.username}');">Supprimer</button>
                        </div>
                    </td>
                </tr>
            </#list>
        </table>
    </div>
</section>

<script>
    function mettreAJourCompte(username) {

    }

    function supprimerCompte(username) {
        if (confirm('Êtes-vous sûr de vouloir supprimer ce compte ?')) {
            fetch('/myaccountadmin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'username=' + encodeURIComponent(username),
            })
                .then(response => {
                    if (response.ok) {
                        alert('Compte supprimé avec succès');
                    } else {
                        alert('Erreur lors de la suppression');
                    }
                })
                .catch(error => {
                    console.error('Erreur:', error);
                });
        }
    }
</script>
</body>
</html>
