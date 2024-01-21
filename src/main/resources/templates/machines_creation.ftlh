<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un cours</title>
    <style>
        .add-machines-section .container {
            background: #fff;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
            margin-top: 100px;
            padding: 20px;
            padding-top: 40px;
            padding-bottom: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .add-machines-section h2 {
            margin-bottom: 50px;
            text-align: center;
            color: #333;
        }

        .add-machines-section form {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 10px;
        }

        .add-machines-section form .form-group {
            display: flex;
            flex-direction: column;
        }
        .add-machines-section form label {
            margin-bottom: 5px;
            font-weight: bold;
        }

        .add-machines-section form input,
        .add-machines-section form select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 270px;
        }

        .add-machines-section form input[type="submit"] {
            grid-column: 1 / -1;
            padding: 10px 15px;
            border-radius: 10px;
            border: none;
            background-color: #3F3F3F;
            color: white;
            cursor: pointer;
            transition: background-color 0.2s;
            width: 1130px;
        }

        .add-machines-section form input[type="submit"]:hover {
            background-color: #323232;
        }

    </style>
</head>
<body>

<section class="add-machines-section">
    <div class="container">
        <h2>Ajouter une nouvelle machine</h2>
        <form action="/machinescreate" method="post">
            <div class="form-group">
                <label for="fitness_id">Fit:</label>
                <select id="fitness_id" name="fitness_id" required>
                    <option value="1">1</option>
                </select>
            </div>
            <div class="form-group">
                <label for="salle_id">Salle:</label>
                <select id="salle_id" name="salle_id" required>
                    <option value="Salle A">Salle A</option>
                    <option value="Salle B">Salle B</option>
                    <option value="Salle C">Salle C</option>
                </select>
            </div>
            <div class="form-group">
                <label for="etat">Etat:</label>
                <select id="etat" name="etat" required>
                    <option value="Salle A">neuf</option>
                    <option value="Salle B">usagé</option>
                    <option value="Salle C">abîmé</option>
                </select>
            </div>
            <div class="form-group">
                <label for="type_machine">Type:</label>
                <select id="type_machine" name="type_machine" required>
                    <option value="Escalator">Escalator</option>
                    <option value="Tapis de course">Tapis de course</option>
                    <option value="Vélo elliptique">Vélo elliptique</option>
                </select>
            </div>
            <input type="submit" value="Ajouter la machine">
        </form>
    </div>
</section>

</body>
</html>