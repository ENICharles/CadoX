package fr.eni.crt.cadox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import java.util.Date;
import fr.eni.crt.cadox.bll.Manager;
import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.databinding.ActivityArticleViewBinding;

public class ArticleViewActivity extends AppCompatActivity
{
    private Article article = null;

    private ActivityArticleViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /* mise en relation entre vue et controleur (binding devient le lien) */
        binding = DataBindingUtil.setContentView(this,R.layout.activity_article_view);
    }
}