package fr.eni.crt.cadox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import java.util.Date;
import fr.eni.crt.cadox.bll.Manager;
import fr.eni.crt.cadox.bo.Article;

public class ArticleViewActivity extends AppCompatActivity
{
    private Article article = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        Manager manager = new Manager();

        /* récupération des ctrls */
        ImageButton btnInternet    = findViewById(R.id.buttonInternet);
        ImageButton btnEdite       = findViewById(R.id.buttonEdite);
        ImageButton btnSMS         = findViewById(R.id.buttonSMS);

        TextView    name           = findViewById(R.id.name_article);
        TextView    descriptif     = findViewById(R.id.descriptif_article);
        TextView    prix           = findViewById(R.id.prix_article);
        CheckBox    achete         = findViewById(R.id.achete_article);
        RatingBar   satisfaction   = findViewById(R.id.satisfaction_article);
        TextView    link           = findViewById(R.id.link);

        /* récupération de l'article d'ID 1 */
        article = manager.getArticleById(1);

        if(article != null)
        {
            /* gestion du label date */
            checkDateAchat();

            /* attribution des valeurs */
            name.setText(article.getIntitule());
            descriptif.setText(article.getDescription());
            achete.setChecked(article.isAchete());
            satisfaction.setRating(article.getNiveau());
            link.setText(article.getUrl());
            prix.setText(article.getPrix().toString());
        }

        btnInternet.setOnClickListener((v ->
        {
            TextView tv = findViewById(R.id.link);
            Log.i("mainAc", "internet");
            Toast.makeText(ArticleViewActivity.this, tv.getText(), Toast.LENGTH_LONG).show();
        }));

        btnEdite.setOnClickListener((v ->
        {
            Log.i("mainAc", "edit");

            AlertDialog.Builder builder = new AlertDialog.Builder(ArticleViewActivity.this);

            builder.setMessage("Voulez-vous vraiment modifier cet article?")
                    .setTitle("Confirmation de modification");

            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(ArticleViewActivity.this, "Modification de l'article", Toast.LENGTH_LONG).show();
                }
            });

            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(ArticleViewActivity.this, "Pas de modification de l'article", Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog dialog = builder.create();

            dialog.show();
        }));

        btnSMS.setOnClickListener((v -> {
            Log.i("mainAc", "sms");
            TextView txt = findViewById(R.id.prix_article);

            Snackbar.make(txt, "text example", Snackbar.LENGTH_LONG).
                    setAction(R.string.actionTexte, view ->
                    {
                        Toast.makeText(ArticleViewActivity.this, "Envoie du sms", Toast.LENGTH_LONG).show();
                    })
                    .show();

        }));
    }

    /**
     * Modifie l'affichage en fonction de l'article
     */
    private void checkDateAchat()
    {
        Date now = new Date();

        TextView btnCheckBoxDateAchat = findViewById(R.id.lab_achete);

        if(article.isAchete())
        {
            btnCheckBoxDateAchat.setText("acheté le : " + article.getDateAchat().toString());
        }
        else
        {
            Log.i("mainAc","ou pas");

            btnCheckBoxDateAchat.setText("");
        }
    }

    /**
     * Méthode appelé par le ctrl
     * @param view
     */
    public void methodeOnclick(View view)
    {
        Manager manager = new Manager();

        CheckBox btnCheckBox = findViewById(R.id.achete_article);

        if(btnCheckBox.isChecked())
        {
            /* mise à la date du jour */
            article.setDateAchat(new Date());

            /* mise à jour de l'article */
            manager.updateArticle(article);

            article.setAchete(true);
        }
        else
        {
            article.setAchete(false);
        }

        checkDateAchat();
    }
}