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
            margin-top: 100px;
            border-collapse: separate;
            border-spacing: 0;
            table-layout: fixed;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }

        th:first-child {
            border-top-left-radius: 8px;
        }

        th:last-child {
            border-top-right-radius: 8px;
        }

        tr:last-child td:first-child {
            border-bottom-left-radius: 8px;
        }

        tr:last-child td:last-child {
            border-bottom-right-radius: 8px;
        }


        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
            color: black; /* Added to make label text black */
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
            border-radius: 10px;
            cursor: pointer;
            text-align: center;
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
                <th style="background-color: #333; color: #fff;">Username</th>
                <th style="background-color: #333; color: #fff;">Nom</th>
                <th style="background-color: #333; color: #fff;">Prénom</th>
                <th style="background-color: #333; color: #fff;">Mode paiement</th>
                <th style="background-color: #333; color: #fff;">Action</th>
            </tr>
            <#list comptes as compte>
                <tr>
                    <td><input type="text" value="${compte.username}" readonly style="border: 0px solid #ccc; border-radius: 5px;"/></td>
                    <td><input type="text" value="${compte.nom}"               style="border: 1px solid #ccc; border-radius: 5px;"/></td>
                    <td><input type="text" value="${compte.prenom}"            style="border: 1px solid #ccc; border-radius: 5px;"/></td>
                    <td><input type="text" value="${compte.moyen_paiement_pref_id!'non renseigné'}" style="border: 1px solid #ccc; border-radius: 5px;"/></td>
                    <td>
                        <div class="button-container">
                            <input  type="hidden" value="${compte.id}" class="compte-id" />
                            <button type="button" onclick="mettreAJourCompte(this);">update</button>
                            <button type="button" class="delete-button" onclick="supprimerCompte('${compte.username}');">delete</button>
                        </div>
                    </td>
                </tr>
            </#list>
        </table>
    </div>
</section>

<script>
    function mettreAJourCompte(button) {
        let row = button.closest("tr");
        let data = 'id='             + encodeURIComponent(row.querySelector(".compte-id").value) +
                   '&username='      + encodeURIComponent(row.cells[0].querySelector("input").value) +
                   '&nom='           + encodeURIComponent(row.cells[1].querySelector("input").value) +
                   '&prenom='        + encodeURIComponent(row.cells[2].querySelector("input").value) +
                   '&mode_paiement=' + encodeURIComponent(row.cells[3].querySelector("input").value);
        fetch('/adminupdate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: data
        })
            .then(response => {
                if (response.ok) {
                    alert('Compte mis à jour avec succès');
                    window.location.reload();
                } else {
                    alert('Erreur lors de la mise à jour');
                }
            })
            .catch(error => {
                console.error('Erreur:', error);
            });
    }

    function supprimerCompte(username) {
        let data = 'username=' + encodeURIComponent(username);
        if (confirm('Êtes-vous sûr de vouloir supprimer ce compte ?')) {
            fetch('/admindelete', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: data,
            })
                .then(response => {
                    if (response.ok) {
                        alert('Compte supprimé avec succès');
                        window.location.reload();
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
